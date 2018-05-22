package com.hfkj.bbt.systemanage.web;


import com.alibaba.fastjson.JSONObject;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.School;
import com.hfkj.bbt.base.entity.TeachBuilding;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.IBuildingService;
import com.hfkj.bbt.systemanage.service.IOtherService;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by Administrator on 2017-10-09.
 */
@Controller
@RequestMapping(value = "/building")
public class BuildingController {



    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private ISchoolService schoolService;



    /**
     * 跳转到教学楼管理页面
     */
    @RequestMapping(value = "forwardBuildingManage")
    public ModelAndView forwardBuildingManage(String schoolCode){
        ModelAndView mav=new ModelAndView("systemManage/building");
        mav.addObject("modifySchoolCode",schoolCode);
        return mav;
    }


    @RequestMapping(value = "forwardClassroomManage")
    public ModelAndView forwardClassroomManage(Long buildingId){
        ModelAndView modelAndView=new ModelAndView("systemManage/classroom");
        TeachBuilding buildingById = buildingService.getBuildingById(buildingId);
        modelAndView.addObject("currentBuilding",buildingById);
        modelAndView.addObject("currentSchool",schoolService.getSchool(buildingById.getSchoolCode()));
        return modelAndView;
    }


    /**
     * 查询教学楼
     */
    @RequestMapping(value = "selectTeachBuilding")
    public ModelAndView selectTeachBuilding(SearchVo searchVo) {
        School school = schoolService.getSchool(searchVo.getSchoolCode());
        searchVo.check();
        ModelAndView mav=new ModelAndView("systemManage/buildingList");
        mav.addObject("page",buildingService.selectTeachBuildingBySchoolName(searchVo));
        mav.addObject("school",school);
        return mav;
    }


    @RequestMapping(value = "deleteBuilding",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteBuilding(Long buildingId,String operateSchoolCode){
//        if (ComUtil.haveAuthority(operateSchoolCode)){
            return buildingService.deleteBuilding(buildingId);
//        }else {
//            return ResponseEntity.isFail("权限不够!");
//        }

    }


    //修改教学楼名字
    @RequestMapping(value = "doSaveOrUpdateBuilding")
    @ResponseBody
    public ResponseEntity doSaveOrUpdateBuilding(String schoolCode,String buildingName,Long buildingId,String doType) {
//        Boolean b =ComUtil.haveAuthority(schoolCode);
//        if(b) {
            if ("add".equalsIgnoreCase(doType)){
                int r = buildingService.addTeachBuilding(schoolCode,buildingName);
                if(r==1){
                    return ResponseEntity.isOk("添加教学楼成功！");
                }else {
                    return ResponseEntity.isFail("添加失败！网络异常");
                }
            }else {
                //修改教学楼
                int r = buildingService.updateTeachBuilding(buildingId, buildingName);
                if (r == 1) {
                    return ResponseEntity.isOk("修改成功！");
                } else {
                    return ResponseEntity.isFail("修改失败！网络异常");
                }
            }

//        }else {
//            return ResponseEntity.isFail("权限不够!");
//        }
    }



    @RequestMapping(value = "loadBuildingById")
    public ModelAndView loadBuildingById(Long buildingId,String schoolCode){
        ModelAndView modelAndView=new ModelAndView("systemManage/addOrModifyBuilding");
        if (buildingId!=null){
            modelAndView.addObject("currentBuilding",buildingService.getBuildingById(buildingId));
        }
        modelAndView.addObject("currentSchoolCode",schoolCode);
        return modelAndView;
    }


    @RequestMapping(value = "loadBuildingBySchool")
    @ResponseBody
    public List<TeachBuilding> loadBuildingBySchool(String schoolCode){
        return buildingService.getBuildingBySchoolCode(schoolCode);
    }



}

















