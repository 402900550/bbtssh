package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.BuildingDao;
import com.hfkj.bbt.base.dao.ClassDao;
import com.hfkj.bbt.base.entity.Classes;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.School;
import com.hfkj.bbt.base.entity.TeachBuilding;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.dao.SystemanageDao;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.ISchoolService;
import com.hfkj.bbt.systemanage.web.vo.SchoolVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import com.hfkj.bbt.base.dao.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/25 0025.
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private SystemanageDao systemanageDao;

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private ClassDao classDao;

    @Override
    public Page selectSchoolList(SearchVo searchVo) {
        return systemanageDao.selectSchoolList(searchVo);
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity deleteSchool(Long schoolId) {
        School school = schoolDao.getSchool(schoolId);
        List<TeachBuilding> buildingBySchool = buildingDao.findBuildingBySchool(school.getSchoolCode());
        List<Classes> classAndSchool = classDao.getClassAndSchool(school.getSchoolCode());
        if (ComUtil.listNotNullAndEmpty(buildingBySchool)||ComUtil.listNotNullAndEmpty(classAndSchool)){
            return ResponseEntity.isFail("请先删除学校的班级和教学楼!");
        }else {
            schoolDao.deleteSchool(schoolId);
            return ResponseEntity.isOk("删除成功!");
        }

    }

    /**
     * 更新学校
     * @param schoolVo
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity updateSchool(SchoolVo schoolVo) {

        School school = schoolDao.getSchool(schoolVo.getSchoolId());
        if (school == null) {
            return ResponseEntity.isFail("学校不存在!");
        }

        if (isRepeat(schoolVo.getSchoolId(),schoolVo.getSchoolCode())){
            return ResponseEntity.isFail("学校单位代码重复!");
        }

        school.setName(schoolVo.getSchoolName());
        school.setSchoolCode(schoolVo.getSchoolCode());
        school.setType(schoolVo.getSchoolType());
        school.setTelephone(schoolVo.getSchoolPhone());
        school.setAddress(schoolVo.getSchoolAddress());
        school.setDescription(schoolVo.getSchoolDescription());
        school.setQxm(schoolVo.getDistinct());
        schoolDao.update(school);
        return ResponseEntity.isOk("修改成功!");
    }

    /**
     * 添加学校
     * @param schoolVo
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public ResponseEntity saveSchool(SchoolVo schoolVo) {

        School school = schoolDao.getSchool(schoolVo.getSchoolCode());
        //如果存在则说明学校代码重复
        if (school!=null){
            return ResponseEntity.isFail("学校单位代码重复!");
        }
        school=new School();
        school.setName(schoolVo.getSchoolName());
        school.setSchoolCode(schoolVo.getSchoolCode());
        school.setType(schoolVo.getSchoolType());
        school.setTelephone(schoolVo.getSchoolPhone());
        school.setAddress(schoolVo.getSchoolAddress());
        school.setDescription(schoolVo.getSchoolDescription());
        school.setQxm(schoolVo.getDistinct());
        school.setStatus(1);
        school.setVersion(100);
        schoolDao.save(school);
        return ResponseEntity.isOk("新增成功!");
    }


    public School getSchool(String schoolCode) {
        return schoolDao.getSchool(schoolCode);
    }

    @Override
    public School getSchool(Long schoolId) {
        return schoolDao.getSchool(schoolId);
    }


    public List<School> getSchool(){
        return schoolDao.getAll();
    }

    @Override
    public List<School> getSchoolByType(int schoolType) {
        return schoolDao.getSchoolBySchoolType(schoolType);
    }


    private boolean isRepeat(Long schoolId,String schoolCode){
        List<School> schoolList = schoolDao.getSchoolListBySchoolCode(schoolCode);
        if (null!=schoolList&&schoolList.size()>1){
            return true;
        }
        if (null!=schoolList&&schoolList.size()==1){
            School school = schoolList.get(0);
            if (school.getId()==schoolId){
                return false;
            }else {
                return true;
            }

        }
        School school = schoolDao.getSchool(schoolCode);
        if (school==null){
            return false;
        }
        return true;
    }

    @Override
    public School getSchoolByName(String schoolName) {
        return schoolDao.getSchoolByName(schoolName);
    }





}















