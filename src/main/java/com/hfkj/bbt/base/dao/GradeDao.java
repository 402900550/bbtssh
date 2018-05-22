package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.entity.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-11-01.
 */
@Repository
public class GradeDao extends BaseDao<Grade>{


    public List<Grade> getGrades(){
        String hql="FROM Grade";
        return find(hql);
    }

    public Grade getGrades(Long id){
        String hql="FROM Grade WHERE id=?";
        return findOne(hql,new Object[]{id});
    }

    public List<Grade> getGrades(String schoolCode){
        String hql="FROM Grade WHERE id IN (SELECT gradeId FROM Classes WHERE schoolCode=? GROUP BY gradeId)";
        return find(hql,new Object[]{schoolCode});
    }

}
