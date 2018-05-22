package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.entity.CityArea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-10-23.
 */
@Repository
public class CityAreaDao extends BaseDao<CityArea> {

    public CityArea getCityAreaByDetailCode(String detailCode){
        String hql="FROM CityArea WHERE detailCode=?";
        return findOne(hql,new Object[]{detailCode});
    }



    public List<CityArea> getAllCityArea(){
        String hql="FROM CityArea WHERE areaCode='023' ";
        return find(hql);
    }

}
