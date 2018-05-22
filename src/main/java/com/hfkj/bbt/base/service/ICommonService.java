package com.hfkj.bbt.base.service;

import com.hfkj.bbt.base.entity.CityArea;
import com.hfkj.bbt.base.entity.School;
import com.hfkj.bbt.base.entity.SchoolType;

import java.util.List;

/**
 * Created by Administrator on 2017-10-24.
 */
public interface ICommonService {

    List<SchoolType> getAllSchoolType();

    List<School> getAllSchool();

    List<CityArea> getAllCityArea();
//模糊查询学校
    List<School> getAllSchoolByLikeName(String schoolName);
}
