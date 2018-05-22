package com.hfkj.bbt.base.web;

import com.hfkj.bbt.application.service.IApplicationService;
import com.hfkj.bbt.assetmanage.service.IEquipmentService;
import com.hfkj.bbt.base.entity.Grade;
import com.hfkj.bbt.base.entity.User;
import com.hfkj.bbt.base.entity.WorkSchedule;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.MyProperties;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.dataAnalysis.service.IDataAnalysis;
import com.hfkj.bbt.dataAnalysis.web.DataAnalysisController;
import com.hfkj.bbt.dataAnalysis.web.vo.DataAnalysisSearchVo;
import com.hfkj.bbt.systemanage.service.IClassroomService;
import com.hfkj.bbt.systemanage.service.IOtherService;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.util.List;
import java.util.Properties;


/**
 * Created by Administrator on 2017-10-09.
 */
@Controller
public class IndexController {

    @Autowired
    private IOtherService otherService;
    @Autowired
    private IClassroomService classroomService;
    @Autowired
    private ISchoolService schoolService;
    @Autowired
    private IEquipmentService equipmentService;
    @Autowired
    private IApplicationService applicationService;
    @Autowired
    private IDataAnalysis dataAnalysis;

    @RequestMapping(value = "index")
    public ModelAndView forwardIndex() {
        ModelAndView mav = new ModelAndView("index");
        int qxmId=500106;
        //资产数据
        List list = applicationService.LoadClassRoomEQuiMentSchool(qxmId);
        if(ComUtil.listNotNullAndEmpty(list)){
            mav.addObject("page1",list.get(0));
        }
        //完好率
        List list1 = applicationService.LoadClassRoomEQuiMent(qxmId);
        if(ComUtil.listNotNullAndEmpty(list1)){
            mav.addObject("page2",list1.get(0));
        }
        //使用率
        List list2 = dataAnalysis.loadDataAnalysisCityarea(qxmId);
        if(ComUtil.listNotNullAndEmpty(list2)){
            mav.addObject("theEmploy",list2.get(0));
        }
        return mav;
    }

    /**
     * 首页排名排序
     * @param ending
     * @return
     */
    @RequestMapping(value = "dataAnalysisList")
    public ModelAndView getDataAnalysisOder(String ending,String type){
        ModelAndView mav = new ModelAndView("dataAnalyList");
        DataAnalysisSearchVo dataAnalysisSearchVo = new DataAnalysisSearchVo();
        dataAnalysisSearchVo.check();
        dataAnalysisSearchVo.setSizeNumber(999);
        dataAnalysisSearchVo.setStartNumber(1);
        dataAnalysisSearchVo.setEnding(ending);
        mav.addObject("theSchool",dataAnalysis.selectEqSchool(dataAnalysisSearchVo));
        mav.addObject("theSubject",dataAnalysis.selectEqSubject(dataAnalysisSearchVo));
        mav.addObject("theTeacher",dataAnalysis.selectEqTeacher(dataAnalysisSearchVo));
        mav.addObject("type",type);
        return mav;
    }


    /**
     * 资产图表数据
     * @return
     */
    @RequestMapping(value = "indexChartDataToRuntime",method = RequestMethod.POST)
    @ResponseBody
    public List indexChartDataToRuntime(){
        int qxmId=500106;
        return applicationService.loadEquipmentSingle(qxmId);
    }


    /**
     * 资产图表数据
     * @return
     */
    @RequestMapping(value = "indexChartData",method = RequestMethod.POST)
    @ResponseBody
    public List indexChartData(){
        int qxmId=500106;
        return applicationService.loadChartDataAsset(qxmId);
    }


    @RequestMapping(value = "forward/forwardByUrl")
    public ModelAndView forwardApplicationMain(String forwardUrl,String type)  {
        ModelAndView modelAndView = new ModelAndView(forwardUrl);
        if("equipmentInput".equalsIgnoreCase(type)){
            addParameter(modelAndView);
        }

        if ("parameter".equalsIgnoreCase(type)){
            loadParameterType(modelAndView);
        }

        if("equipmentCount".equalsIgnoreCase(type)){
            loadEquimentAndClassRoom(modelAndView);
        }
        if ("schedule".equalsIgnoreCase(type)){
            loadSchedule(modelAndView);

        }

        return modelAndView;
    }

    //获取添加前数据库的数据
    private void addParameter(ModelAndView mav)  {

        User user = UserUtil.getCurrentUser();
        List<Grade> grade = classroomService.selectGrade();

        //查询教学楼
        mav.addObject("teacherBuilding",otherService.findBuildingBySchoolId(user.getSchoolCode()));
        //所属学校
        mav.addObject("school",schoolService.getSchool(user.getSchoolCode()));
        mav.addObject("allSchool",schoolService.getSchool());
        //配备表
        mav.addObject("develops",otherService.getDevelop());

        //查询的是品牌
        mav.addObject("pinpai",otherService.getParametersByTypeId(1L));
        //查询的是规格型号
        mav.addObject("guigexinghao",otherService.getParametersByTypeId(2L));
    }

    private void loadParameterType(ModelAndView mav){
        mav.addObject("parameterTypes",otherService.getAllParameterType());
    }

    private Long getLongValue(String name) throws IOException {
        MyProperties pro=new MyProperties();
        Properties properties = pro.getProperties();
        return Long.valueOf(properties.getProperty(name));
    }

    /**
     * 查询设备
     * @param mav
     */
    public void loadEquimentAndClassRoom(ModelAndView mav){
        mav.addObject("equimentNoALLSchool",equipmentService.loadEquimentAndClassRoom());
    }

    public void loadSchedule(ModelAndView modelAndView){
        String schoolCode = UserUtil.getCurrentUser().getSchoolCode();
        if (ComUtil.stringIsNotNull(schoolCode)){

            List grades=otherService.getGradeBySchoolCode(schoolCode);
            modelAndView.addObject("grades",grades);

            modelAndView.addObject("school",schoolService.getSchool(schoolCode));
        }
    }

    @RequestMapping(value = "environmentList")
    public ModelAndView forwardIndexEnvironment(String qxmId){
        ModelAndView mav = new ModelAndView("indexEnvironment");
        //环境监测
        List list = applicationService.loadEnvironmentIndex(qxmId);
        if (ComUtil.listNotNullAndEmpty(list)){
            mav.addObject("environment",applicationService.loadEnvironmentIndex(qxmId).get(0));
        }
        mav.addObject("qxmId",qxmId);
        return mav;
    }

}
