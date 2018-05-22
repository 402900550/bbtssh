package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.entity.ClassRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-10-31.
 */
@Repository
public class ClassRoomDao extends BaseDao<ClassRoom>{

    public ClassRoom getClassRoom(Long roomId){
        String hql="FROM ClassRoom WHERE id=?";
        return findOne(hql,new Object[]{roomId});
    }
    public ClassRoom getClassRoom(String equipmentNo){
        String hql="FROM ClassRoom WHERE equipmentNo=?";
        return findOne(hql,new Object[]{equipmentNo});
    }
    /**
     * 查询所有
     * @return
     */
    public List<ClassRoom> selectClassRoom(Long buildingId){
        String hql=" FROM ClassRoom where buildingId=? AND status=0";
        return find(hql,new Object[]{buildingId});
    }


    public List<ClassRoom> getAllClassRoomByBuilding(Long building){
        String hql="FROM ClassRoom WHERE buildingId=?";
        return find(hql,new Object[]{building});
    }

    public List<ClassRoom> getClassRoomByBuilding(Long building){
        String hql="FROM ClassRoom WHERE buildingId=? AND status=0 AND id NOT IN (SELECT coalesce(roomId,-1) FROM Accessory)";
        return find(hql,new Object[]{building});
    }

    /**
     * 删除教室
     * @param roomId
     */
    public void deleteClassRoomById(Long roomId){
        String hql="DELETE ClassRoom WHERE id=?";
        executeUpdate(hql,new Object[]{roomId});
    }

    /**
     * 查询所有设备
     * @return
     */
    public ClassRoom loadClassRoom(String equipmentNo){
        String hql="FROM ClassRoom where equipmentNo=? ";
        return findOne(hql,new Object[]{equipmentNo});
    }
}
