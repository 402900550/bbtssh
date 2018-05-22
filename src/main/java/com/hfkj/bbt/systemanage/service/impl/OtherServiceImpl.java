package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.systemanage.service.IOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
@Service
public class OtherServiceImpl implements IOtherService {

    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private SystemanageDao systemanageDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private ParametersDao parametersDao;
    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private ParameterTypeDao parameterTypeDao;

    @Autowired
    private DevelopTypeDao developTypeDao;

    @Autowired
    private ScheduleDao scheduleDao;
    /**
     * 查询类型
     *
     * @return
     */
    public List<Parameters> getParametersByTypeId(Long typeId) {
        return parametersDao.getParametersByTypeId(typeId);
    }

    @Override
    public Parameters getParametersById(Long id) {
        return parametersDao.getParametersById(id);
    }


    @Override
    public List<DevelopType> getDevelop() {
        return developTypeDao.getDevelops();
    }

    @Override
    public List<DevelopType> getDevelop(Long typeLong) {
        return developTypeDao.getDevelops(typeLong);
    }

    @Override
    public List getSubjectName() {
        return subjectDao.getSubjectName();
    }

    @Override
    public List selectRoleId(Long userId) {
        return systemanageDao.selectRoleList(userId);
    }

    @Override
    public List getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public List<Equipment> getEquipmentByNoAndType(String schoolCode, Integer type) {
        return equipmentDao.findEquipment(schoolCode, type);
    }


    @Override
    public List<Equipment> findEquipment(String schoolCode) {
        return equipmentDao.findEquipment(schoolCode);
    }

    @Override
    public Equipment findEquipmentByNo(String equipmentNo) {
        return equipmentDao.findEquipmentByNo(equipmentNo);
    }

    @Override
    public List<TeachBuilding> findBuildingBySchoolId(String schoolCode) {
        return buildingDao.findBuildingBySchoolId(schoolCode);
    }

    @Override
    public List<ParameterType> getAllParameterType() {
        return parameterTypeDao.getAllType();
    }



    @Override
    public List getGradeBySchoolCode(String schoolCode) {
        return systemanageDao.selectGradeBySchoolCodeToSchedule(schoolCode);
    }
}
