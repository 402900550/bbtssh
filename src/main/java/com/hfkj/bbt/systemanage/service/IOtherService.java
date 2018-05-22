package com.hfkj.bbt.systemanage.service;

import com.hfkj.bbt.base.entity.*;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
public interface IOtherService {


    List<DevelopType> getDevelop();
    List<DevelopType> getDevelop(Long typeLong);
    /**
     * 查询课程表
     * @return
     */
    List getSubjectName();

    /**
     * 查询角色ID
     * @param userId
     * @return
     */
    List selectRoleId(Long userId);

    /**
     * 查询角色表
     * @return
     */
    List getAllRoles();



    List<Equipment> getEquipmentByNoAndType(String schoolCode, Integer type);


    List<Equipment> findEquipment(String schoolCode);

    Equipment findEquipmentByNo(String equipmentNo);

    List<Parameters> getParametersByTypeId(Long typeId);

    Parameters getParametersById(Long id);
    //根据学校id查询教学楼
    List<TeachBuilding> findBuildingBySchoolId(String schoolCode);

    /**
     * 得到所有参数类型
     * @return
     */
    List<ParameterType> getAllParameterType();

    List getGradeBySchoolCode(String schoolCode);
}
