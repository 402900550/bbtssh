package com.hfkj.bbt.operationsManagement.activiti;

import com.hfkj.bbt.base.dao.AccessoryDao;
import com.hfkj.bbt.base.dao.ObjectDao;
import com.hfkj.bbt.base.dao.OperationsDao;
import com.hfkj.bbt.base.dao.UserDao;
import com.hfkj.bbt.base.entity.Accessory;
import com.hfkj.bbt.base.entity.Operations;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.DateUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.operationsManagement.web.vo.HistoricActivityVo;
import com.hfkj.bbt.operationsManagement.web.vo.OperationVo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2018-01-05.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ActivitiService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private AccessoryDao accessoryDao;
    @Autowired
    private OperationsDao operationsDao;


    /**
     * 根据流程实例ID获取历史活动
     *
     * @param processId
     * @return
     */
    public List<HistoricActivityVo> getHistoryByProcessInstanceId(String processId) {
        List<HistoricActivityVo> voList = new ArrayList<HistoricActivityVo>();
        HistoricActivityVo vo;
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processId)
                .orderByHistoricActivityInstanceStartTime().asc().list();
        if (ComUtil.listNotNullAndEmpty(list)) {
            for (HistoricActivityInstance instance : list) {
                vo = new HistoricActivityVo();
                vo.setEndTime(DateUtil.tranDateToString("yyyy-MM-dd HH:mm:ss", instance.getEndTime()));
                vo.setStartTime(DateUtil.tranDateToString("yyyy-MM-dd HH:mm:ss", instance.getStartTime()));
                String assignee = instance.getAssignee();
                if (ComUtil.stringIsNotNull(assignee)) {
                    vo.setOperateUserName(userDao.getUserById(Long.valueOf(assignee)).getRealName());
                }
                if (ComUtil.stringIsNotNull(instance.getTaskId())) {
                    List<HistoricVariableInstance> variableInstances =
                            historyService.createHistoricVariableInstanceQuery().taskId(instance.getTaskId()).list();
                    if (ComUtil.listNotNullAndEmpty(variableInstances)) {
                        for (HistoricVariableInstance var : variableInstances) {
                            if ("remark".equals(var.getVariableName())) {
                                vo.setRemark((String) var.getValue());
                            }

                        }
                    }
                }
                vo.setActivityName(instance.getActivityName());
                voList.add(vo);
            }
        }

        return voList;
    }

    /**
     * 处理自动异常任务
     *
     * @return
     */
    @Transactional(readOnly = false)
    public void doCompleteTask(String taskId, Map<String, Object> values, String processId,String approval) {
        User currentUser = UserUtil.getCurrentUser();
        //拾取任务
        taskService.claim(taskId, String.valueOf(currentUser.getId()));
        taskService.setVariableLocal(taskId,"remark",approval);
        taskService.complete(taskId,values);
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
        Date endTime = historicProcessInstance.getEndTime();
        if (endTime != null) {
            Operations operations = operationsDao.getOpertionbyProcessId(processId);
            operations.setEndDate(endTime);
        }
    }

    /**
     * 处理手动异常任务
     *
     * @return
     */
    @Transactional(readOnly = false)
    public void doCompleteTask(String taskId, String processId,String approval) {
        User currentUser = UserUtil.getCurrentUser();
        //拾取任务
        taskService.claim(taskId, String.valueOf(currentUser.getId()));
        taskService.setVariableLocal(taskId,"remark",approval);
        taskService.complete(taskId);
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
        Date endTime = historicProcessInstance.getEndTime();
        if (endTime != null) {
            Operations operations = operationsDao.getOpertionbyProcessId(processId);
            operations.setEndDate(endTime);
            //设置报废
            Accessory accessory = accessoryDao.findAccessoryById(operations.getAccessoryId());
            if (accessory!=null){
                accessory.setFacilities(3);
                accessoryDao.update(accessory);
            }


        }
    }


    /**
     * 启动流程实例
     * 根据流程图key
     *
     * @return
     */
    @Transactional(readOnly = false)
    public String startHandException(Map<String, Object> values, String key) {
        //通过流程定义KEY启动流程,并将异常关联的表数据添加到流程中
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey(key, values);
        return processInstance.getProcessInstanceId();
    }


    /**
     * 根据当前用户查询待处理流程任务
     */
    public List<OperationVo> getProcessTask(String schoolCode,String roleName,Long userId) {
        List<OperationVo> allVo = new ArrayList<OperationVo>();
        //如果当前用户是厂商 则查询个人任务
        if (ComUtil.stringIsNotNull(schoolCode)){
            if ("SCHOOLADMIN".equalsIgnoreCase(roleName)){
                //否则查询学校管理员的任务
                List<Task> listSchool=new ArrayList<Task>();
                List<Operations> operationsList = operationsDao.getOpertionsBySchool(schoolCode);
                if (ComUtil.listNotNullAndEmpty(operationsList)){
                    for (Operations op:operationsList){
                        String processId = op.getProcessId();
                        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
                        if (task!=null){
                            listSchool.add(task);
                        }
                    }
                }
                allVo.addAll(getProcessValues(listSchool));
            }else {
                //查询当前用户能看到的组任务
                List<Task> listGroup = taskService.createTaskQuery()
                        .taskCandidateUser(String.valueOf(userId))
                        .orderByTaskCreateTime().desc()
                        .list();
                List<Task> listSingle = taskService.createTaskQuery().taskAssignee(String.valueOf(userId))
                        .orderByTaskCreateTime().desc().list();

                allVo.addAll(getProcessValues(listGroup));
                allVo.addAll(getProcessValues(listSingle));
            }
        }


        return allVo;
    }


    /**
     * 解析任务
     *
     * @param tasks
     * @return
     */
    private List getProcessValues(List<Task> tasks) {
        List<OperationVo> list = new ArrayList<OperationVo>();
        OperationVo vo;
        //查询本人待处理流程
        if (ComUtil.listNotNullAndEmpty(tasks)) {
            for (Task task : tasks) {
                vo = new OperationVo();
                vo.setTaskId(task.getId());
                vo.setProcessStatus(task.getName());
                vo.setProcessId(task.getProcessInstanceId());
                list.add(vo);
            }

        }
        return list;
    }


    /**
     * 判断是否属于自己可以审批的任务,并响应接单
     *
     * @param processId
     * @return
     */

    public Task isMyTask(String processId) {
        User currentUser = UserUtil.getCurrentUser();
        Task task=taskService.createTaskQuery().processInstanceId(processId).taskAssignee(String.valueOf(currentUser.getId())).singleResult();
        return task;
    }

    @Transactional(readOnly = false)
    public void acceptTask(String taskId){
        //判断是否是自己的组任务
        User currentUser = UserUtil.getCurrentUser();
        Task canClam = taskService.createTaskQuery().taskId(taskId).taskCandidateUser(String.valueOf(currentUser.getId())).singleResult();
        if (canClam!=null){
            //若不为空 则说明可以接受任务
            taskService.claim(taskId,String.valueOf(currentUser.getId()));
            taskService.setVariableLocal(taskId,"remark","已响应");
            Map<String,Object> map=new HashMap<String, Object>();
            map.put(Operations.PENDINGUSER,String.valueOf(currentUser.getId()));
            taskService.complete(taskId,map);
        }
    }

    @Transactional(readOnly = false)
    public void acceptAutoTask(String taskId){
        //判断是否是自己的组任务
        User currentUser = UserUtil.getCurrentUser();
        Task canClam = taskService.createTaskQuery().taskId(taskId).taskCandidateUser(String.valueOf(currentUser.getId())).singleResult();
        Role role = UserUtil.getCurrentMostHeighRole();
        if (canClam!=null){
            if ("SCHOOLADMIN".equals(role.getRoleName())){
                //若不为空 则说明可以接受任务
                taskService.claim(taskId,String.valueOf(currentUser.getId()));
                taskService.setVariableLocal(taskId,"remark","已响应");
                Map<String,Object> map=new HashMap<String, Object>();
                map.put(Operations.SCHOOLMANAGER,String.valueOf(currentUser.getId()));
                taskService.complete(taskId,map);
            }else {
                //若不为空 则说明可以接受任务
                taskService.claim(taskId,String.valueOf(currentUser.getId()));
                taskService.setVariableLocal(taskId,"remark","已响应");
                Map<String,Object> map=new HashMap<String, Object>();
                map.put(Operations.PENDINGUSER,String.valueOf(currentUser.getId()));
                taskService.complete(taskId,map);
            }
        }
    }

}


















