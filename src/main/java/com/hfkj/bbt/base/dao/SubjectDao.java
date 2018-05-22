package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.Subject;
import com.hfkj.bbt.base.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/27 0027.
 */
@Repository
public class SubjectDao extends BaseDao<Subject> {

    /**
     * 查询学科表
     * @return
     */
    public List<Subject> getSubjectName(){
        String hql="FROM Subject";
        return find(hql);
    }

}
