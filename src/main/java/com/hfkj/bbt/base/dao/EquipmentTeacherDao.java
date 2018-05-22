package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.EquipmentSchool;
import com.hfkj.bbt.base.entity.EquipmentTeacher;
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
public class EquipmentTeacherDao extends BaseDao<EquipmentTeacher> {

    /**
     * 根据学校查询教师的排名以及使用率
     * @param dataAnalysisSearchVo
     * @return
     */
    public Page selectEqTeacher(DataAnalysisSearchVo dataAnalysisSearchVo) {
        Map<String,Object> params=new HashMap<String,Object>();
        String sql=" SELECT " +
                " ts.`name`,tu.real_name, " +
                " sum(tet.number), " +
                " sum(tet.number_all), " +
                " ROUND(sum(tet.number) / sum(tet.number_all),2) " +
                "FROM tab_equipment_teacher tet " +
                "LEFT JOIN tab_school ts ON tet.school_code = ts.school_code  " +
                "LEFT JOIN tab_user tu on tu.id = tet.user_id " +
                " where ts.name like :schoolName " ;
        if(dataAnalysisSearchVo.getSchoolCode()!=null) {
            sql += " and ts.school_code =:schoolCode " ;
            params.put("schoolCode",dataAnalysisSearchVo.getSchoolCode());
        }
        sql += " GROUP BY tu.id " ;
        if ("1".equals(dataAnalysisSearchVo.getEnding())){
            sql += " ORDER BY sum(tet.number) / sum(tet.number_all) ";
        }else {
            sql += " ORDER BY sum(tet.number) / sum(tet.number_all) DESC ";
        }
        params.put("schoolName",dataAnalysisSearchVo.getSchoolName());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setRowsOfPage(dataAnalysisSearchVo.getSizeNumber());
        pageInfo.setCurrentPage(dataAnalysisSearchVo.getStartNumber());
        return findPage(sql,params,pageInfo);
    }

}
