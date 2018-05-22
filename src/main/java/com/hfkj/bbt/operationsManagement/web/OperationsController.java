package com.hfkj.bbt.operationsManagement.web;

import com.hfkj.bbt.base.entity.Classes;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.operationsManagement.service.IOperationsService;
import com.hfkj.bbt.operationsManagement.web.vo.ExceptionVo;
import com.hfkj.bbt.operationsManagement.web.vo.PendingVo;
import com.hfkj.bbt.systemanage.service.IClassService;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
@Controller
@RequestMapping(value = "operation")
public class OperationsController {

    @Autowired
    private IOperationsService operationsService;

    @Autowired
    private IClassService classService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 待处理异常查询
     * @return
     */
    @RequestMapping(value = "pendingExceptionList")
    public ModelAndView pendingExceptionList() {
        ModelAndView mav = new ModelAndView("operationsManagement/pendingExceptionList");
        User currentUser = UserUtil.getCurrentUser();
        Role currentMostHeighRole = UserUtil.getCurrentMostHeighRole();
        mav.addObject("theOperation",operationsService.selectOperationsPending(currentUser.getSchoolCode(),
                currentMostHeighRole.getRoleName(),currentUser.getId()));
        return mav;
    }

    /**
     * 所有的异常查询
     * @return
     */
    @RequestMapping(value = "completeOrAllExceptionList",method = RequestMethod.POST)
    public ModelAndView completeOrAllExceptionList(Integer toPage,Integer pageSize,Integer state) {
        ModelAndView mav = new ModelAndView("operationsManagement/completeAllExceptionList");
        mav.addObject("theOperation",operationsService
                .selectOperationsMyList(toPage,pageSize,state,UserUtil.getCurrentUser().getSchoolCode()));
        return mav;
    }


    /**
     * 跳转运维详情页面
     * @param operationId
     * @return
     */
    @RequestMapping(value = "forwardOperationList")
    public ModelAndView forwardOperationList(String operationId){
        ModelAndView mav = new ModelAndView("operationsManagement/process");
        mav.addObject("operationId",operationId);
        return mav;
    }

    /**
     * 添加异常弹框
     * @return
     */
    @RequestMapping(value ="addHandExceptionDialog" )
    public ModelAndView addHandExceptionDialog(){
        ModelAndView mav = new ModelAndView("operationsManagement/addExceptionDialog");
        User currentUser = UserUtil.getCurrentUser();
        String schoolCode = currentUser.getSchoolCode();
        if (ComUtil.stringIsNotNull(schoolCode)){
            mav.addObject("grades",classService.getGradeList(schoolCode));
        }
        mav.addObject("schools",schoolService.getSchoolByType(11));
        return mav;
    }


    /**
     * 提交运维单
     * @param vo
     * @return
     */
    @RequestMapping(value = "doAddHandException",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addHandException(ExceptionVo vo){
        operationsService.addHandException(vo);
        return ResponseEntity.isOk("新增成功!");
    }

    /**
     * 查询班级
     * @param gradeId
     * @return
     */
    @RequestMapping(value = "getClassByGradesException")
    @ResponseBody
    public List<Classes> getClassByGradesException(Long gradeId){
        String schoolCode = UserUtil.getCurrentUser().getSchoolCode();
        return classService.getClassesByGrade(schoolCode,gradeId);
    }


    /**
     * 查看流程详情
     * @param processId
     * @param type
     * @return
     */
    @RequestMapping(value = "lookDetailProcess")
    public ModelAndView lookDetailProcess(String processId,String type,String taskId){
        ModelAndView modelAndView=new ModelAndView("operationsManagement/process");
        modelAndView.addAllObjects(operationsService.lookProcessDetailHis(processId,taskId ));
        modelAndView.addObject("types",type);
        modelAndView.addObject("schools",schoolService.getSchoolByType(11));

        return modelAndView;
    }



    /**
     * 管理员处理
     * @return
     */
    @RequestMapping(value = "submitAdvice",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity submitAdvice(PendingVo vo){

        operationsService.doCompleteHandTask(vo);

        return ResponseEntity.isOk("提交成功!");
    }


    /**
     * 首页运维管理（图）
     * @return
     */
    @RequestMapping(value = "selectGetOperations")
    @ResponseBody
    public List selectGetOperations(){
        return operationsService.getOperations();
    }


    /**
     * 提交报废流程
     * @param accessoryId
     * @return
     */
    @RequestMapping(value = "applicationForScrap",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity applicationForScrap(Long accessoryId,Long classId){
        boolean canScrap = operationsService.isCanScrap(accessoryId);
        if (canScrap){
            operationsService.applicationForScrap(accessoryId,classId);
            return ResponseEntity.isOk("申请成功!等待教委响应!");
        }else {
            return ResponseEntity.isOk("已申请过报废!请勿重复申请!");
        }
    }






}
