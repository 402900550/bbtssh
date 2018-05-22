package com.hfkj.bbt.application.web;

import com.hfkj.bbt.application.service.IApplicationService;
import com.hfkj.bbt.application.web.vo.ApplicationVo;
import com.hfkj.bbt.application.web.vo.ControlVo;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
@Controller
@RequestMapping(value = "application")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 应用监测
     *
     * @param applicationVo
     * @return
     */
    @RequestMapping(value = "selectEquipmenDistinctSchoolList")
    public ModelAndView selectEquipmenDistinctSchoolList(ApplicationVo applicationVo) {
        applicationVo.check();
        ModelAndView mav = new ModelAndView("applicationMonitoring/applicationMainList");
        mav.addObject("page", applicationService.selectDistinctSchoolList(applicationVo));
        return mav;
    }

    /**
     * 设备使用记录查询
     * @param applicationVo
     * @return
     */
    @RequestMapping(value = "selectUsedRecord")
    public ModelAndView selectUsedRecord(ApplicationVo applicationVo){
        applicationVo.check();
        ModelAndView mav = new ModelAndView("applicationMonitoring/usedRecordList");
        mav.addObject("page", applicationService.selectUsedRecord(applicationVo));
        return mav;
    }



    /**
     * 单个学校检测数据
     *
     * @param applicationVo
     * @return
     */
    @RequestMapping(value = "selectEquipmentSingleSchoolList")
    public ModelAndView selectEquipmentSingleSchoolList(ApplicationVo applicationVo) {
        applicationVo.check();
        ModelAndView mav = new ModelAndView("applicationMonitoring/schoolRealTimeList");
        mav.addObject("page", applicationService.selectEquipmentSingleSchoolList(applicationVo));
        return mav;
    }


    /**
     * 查询资产总额统计图
     *
     * @return
     */
    @RequestMapping(value = "loadEquimentSchoolList", method = RequestMethod.POST)
    @ResponseBody
    public Page loadEquimentSchoolLists(ApplicationVo applicationVo) {
        applicationVo.setSchoolType(10086);
        applicationVo.check();
        return applicationService.selectDistinctSchoolList(applicationVo);
    }

    @RequestMapping(value = "forwardSchoolDetail")
    public ModelAndView forwardSchoolDetail(String schoolCode) {
        ModelAndView modelAndView = new ModelAndView("applicationMonitoring/applicationSchoolRealTime");
        modelAndView.addObject("theSchool", schoolService.getSchool(schoolCode));
        return modelAndView;
    }


    /**
     * 电控
     *
     * @return
     */
    @RequestMapping(value = "doControlOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity doControlOrder(ControlVo vo) {

        return applicationService.doControlOrder(vo);

    }


    /**
     * 跳转环境检测
     *
     * @param schoolCode
     * @return
     */
    @RequestMapping(value = "forwardEnvironmentList")
    public ModelAndView forwardEnvironmentList(String schoolCode) {
        ModelAndView mav = new ModelAndView("applicationMonitoring/environment");
        mav.addObject("schoolName", schoolService.getSchool(schoolCode));
        mav.addObject("schoolCode", schoolCode);
        return mav;
    }

    /**
     * 跳转环境检测
     *
     * @param applicationVo
     * @return
     */
    @RequestMapping(value = "lodeEnvironmentList")
    public ModelAndView lodeEnvironmentList(ApplicationVo applicationVo) {
        applicationVo.check();
        ModelAndView mav = new ModelAndView("applicationMonitoring/environmentList");
        mav.addObject("page", applicationService.selectEnvironmentGradeClassList(applicationVo));
        return mav;
    }

    /**
     * 应用监测(手机)
     *
     * @param applicationVo
     * @return
     */
    @RequestMapping(value = "selectEquipmenDistinctSchoolListMobile")
    @ResponseBody
    public Page selectEquipmenDistinctSchoolListMobile(ApplicationVo applicationVo) {
        applicationVo.check();
        return applicationService.selectDistinctSchoolList(applicationVo);
    }

    /**
     * 单个学校检测数据(手机)
     *
     * @param applicationVo
     * @return
     */
    @RequestMapping(value = "selectEquipmentSingleSchoolListMebile")
    @ResponseBody
    public Page selectEquipmentSingleSchoolListMebile(ApplicationVo applicationVo) {
        applicationVo.check();
        return applicationService.selectEquipmentSingleSchoolList(applicationVo);
    }

    /**
     * 跳转作息时间设备使用情况
     * @return
     */
    @RequestMapping(value = "forwardGradeTimeTable")
    public ModelAndView forwardGradeTimeTable(String gradeId,String classId,String schoolCode) {
        ModelAndView mav = new ModelAndView("applicationMonitoring/timeTable");
        if(!gradeId.equals("")&&!classId.equals("")) {
            mav.addObject("page", applicationService.getWorkSchedule(Long.valueOf(gradeId), Long.valueOf(classId), schoolCode));
            mav.addObject("page1", applicationService.getWorkSchedule(Long.valueOf(gradeId), Long.valueOf(classId), schoolCode).get(0));
            mav.addObject("scheduleClass", applicationService.getWorkScheduleClassUser(Long.valueOf(gradeId), Long.valueOf(classId), schoolCode));
        }
        return mav;
    }



}


















