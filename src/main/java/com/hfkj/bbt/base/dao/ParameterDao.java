package com.hfkj.bbt.base.dao;


import com.hfkj.bbt.base.entity.Parameters;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.systemanage.web.vo.ParameterVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Repository
public class ParameterDao extends BaseDao<Parameters>{

    public Parameters getParameters(Long PId){
        String hql="FROM Parameters WHERE id=?";
        return findOne(hql,new Object[]{PId});
    }


    public Page selectParameterList(ParameterVo vo){
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT " +
                " t.id tid, " +
                " t.`name` tname, " +
                " p.detail pname, " +
                " p.remark , " +
                " p.id pid " +
                " FROM " +
                " tab_parameters p " +
                " LEFT JOIN tab_parameter_type t ON p.type_id = t.id " +
                " WHERE p.detail LIKE :pname  ";

        if (vo.getParameterTypeId()!=null){
            sql+="AND t.id=:tid";
            params.put("tid",vo.getParameterTypeId());
        }
        sql+=" order by t.id ";
        params.put("pname",vo.getParameterValue());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(vo.getPageSize());
        pageInfo.setCurrentPage(vo.getStartNum());
        return findPage(sql,params,pageInfo);
    }

}
