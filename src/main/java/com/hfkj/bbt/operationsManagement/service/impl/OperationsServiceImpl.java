package com.hfkj.bbt.operationsManagement.service.impl;

import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.DateUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.operationsManagement.activiti.ActivitiService;
import com.hfkj.bbt.operationsManagement.service.IOperationsService;
import com.hfkj.bbt.operationsManagement.web.vo.ExceptionVo;
import com.hfkj.bbt.operationsManagement.web.vo.HistoricActivityVo;
import com.hfkj.bbt.operationsManagement.web.vo.OperationVo;
import com.hfkj.bbt.operationsManagement.web.vo.PendingVo;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class OperationsServiceImpl implements IOperationsService {

    @Autowired
    private OperationsDao operationsDao;
    @Autowired
    private ObjectDao objectDao;
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private GradeDao gradeDao;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccessoryDao accessoryDao;
    @Autowired
    private DevelopTypeDao developTypeDao;
    @Autowired
    private RoleToUserDao roleToUserDao;
    /**
     * 根据用户id查询待处理运维单
     *
     * @return
     */
    public List<OperationVo> selectOperationsPending(String schoolCode,String roleName,Long userId) {
        List<OperationVo> processTask = activitiService.getProcessTask(schoolCode,roleName,userId);
        if (ComUtil.listNotNullAndEmpty(processTask)){
            for (OperationVo vo:processTask){
                String processId = vo.getProcessId();
                Operations operations = operationsDao.getOpertionbyProcessId(processId);
                School school = schoolDao.getSchool(operations.getSchoolCode());
                vo.setOperateDate(DateUtil.tranDateToString("yyyy-MM-dd",operations.getStartDate()));
                vo.setOperateSchool(school.getName());
            }
        }
        return processTask;
    }

    /**
     * 查询我的待处理异常
     *
     * @return
     * @param toPage
     * @param pageSize
     */
    @Override
    public Page selectOperationsMyList(Integer toPage, Integer pageSize,Integer state,String schoolCode) {
        if (state==2){
            return operationsDao.selectAllExceptionList(toPage,pageSize,schoolCode);
        }
        return operationsDao.selectCompleteExcpetionList(toPage,pageSize,schoolCode);
    }


    /**
     * 新增手动异常
     *
     * @param vo
     */
    @Override
    @Transactional(readOnly = false)
    public void addHandException(ExceptionVo vo) {
        User currentUser = UserUtil.getCurrentUser();
        Operations operations = new Operations();
        operations.setExceptionType(vo.getExceptionType());
        operations.setExceptionDescription(vo.getExceptionDescription());
        operations.setSchoolCode(currentUser.getSchoolCode());
        operations.setSubmitUser(currentUser.getId());
        operations.setClassId(vo.getClassId());
        operations.setStartDate(new Date());
        operations.setBirthType("hand");
        //启动流程实例 指定的任务人 需前端保证有人员ID
        String designManagers = getDesignManagers(vo.getSchoolCode());
        Map<String, Object> values = new HashMap<String, Object>();
        //选择上报给谁
        values.put(Operations.CHOOSEMANAGERS, designManagers);
        //最终确认人
        values.put(Operations.SCHOOLMANAGER, currentUser.getId());

        String processId = activitiService.startHandException(values, Operations.HANDEXCEPTIONKEY);
        operations.setProcessId(processId);
        operationsDao.save(operations);

    }

    /**
     * 查询详细流程 及流程历史
     *
     * @param processId
     * @param taskId
     */
    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> lookProcessDetailHis(String processId, String taskId) {
        //首先接受任务
        Operations operation = operationsDao.getOpertionbyProcessId(processId);
        if ("hand".equals(operation.getBirthType())){
            activitiService.acceptTask(taskId);
        }
        if ("auto".equals(operation.getBirthType())){
            activitiService.acceptAutoTask(taskId);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Task myTask = activitiService.isMyTask(processId);
        map.put("isMyTask", myTask);
        if (myTask!=null){
            map.put("isQueren",myTask.getName());
            map.put("taskId",myTask.getId());
        }
        List<HistoricActivityVo> historyByProcessInstanceId =
                activitiService.getHistoryByProcessInstanceId(processId);
        map.put("opertions", operation);
        map.put("school", schoolDao.getSchool(operation.getSchoolCode()));
        if (operation != null && operation.getClassId() != null) {
            map.put("classes", classDao.getClass(operation.getClassId()));
            Classes classess = classDao.getClass(operation.getClassId());
            map.put("grade", gradeDao.getGrades(classess.getGradeId()));

            //确认是哪台设备
            Long roomId = classess.getRoomId();
            if (roomId!=null){
                List<Accessory> accessoryByRoom = accessoryDao.findAccessoryByRoom(roomId);
                map.put("accessorys",accessoryByRoom);
                List<DevelopType> develops = developTypeDao.getDevelops();
                map.put("develops",develops);
            }

        }
        Long userId = operation.getSubmitUser();
        if (userId!=null){
            map.put("submitUserName",userDao.getUserById(userId).getRealName());
        }

        map.put("activitiHis", historyByProcessInstanceId);
        return map;
    }

    /**
     * 执行对手动和自动异常的处理
     *
     * @param vo
     */
    @Override
    @Transactional(readOnly = false)
    public void doCompleteHandTask(PendingVo vo) {
        if ("auto".equalsIgnoreCase(vo.getBirthType())){
            String ManagerIds = objectDao.getCompanyUserAdmin(vo.getPendingCompany());
            Map<String, Object> values = new HashMap<String, Object>();
            values.put(Operations.CHOOSE, vo.getPendingType());
            values.put(Operations.CHOOSEMANAGERS, ManagerIds);
            activitiService.doCompleteTask(vo.getTaskId(), values, vo.getProcessId(), vo.getApproval());
        }

        if ("hand".equalsIgnoreCase(vo.getBirthType())){
            activitiService.doCompleteTask(vo.getTaskId(), vo.getProcessId(), vo.getApproval());
        }
        if ("scrap".equalsIgnoreCase(vo.getBirthType())){
            activitiService.doCompleteTask(vo.getTaskId(), vo.getProcessId(), vo.getApproval());
        }
        Long accessoryId = vo.getAccessoryId();
        if (accessoryId!=null){
            Accessory accessory = accessoryDao.findAccessoryById(accessoryId);
            Integer repairTimes = accessory.getRepairTimes()==null?0:accessory.getRepairTimes();
            accessory.setRepairTimes(repairTimes+1);
            accessoryDao.update(accessory);
        }


    }

    /**
     * 根据单位查询审批人
     *
     * @param schoolCode
     * @return
     */
    private String getDesignManagers(String schoolCode) {
        return objectDao.getCompanyUserAdmin(schoolCode);
    }


    /**
     * 运维首页显示（图）
     * @return
     */
    public List getOperations(){
        return operationsDao.getOperations();
    }

    /**
     * 申请报废流程
     * @param accessoryId
     * @param classId
     */
    @Override
    @Transactional(readOnly = false)
    public void applicationForScrap(Long accessoryId, Long classId) {
        User currentUser = UserUtil.getCurrentUser();
        Operations operations = new Operations();
        operations.setExceptionType("申请报废设备");
        operations.setExceptionDescription("申请报废设备");
        operations.setSchoolCode(currentUser.getSchoolCode());
        operations.setSubmitUser(currentUser.getId());
        operations.setClassId(classId);
        operations.setStartDate(new Date());
        operations.setBirthType("scrap");
        operations.setAccessoryId(accessoryId);
        //判断是否已经申请过报废


        //启动流程实例 指定的任务人 需前端保证有人员ID  //教委
        String designManagers = roleToUserDao.findEdutionUser();
        Map<String, Object> values = new HashMap<String, Object>();
        //选择上报给教委
        values.put(Operations.EDUATIONMANAGER, designManagers);

        String processId = activitiService.startHandException(values, Operations.SCRAPKEY);
        operations.setProcessId(processId);
        operationsDao.save(operations);
    }


    /**
     * 是否能申请报废
     * @param accessoryId
     * @return
     */
    public boolean isCanScrap(Long accessoryId){
        List<Operations> accessory = operationsDao.getAccessory(accessoryId);
        if (ComUtil.listNotNullAndEmpty(accessory)){
            return false;
        }
        return true;
    }

}














