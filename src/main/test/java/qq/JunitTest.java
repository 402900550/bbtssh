package qq;

import com.hfkj.bbt.base.dao.OperationsDao;
import com.hfkj.bbt.base.dao.RoleToUserDao;
import com.hfkj.bbt.base.entity.Operations;
import com.hfkj.bbt.base.entity.RoleToUser;
import com.hfkj.bbt.base.util.DateUtil;
import com.hfkj.bbt.operationsManagement.activiti.ActivitiService;
import com.hfkj.bbt.task.service.BbtTaskService;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.activiti.engine.task.Task;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context/*.xml",
        "classpath:db/edu-db.xml",  "classpath:activiti/activiti.cfg.xml"
        })
public class JunitTest {

    /**
     * 使用配置文件创建23张表
     * */
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private BbtTaskService bbtTaskService;

    @Autowired
    private RoleToUserDao dao ;

    @Autowired
    private OperationsDao operationsDao;

    @Autowired
    private ActivitiService activitiService;

    @Test
    public void test1(){
        Deployment deployment = repositoryService//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("自动异常处理程序程序")//添加部署的名称
                .addClasspathResource("activiti/diagram/testHellow.bpmn")//从classpath的资源中加载，一次只能加载一个文件
                .addClasspathResource("activiti/diagram/testHellow.png")//从classpath的资源中加载，一次只能加载一个文件
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署名称："+deployment.getName());//helloworld入门程序
    }

    /**启动流程实例*/
    @Test
    public void startProcessInstance(){
        //流程定义的key
//        String processDefinitionKey = "ExceptionAuto";
//        ProcessInstance pi = runtimeService//与正在执行的流程实例和执行对象相关的Service
//                .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
//        System.out.println("流程实例ID:"+pi.getId());//流程实例ID    101
//        System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID   helloworld:1:4
//        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list();
//        for (HistoricActivityInstance hi:list){
//            System.out.println(hi.getActivityName()+""+hi.getAssignee());
//
//        }
        repositoryService.deleteDeployment("82501",true);
        System.out.print("删除成功");
    }


    /**查询当前人的个人任务*/
    @Test
    public void findMyPersonalTask(){
//        String assignee = "张三";
        String assignee = "李四";
        List<Task> list = taskService//与正在执行的任务管理相关的Service
                .createTaskQuery()//创建任务查询对象
                .taskAssignee(assignee)//指定个人任务查询，指定办理人
                .list();
        if(list!=null && list.size()>0){
            for(Task task:list){
                System.out.println("任务ID:"+task.getId());
                System.out.println("任务名称:"+task.getName());
                System.out.println("任务的创建时间:"+task.getCreateTime());
                System.out.println("任务的办理人:"+task.getAssignee());
                System.out.println("流程实例ID："+task.getProcessInstanceId());
                System.out.println("执行对象ID:"+task.getExecutionId());
                System.out.println("流程定义ID:"+task.getProcessDefinitionId());
                System.out.println("########################################################");
            }
        }
    }

    /**完成我的任务*/
    @Test
    public void completeMyPersonalTask(){
        //任务ID
        String taskId = "192526";
        taskService.claim("192526","27");
        taskService//与正在执行的任务管理相关的Service
                .complete(taskId);
        System.out.println("完成任务：任务ID："+taskId);






    }

    @Test
    public void dodododo(){
        bbtTaskService.workOperationsEquipment();
    }


}

















