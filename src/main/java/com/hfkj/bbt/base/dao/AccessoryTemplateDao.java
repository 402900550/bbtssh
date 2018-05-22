package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.entity.AccessoryTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
@Repository
public class AccessoryTemplateDao extends BaseDao<AccessoryTemplate>{

    public List<AccessoryTemplate> findAccessoryRoomBySchool(Long developType){
        String hql="FROM AccessoryTemplate WHERE  developType = ? ";
        return find(hql,new Object[]{developType});
    }

    public AccessoryTemplate findAccessoryTemplateById(Long saveId){
        String hql="FROM AccessoryTemplate WHERE  id = ? ";
        return findOne(hql,new Object[]{saveId});
    }

}
