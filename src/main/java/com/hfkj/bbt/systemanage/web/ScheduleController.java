package com.hfkj.bbt.systemanage.web;

import com.alibaba.fastjson.JSONArray;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.WorkSchedule;
import com.hfkj.bbt.systemanage.service.IScheduleService;
import com.hfkj.bbt.systemanage.web.vo.WorkScheduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017-12-08.
 */
@Controller
@RequestMapping(value = "schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService scheduleService;

    @RequestMapping(value = "saveOrEditTime",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveOrEditTime(String dataStr){
        List<WorkScheduleVo> workSchedule = JSONArray.parseArray(dataStr, WorkScheduleVo.class);
        return scheduleService.doSaveSchedule(workSchedule);
    }


    @RequestMapping(value = "loadScheduleTableBySchoolAndGrade")
    public ModelAndView loadScheduleTableBySchoolAndGrade(String schoolCode,Long gradeId){
        ModelAndView modelAndView=new ModelAndView("systemManage/scheduleList");
        List<WorkSchedule> mornings = scheduleService.loadWorkSchedule(schoolCode,"morning",gradeId);
        List<WorkSchedule> afternoon = scheduleService.loadWorkSchedule(schoolCode,"afternoon",gradeId);
        List<WorkSchedule> night = scheduleService.loadWorkSchedule(schoolCode,"night",gradeId);
        modelAndView.addObject("morning",mornings);
        modelAndView.addObject("afternoon",afternoon);
        modelAndView.addObject("night",night);
        modelAndView.addObject("schoolCode",schoolCode);
        return modelAndView;
    }


}
