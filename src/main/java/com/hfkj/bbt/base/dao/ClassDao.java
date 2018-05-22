package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.Classes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-10-25.
 */
@Repository
public class ClassDao extends BaseDao<Classes> {



    public Classes getClass(Long classId){
        String hql="FROM Classes WHERE id=?";
        return findOne(hql,new Object[]{classId});
    }

    /**
     * 根据学校id查询班级
     * @param schoolId
     * @return
     */
    public List<Classes> getClassAndSchool(String schoolId){
        String hql="FROM Classes WHERE schoolCode=?";
        return find(hql,new Object[]{schoolId});
    }

    public Classes getClass(Long gradeId,String schoolCode,String className){
        String hql="FROM Classes WHERE gradeId=? AND schoolCode=? AND className=?";
        return findOne(hql,new Object[]{gradeId,schoolCode,className});
    }

    /**
     * 根据学校Id与年级Id查询班级
     * @param schoolId
     * @param gradeId
     * @return
     */
    public List<Classes> getGradeClassAndSchool(String schoolId,Long gradeId){
        String hql="FROM Classes WHERE schoolCode=? AND gradeId=?  ";
        return find(hql,new Object[]{schoolId,gradeId});
    }

    /**
     * 根据学校Id与年级Id查询班级
     * @param schoolId
     * @param gradeId
     * @return
     */
    public List<Classes> getClassBySchoolException(String schoolId,Long gradeId){
        String hql="FROM Classes WHERE schoolCode=? AND gradeId=? ";
        return find(hql,new Object[]{schoolId,gradeId});
    }

    /**
     * 根据学校Id与年级Id查询班级
     * @param roomId
     * @return
     */
    public Classes getClassRoomId(Long roomId){
        String hql="FROM Classes WHERE roomId = ? ";
        return findOne(hql,new Object[]{roomId});
    }

}
