package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.assetmanage.web.vo.AssetSearchVo;
import com.hfkj.bbt.base.entity.EquipmentClass;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.dataAnalysis.web.vo.DataAnalysisSearchVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
@Repository
public class EquipmentClassDao extends BaseDao<EquipmentClass> {


    /**
     * 根据学校查询该学校的排名以及使用率
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqSchool(DataAnalysisSearchVo dataAnalysisSearchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT ts.`name`,sum(tec.number),sum(tec.number_all),ROUND(sum(tec.number)/sum(tec.number_all),2),ts.school_code " +
                "FROM tab_equipment_school tec  " +
                "LEFT JOIN tab_school ts on tec.school_code = ts.school_code  " +
                "where ts.name like :schoolName " +
                "GROUP BY ts.school_code " ;
        if("1".equals(dataAnalysisSearchVo.getEnding())){
            sql+= "ORDER BY sum(tec.number) / sum(tec.number_all) ";
        }else {
            sql += "ORDER BY sum(tec.number) / sum(tec.number_all) DESC ";
        }
        params.put("schoolName",dataAnalysisSearchVo.getSchoolName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(dataAnalysisSearchVo.getSizeNumber());
        pageInfo.setCurrentPage(dataAnalysisSearchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }

    /**
     * 根据学校查询该学校的排名以及使用率
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqClass(DataAnalysisSearchVo dataAnalysisSearchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql="SELECT " +
                " ts.`name` tsName,tg.`name` tgName,tc.class_name tcName, " +
                " sum(tec.number), " +
                " sum(tec.number_all), " +
                " ROUND(sum(tec.number) / sum(tec.number_all),2) " +
                " FROM tab_equipment_class tec " +
                " LEFT JOIN tab_school ts ON tec.school_code = ts.school_code  " +
                " LEFT JOIN tab_classes tc on tc.id = tec.class_id  " +
                " LEFT JOIN tab_grade tg on tg.id = tec.grade_id " +
                " where ts.name like :schoolName" ;
        if(dataAnalysisSearchVo.getSchoolCode()!=null) {
            sql += " and ts.school_code =:schoolCode " ;
            params.put("schoolCode",dataAnalysisSearchVo.getSchoolCode());
        }
        sql+= " GROUP BY tc.id " +
                " ORDER BY sum(tec.number) / sum(tec.number_all) DESC";
        params.put("schoolName",dataAnalysisSearchVo.getSchoolName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(dataAnalysisSearchVo.getSizeNumber());
        pageInfo.setCurrentPage(dataAnalysisSearchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }


}
