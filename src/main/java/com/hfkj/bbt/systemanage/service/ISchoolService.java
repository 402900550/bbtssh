package com.hfkj.bbt.systemanage.service;

import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.School;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.SchoolVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25 0025.
 */
public interface ISchoolService {


    /**
     * 根据条件查询学校详情
     */
    Page selectSchoolList(SearchVo searchVo);

    /**
     * 删除学校
     * @param schoolId
     */
    ResponseEntity deleteSchool(Long schoolId);

    ResponseEntity updateSchool(SchoolVo schoolVo);

    ResponseEntity saveSchool(SchoolVo schoolVo);

    School getSchool(String schoolCode);

    School getSchool(Long schoolId);

    List<School> getSchool();

    List<School> getSchoolByType(int schoolType);

    School getSchoolByName(String schoolName);

}
