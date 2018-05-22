package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.Grade;
import com.hfkj.bbt.base.entity.Parameters;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */
@Repository
public class ParametersDao extends BaseDao<Parameters> {

    /**
     * 根据id类型查询设备类型
     * @return
     */
    public List<Parameters> getParametersByTypeId(Long typeId){
        String hql="FROM Parameters where typeId = ? order by id";
        return find(hql,new Object[]{typeId});
    }


    /**
     * 根据id查询Parameters
     * @return
     */
    public Parameters getParametersById(Long id){
        String hql="FROM Parameters where id = ? ";
        return findOne(hql,new Object[]{id});
    }

}
