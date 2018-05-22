package com.hfkj.bbt.base.service.impl;

import com.hfkj.bbt.base.dao.SchoolTypeDao;
import com.hfkj.bbt.base.entity.CityArea;
import com.hfkj.bbt.base.entity.School;
import com.hfkj.bbt.base.entity.SchoolType;
import com.hfkj.bbt.base.service.ICommonService;
import com.hfkj.bbt.base.dao.CityAreaDao;
import com.hfkj.bbt.base.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017-10-24.
 */
@Service
public class CommonServiceImpl implements ICommonService{

    @Autowired
    private SchoolTypeDao schoolTypeDao;

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private CityAreaDao cityAreaDao;

    public List<SchoolType> getAllSchoolType(){
        return schoolTypeDao.getSchoolTypes();
    }

    @Override
    public List<School> getAllSchool() {
        return schoolDao.getAll();
    }

    @Override
    public List<CityArea> getAllCityArea() {
        return cityAreaDao.getAllCityArea();
    }

    //模糊查询学校
    @Override
    public List<School> getAllSchoolByLikeName(String schoolName){

        return schoolDao.getSchoolByLikeName(schoolName);
    }
}
