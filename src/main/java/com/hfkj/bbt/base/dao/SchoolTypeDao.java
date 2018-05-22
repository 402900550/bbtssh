package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.SchoolType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-10-24.
 */
@Repository
public class SchoolTypeDao extends BaseDao<SchoolType>{


    public List<SchoolType> getSchoolTypes(){
        String hql="FROM SchoolType";
        return find(hql);
    }

}
