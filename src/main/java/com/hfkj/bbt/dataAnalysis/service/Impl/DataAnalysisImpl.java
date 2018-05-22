package com.hfkj.bbt.dataAnalysis.service.Impl;

import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.EquipmentClass;
import com.hfkj.bbt.base.entity.EquipmentSchool;
import com.hfkj.bbt.base.entity.EquipmentTeacher;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.dataAnalysis.service.IDataAnalysis;
import com.hfkj.bbt.dataAnalysis.web.vo.DataAnalysisSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/12/15 0015.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class DataAnalysisImpl implements IDataAnalysis {

    @Autowired
    private AccessoryDao accessoryDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private EquipmentClassDao equipmentClassDao;

    @Autowired
    private EquipmentTeacherDao equipmentTeacherDao;

    @Autowired
    private EquipmentSubjectDao equipmentSubjectDao;

    @Autowired
    private EquipmentSchoolDao equipmentSchoolDao;


    /**
     * 查询地区的完好率情况
     * @param qxmId
     * @return
     */
    public Page loadDataAnalysisSchoolList(String qxmId){
        return accessoryDao.loadDataAnalysisSchoolList(qxmId);
    }

    /**
     * 所有学校的排名
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqSchool(DataAnalysisSearchVo dataAnalysisSearchVo){
        return equipmentClassDao.selectEqSchool(dataAnalysisSearchVo);
    }

    /**
     * 所有教师排名
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqTeacher(DataAnalysisSearchVo dataAnalysisSearchVo){
        return equipmentTeacherDao.selectEqTeacher(dataAnalysisSearchVo);
    }

    /**
     * 所有班级排名
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqClass(DataAnalysisSearchVo dataAnalysisSearchVo){
        return equipmentClassDao.selectEqClass(dataAnalysisSearchVo);
    }

    /**
     * 所有科目排名
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqSubject(DataAnalysisSearchVo dataAnalysisSearchVo){
        return equipmentSubjectDao.selectEqSubject(dataAnalysisSearchVo);
    }

    /**
     * 查询地区的使用情况
     * @param qxmId
     * @return
     */
    public Page loadDataAnalysisCityareaList(String qxmId){
        return equipmentDao.loadDataAnalysisCityareaList(qxmId);
    }

    /**
     * 查询地区的完好率情况
     * @param schoolCode
     * @return
     */
    public Page loadDataAnalysisSchool(String schoolCode){
        return accessoryDao.loadDataAnalysisSchool(schoolCode);
    }

    /**
     * 查询学校的使用情况
     * @param schoolCode
     * @return
     */
    public Page loadDataAnalysisSchoolCityarea(String schoolCode){
        return equipmentDao.loadDataAnalysisSchoolCityarea(schoolCode);
    }

    /**
     * 查询每年每月每天设备使用统计图
     * @param newDate
     * @param schoolCode
     * @return
     */
    public List loadDataAnalysisSchoolList(String newDate, String schoolCode){
        return equipmentSchoolDao.loadDataAnalysisSchoolList(newDate,schoolCode);
    }

    /**
     * 查询地区的使用情况(首页)
     * @param qxmId
     * @return
     */
    public List loadDataAnalysisCityarea(int qxmId){
        return equipmentDao.loadDataAnalysisCityarea(qxmId);
    }
}
