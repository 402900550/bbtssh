package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.entity.School;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-10-23.
 */
@Repository
public class SchoolDao extends BaseDao<School> {

    public School getSchool(String schoolCode){
        String hql="FROM School WHERE schoolCode=?";
        return findOne(hql,new Object[]{schoolCode});
    }


    public List<School> getSchoolListBySchoolCode(String schoolCode){
        String hql="FROM School WHERE schoolCode=?";
        return find(hql,new Object[]{schoolCode});
    }

    public School getSchool(Long schoolId){
        String hql="FROM School WHERE id=?";
        return findOne(hql,new Object[]{schoolId});
    }

    public List<School> getSchoolBySchoolType(int schoolType){
        String hql="FROM School s WHERE type=? AND (SELECT COUNT(*) FROM User u WHERE u.schoolCode=s.schoolCode)>0";
        return find(hql,new Object[]{schoolType});
    }

    public List<School> getAll(){
        String hql="FROM School";
        return find(hql);
    }
    public List<School> getAllSchool(){
        String hql="FROM School AND type!=11";
        return find(hql);
    }
    //模糊查询学校
    public List<School> getSchoolByLikeName(String schoolName){
        String hql="FROM School WHERE name like ? ";
        return find(hql,new Object[]{"%"+schoolName+"%"});
    }

    //删除学校
    public void deleteSchool(Long schoolId){
        String hql="DELETE School WHERE id=?";
        executeUpdate(hql,new Object[]{schoolId});
    }

    public School getSchoolByName(String schoolName){
        String hql="FROM School WHERE name =?";
        return findOne(hql,new Object[]{schoolName});
    }
}
