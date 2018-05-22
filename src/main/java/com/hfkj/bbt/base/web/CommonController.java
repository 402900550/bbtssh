package com.hfkj.bbt.base.web;

import com.hfkj.bbt.base.entity.CityArea;
import com.hfkj.bbt.base.entity.School;
import com.hfkj.bbt.base.entity.SchoolType;
import com.hfkj.bbt.base.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017-10-24.
 */
@Controller
@RequestMapping(value = "common")
public class CommonController {

    @Autowired
    private ICommonService commonService;

    /**
     * 加载所有学校类型
     * @return
     */
    @RequestMapping(value = "/loadSchoolType",method = RequestMethod.POST)
    @ResponseBody
    public List<SchoolType> loadSchoolType(){
        return commonService.getAllSchoolType();
    }


    /**
     * 加载查询所有学校
     * @return
     */
    @RequestMapping(value = "/loadAllSchool",method = RequestMethod.POST)
    @ResponseBody
    public List<School> loadAllSchool(){
        return commonService.getAllSchool();
    }

    /**
     * 查询重庆市所有区县
     * @return
     */
    @RequestMapping(value = "/loadAllDistinct",method = RequestMethod.POST)
    @ResponseBody
    public List<CityArea> loadAllDistinct(){
        return commonService.getAllCityArea();
    }

    /**
     * 根据名称模糊查询学校
     * @param schoolName
     * @return
     */
    @RequestMapping(value = "/loadAllSchoolByLikeName",method = RequestMethod.POST)
    @ResponseBody
    public List<School> loadAllSchoolByLikeName(String schoolName){
        return commonService.getAllSchoolByLikeName(schoolName);
    }

}
















