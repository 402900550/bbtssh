package com.hfkj.bbt.dataAnalysis.service;

import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.dataAnalysis.web.vo.DataAnalysisSearchVo;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
public interface IDataAnalysis {

    /**
     * 查询地区的完好率情况
     * @param qxmId
     * @return
     */
    Page loadDataAnalysisSchoolList(String qxmId);


    /**
     * 所有学校的排名
     * @param dataAnalysisSearchVo
     * @return
     */
    Page selectEqSchool(DataAnalysisSearchVo dataAnalysisSearchVo);

    /**
     * 所有教师排名
     * @param dataAnalysisSearchVo
     * @return
     */
    Page selectEqTeacher(DataAnalysisSearchVo dataAnalysisSearchVo);

    /**
     *所有班级排名
     * @param dataAnalysisSearchVo
     * @return
     */
    Page selectEqClass(DataAnalysisSearchVo dataAnalysisSearchVo);

    /**
     * 所有科目排名
     * @param dataAnalysisSearchVo
     * @return
     */
    Page selectEqSubject(DataAnalysisSearchVo dataAnalysisSearchVo);

    /**
     * 查询地区的使用情况
     * @param qxmId
     * @return
     */
    Page loadDataAnalysisCityareaList(String qxmId);

    /**
     * 查询地区的完好率情况
     * @param schoolCode
     * @return
     */
    Page loadDataAnalysisSchool(String schoolCode);

    /**
     * 查询学校的使用情况
     * @param schoolCode
     * @return
     */
    Page loadDataAnalysisSchoolCityarea(String schoolCode);

    /**
     * 查询每年每月每天设备使用统计图
     * @param newDate
     * @param schoolCode
     * @return
     */
    List loadDataAnalysisSchoolList(String newDate, String schoolCode);

    /**
     * 查询地区的使用情况(首页)
     * @param qxmId
     * @return
     */
    List loadDataAnalysisCityarea(int qxmId);
}
