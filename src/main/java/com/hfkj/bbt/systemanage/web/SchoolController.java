package com.hfkj.bbt.systemanage.web;

import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.service.ICommonService;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import com.hfkj.bbt.systemanage.web.vo.SchoolVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/10/25 0025.
 */
@Controller
@RequestMapping(value = "/school")
public class SchoolController {

    private Logger logger = LoggerFactory.getLogger(SchoolController.class);

    @Autowired
    private ISchoolService schoolService;

    @Autowired
    private ICommonService commonService;

    /**
     * 查询学校
     */
    @RequestMapping(value = "selectSchoolList", method = RequestMethod.POST)
    private ModelAndView selectSchoolList(SearchVo searchVo) {
        searchVo.check();
        ModelAndView mav = new ModelAndView("systemManage/schoolList");
        Page page = schoolService.selectSchoolList(searchVo);
        mav.addObject("page", page);
        return mav;
    }

    /**
     * 删除学校
     *
     * @param schoolId
     * @param operateSchoolCode
     * @return
     */
    @RequestMapping(value = "deleteSchool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteSchool(Long schoolId, String operateSchoolCode) {
//        if (ComUtil.haveAuthority(operateSchoolCode)) {
            return schoolService.deleteSchool(schoolId);
//        } else {
//            return ResponseEntity.isFail("权限不够!");
//        }
    }

    /**
     * 加载修改学校的数据
     *
     * @param schoolId
     * @return
     */
    @RequestMapping(value = "loadSchoolById", method = RequestMethod.POST)
    public ModelAndView loadSchoolById(Long schoolId) {
        ModelAndView modelAndView = new ModelAndView("systemManage/addOrModifySchool");
        modelAndView.addObject("thisSchool", schoolService.getSchool(schoolId));
        modelAndView.addObject("schoolType", commonService.getAllSchoolType());
        modelAndView.addObject("distinctList", commonService.getAllCityArea());
        return modelAndView;
    }


    /**
     * 添加或者修改学校
     *
     * @param schoolVo
     */
    @RequestMapping(value = "doSaveOrUpdateSchool", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity doSaveOrUpdateSchool(SchoolVo schoolVo) {
//        if (!ComUtil.haveAuthority(schoolVo.getSchoolCode())) {
//            return ResponseEntity.isFail("没有权限!");
//        }
        if (schoolVo.check()) {

            if ("add".equalsIgnoreCase(schoolVo.getDoType())) {
                return schoolService.saveSchool(schoolVo);
            } else {
                return schoolService.updateSchool(schoolVo);
            }
        }else {
            return ResponseEntity.isFail("请完善数据!");
        }
    }


}


















