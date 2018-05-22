package com.hfkj.bbt.dataAnalysis.web;

import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.dataAnalysis.service.IDataAnalysis;
import com.hfkj.bbt.dataAnalysis.web.vo.DataAnalysisSearchVo;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
@Controller
@RequestMapping(value = "dataAnalysis")
public class DataAnalysisController {

    @Autowired
    private IDataAnalysis dataAnalysis;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 地区完好率图
     * @return
     */
    @RequestMapping(value = "loadDataAnalysisSchoolList", method = RequestMethod.POST)
    @ResponseBody
    public Page loadDataAnalysisSchoolList() {
        String qxm = "500106";
        return dataAnalysis.loadDataAnalysisSchoolList(qxm);
    }

    /**
     * 地区使用率图
     * @return
     */
    @RequestMapping(value = "loadDataAnalysisCityareaList", method = RequestMethod.POST)
    @ResponseBody
    public Page loadDataAnalysisCityareaList() {
        String qxm = "500106";
        return dataAnalysis.loadDataAnalysisCityareaList(qxm);
    }

    /**
     * 学校完好率图
     * @return
     */
    @RequestMapping(value = "loadDataAnalysisSchool", method = RequestMethod.POST)
    @ResponseBody
    public Page loadDataAnalysisSchool(String schoolCode) {
        return dataAnalysis.loadDataAnalysisSchool(schoolCode);
    }

    /**
     * 学校使用率图
     * @return
     */
    @RequestMapping(value = "loadDataAnalysisSchoolCityarea", method = RequestMethod.POST)
    @ResponseBody
    public Page loadDataAnalysisSchoolCityarea(String schoolCode) {
        return dataAnalysis.loadDataAnalysisSchoolCityarea(schoolCode);
    }

    /**
     * 所有学校排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisSchoolList", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisSchoolList(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisSchool");
        dataAnalysisSearchVo.check();
        mav.addObject("theSchool",dataAnalysis.selectEqSchool(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 所有教师排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisTeacherRanksList", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisTeacherRanksList(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisTeacher");
        dataAnalysisSearchVo.check();
        mav.addObject("theTeacher",dataAnalysis.selectEqTeacher(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 所有班级排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisClassRanksList", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisClassRanksList(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisClass");
        dataAnalysisSearchVo.check();
        mav.addObject("theClass",dataAnalysis.selectEqClass(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 所有科目排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisSubjectRanksList", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisSubjectRanksList(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisSubject");
        dataAnalysisSearchVo.check();
        mav.addObject("theSubject",dataAnalysis.selectEqSubject(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 跳转学校页面
     * @param schoolCode
     * @return
     */
    @RequestMapping(value = "forwardSchoolRanksList")
    public ModelAndView forwardSchoolRanksList(String schoolCode){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataSchool");
        mav.addObject("schoolName",schoolService.getSchool(schoolCode));
        mav.addObject("schoolCode",schoolCode);
        return mav;
    }

    /**
     * 学校教师排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisTeacherRanks", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisTeacherRanks(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisTeacher");
        dataAnalysisSearchVo.check();
        mav.addObject("theTeacher",dataAnalysis.selectEqTeacher(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 学校班级排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisClassRanks", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisClassRanks(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisClass");
        dataAnalysisSearchVo.check();
        mav.addObject("theClass",dataAnalysis.selectEqClass(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 学校科目排名
     * @param dataAnalysisSearchVo
     * @return
     */
    @RequestMapping(value = "selectDataAnalysisSubjectRanks", method = RequestMethod.POST)
    public ModelAndView selectDataAnalysisSubjectRanks(DataAnalysisSearchVo dataAnalysisSearchVo){
        ModelAndView mav = new ModelAndView("dataAnalysis/dataAnalysisSubject");
        dataAnalysisSearchVo.check();
        mav.addObject("theSubject",dataAnalysis.selectEqSubject(dataAnalysisSearchVo));
        return mav;
    }

    /**
     * 学校每年每月每天使用率图
     * @return
     */
    @RequestMapping(value = "loadDataAnalysisSchoolListChart")
    @ResponseBody
    public List loadDataAnalysisSchoolListChart(String newDate, String schoolCode) {
        return dataAnalysis.loadDataAnalysisSchoolList(newDate,schoolCode);
    }


}
