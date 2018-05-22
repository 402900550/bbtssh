package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.EquipmentClass;
import com.hfkj.bbt.base.entity.EquipmentSchool;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.dataAnalysis.web.vo.DataAnalysisSearchVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/14 0014.
 */
@Repository
public class EquipmentSchoolDao extends BaseDao<EquipmentSchool> {


    /**
     * 查询每年每月每天设备使用统计图
     * @param newDate
     * @param schoolCode
     * @return
     */
    public List loadDataAnalysisSchoolList(String newDate, String schoolCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        String sql = "SELECT ROUND(SUM(number)), ";
        if(newDate.equals("1")){
            sql +=  " DATE_FORMAT(new_date,'%Y年') " +
                    " FROM tab_equipment_school " +
                    " where school_code = "+schoolCode+
                    " GROUP BY DATE_FORMAT(new_date,'%y') " ;
        }else if (newDate.equals("2")){
            sql +=  " DATE_FORMAT(new_date,'%Y年%m月') " +
                    " FROM tab_equipment_school " +
                    " where school_code = "+schoolCode+
                    " GROUP BY DATE_FORMAT(new_date,'%m') " ;
        }else if (newDate.equals("3")){
            sql +=  "  DATE_FORMAT(new_date,'%Y-%m-%d') " +
                    " FROM tab_equipment_school  " +
                    " where school_code = "+schoolCode+
                    " GROUP BY new_date  ";
        }
        return findBySql(sql,params);
    }

}
