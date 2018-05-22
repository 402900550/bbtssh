package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.DevelopType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-11-13.
 */
@Repository
public class DevelopTypeDao extends BaseDao<DevelopType>{


    public List<DevelopType> getDevelops(){
        String hql="FROM DevelopType order by id";
        return find(hql);
    }

    public List<DevelopType> getDevelops(Long type){
        String hql="FROM DevelopType where type=? or type=0 ";
        if (type!=3){
            hql+=" or type=5 ";
        }
        hql+=" order by id";
        return find(hql,new Object[]{type});
    }

    public DevelopType getDevelop(Long id){
        String hql="FROM DevelopType where id=? ";
        return findOne(hql,new Object[]{id});
    }

}
