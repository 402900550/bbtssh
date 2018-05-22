package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.util.ComUtil;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-12-06.
 */
@Repository
public class TaskDao extends BaseDao{


    /**
     * 根据学校获取监测数
     * @param schoolCode
     * @return
     */
    public int getMonitorsCount(String schoolCode){
        String sql="SELECT " +
                " COUNT(*) " +
                " FROM " +
                " tab_classroom class " +
                " LEFT JOIN tab_teach_building ttb ON class.building_id=ttb.id " +
                " LEFT JOIN tab_school ts ON ts.school_code=ttb.school_code " +
                " WHERE ts.school_code=:schoolCode AND class.equipment_no IS NOT NULL";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("schoolCode",schoolCode);
        return getIntValue(findBySql(sql, params));
    }

    /**
     * 根据学校获取运行数
     * @param schoolCode
     * @return
     */
    public int getRunningCount(String schoolCode){
        String sql="SELECT  " +
                "  COUNT(*)  " +
                " FROM  " +
                "  tab_classroom class  " +
                " LEFT JOIN tab_teach_building ttb ON class.building_id=ttb.id  " +
                " LEFT JOIN tab_school ts ON ts.school_code=ttb.school_code  " +
                " LEFT JOIN tab_equipment eq ON eq.equipment_no=class.equipment_no  " +
                " WHERE ts.school_code=:schoolCode AND eq.is_used=1";
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("schoolCode",schoolCode);
        return getIntValue(findBySql(sql, params));
    }

    /**
     * 获取值
     * @param list
     * @return
     */
    private int getIntValue(List list){
        if (ComUtil.listNotNullAndEmpty(list)){
            BigInteger bigInteger = (BigInteger) list.get(0);
            return bigInteger.intValue();
        }
        return 0;
    }


}
