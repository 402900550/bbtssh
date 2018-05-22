package com.hfkj.bbt.base.dao;


import com.hfkj.bbt.base.entity.ParameterType;
import com.hfkj.bbt.base.entity.Parameters;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Repository
public class ParameterTypeDao extends BaseDao<ParameterType>{

    public List<ParameterType> getAllType(){
        String hql="FROM ParameterType order by id  ";
        return find(hql);
    }


    public ParameterType getTypeByName(String name){
        String hql="FROM  ParameterType WHERE name=?";
        return findOne(hql,new Object[]{name});
    }

}
