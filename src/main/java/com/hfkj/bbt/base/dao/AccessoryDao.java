package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.assetmanage.web.vo.AssetSearchVo;
import com.hfkj.bbt.base.entity.Accessory;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
@Repository
public class AccessoryDao extends BaseDao<Accessory>{


    public Accessory findAccessoryById(Long accessoryId){
        String hql="FROM Accessory WHERE id=?";
        return findOne(hql,new Object[]{accessoryId});
    }


    public List<Accessory> findAccessoryList(List<Long> ids){
        String hql="FROM Accessory WHERE id IN (:ids)";
        return find(hql,"ids",ids);
    }


    public List<Accessory> findAccessoryByRoom(Long roomId){
        String hql="FROM Accessory WHERE roomId=? AND facilities=1";
        return find(hql,new Object[]{roomId});
    }

    /**
     * 查询学校设备
     * @param searchVo
     * @return
     */
    public Page findAccessoryList(SearchVo searchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT " +
                "  ts.`name` schname, " +
                "  dt.`name`, " +
                "  p.detail, " +
                "  COUNT(dt.`name`) dcount, " +
                "  ta.price, " +
                "  YEAR (MAX(ta.purchase_date)) mm, " +
                "  YEAR (MIN(ta.purchase_date)) nn " +
                "FROM " +
                "  tab_accessory ta " +
                "LEFT JOIN tab_school ts ON ts.school_code = ta.school_code " +
                "LEFT JOIN tab_develop_type dt ON dt.id = ta.equipment_type " +
                "LEFT JOIN tab_parameters p ON p.id = ta.size_type " +
                "WHERE ts.`name` LIKE :schoolName " +
                "GROUP BY " +
                "  ts.`name`, " +
                "  dt.`name` " +
                "ORDER BY ta.purchase_date ASC";

        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        params.put("schoolName",searchVo.getSchoolName());
        return  findPage(sql,params,pageInfo);
    }


    public List<Accessory> findAccessoryBySchoolAndDevelopType(String school){
        String hql="FROM Accessory WHERE schoolCode=? AND roomId IS NULL AND facilities=1 ";
        return find(hql,new Object[]{school});
    }

    /**
     * 查询设备
     * @param ids
     * @return
     */
    public Accessory findAccessoryClassList(Long ids){
        String hql="FROM Accessory WHERE id = ? ";
        return findOne(hql,new Object[]{ids});
    }


    /**
     * 查询地区的完好率情况
     * @param qxmId
     * @return
     */
    public Page loadDataAnalysisSchoolList(String qxmId) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT COUNT(*), " +
                " (SELECT COUNT(*) FROM tab_accessory tas  LEFT JOIN tab_school tsh ON tsh.school_code = tas.school_code WHERE  tas.facilities = 1 and tsh.qxm = ts.qxm ), " +
                " (SELECT COUNT(*) FROM tab_accessory tas LEFT JOIN tab_school tsh ON tsh.school_code = tas.school_code  WHERE   tsh.qxm = ts.qxm AND tas.facilities != 1) " +
                " FROM tab_accessory ta  " +
                " LEFT JOIN tab_school ts on ts.school_code = ta.school_code  " +
                " WHERE ts.qxm = "+qxmId;
        PageInfo pageInfo = new PageInfo();
        return findPage(sql, params, pageInfo);
    }

    /**
     * 查询地区的完好率情况
     * @param schoolCode
     * @return
     */
    public Page loadDataAnalysisSchool(String schoolCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT COUNT(*),(SELECT COUNT(*) FROM tab_accessory tas where tas.school_code = ts.school_code  and tas.facilities = 1),(SELECT COUNT(*) FROM tab_accessory tas where tas.school_code = ts.school_code  and tas.facilities !=1) " +
                "FROM tab_accessory ta  " +
                "LEFT JOIN tab_school ts on ts.school_code = ta.school_code  " +
                "WHERE ts.school_code = "+schoolCode;
        PageInfo pageInfo = new PageInfo();
        return findPage(sql, params, pageInfo);
    }


}
