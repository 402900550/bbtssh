package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.EquipmentSubject;
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
public class EquipmentSubjectDao extends BaseDao<EquipmentSubject> {

    /**
     * 根据学校查询科目的排名以及使用率
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqSubject(DataAnalysisSearchVo dataAnalysisSearchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT ts.subject_name,sum(tes.number) " +
                " FROM tab_equipment_subject tes  " +
                " LEFT JOIN tab_subject ts on tes.subject_id = ts.id " +
                " LEFT JOIN tab_school tc on tes.school_code = tc.school_code " +
                " where tc.`name` like :schoolName ";
        if(dataAnalysisSearchVo.getSchoolCode()!=null) {
            sql += " and tc.school_code =:schoolCode " ;
            params.put("schoolCode",dataAnalysisSearchVo.getSchoolCode());
        }
        sql+= " GROUP BY tes.subject_id  ";
        if("1".equals(dataAnalysisSearchVo.getEnding())){
            sql+=" ORDER BY sum(tes.number) ";
        }else {
            sql+=" ORDER BY sum(tes.number) DESC ";
        }
        params.put("schoolName",dataAnalysisSearchVo.getSchoolName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(dataAnalysisSearchVo.getSizeNumber());
        pageInfo.setCurrentPage(dataAnalysisSearchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }
}
