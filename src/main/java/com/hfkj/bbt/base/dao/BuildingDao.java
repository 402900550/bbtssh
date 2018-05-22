package com.hfkj.bbt.base.dao;

import com.hfkj.bbt.base.dao.BaseDao;
import com.hfkj.bbt.base.entity.TeachBuilding;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.page.PageInfo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017-10-25.
 */
@Repository
public class BuildingDao extends BaseDao<TeachBuilding> {


    public void deleteBuilding(Long buildingId){
        String hql="DELETE TeachBuilding WHERE id=?";
        executeUpdate(hql,new Object[]{buildingId});
    }

    //根据教学楼ID找教学楼类
    public TeachBuilding findBuilding(Long buildingId){
        String hql="FROM TeachBuilding WHERE id=?";
        return findOne(hql,new Object[]{buildingId});
    }


    public List<TeachBuilding> findBuildingBySchool(String schoolCode){
        String hql="FROM TeachBuilding WHERE schoolCode LIKE ?";
        return find(hql,new Object[]{schoolCode});
    }



    //根据学校代码和名称查询学校
    public Page<TeachBuilding> findBuilding(SearchVo searchVo){
        String hql="FROM TeachBuilding WHERE schoolCode like ? AND buildingName like ?";
        PageInfo pageInfo=new PageInfo();
        pageInfo.setCurrentPage(searchVo.getStartNumber());
        pageInfo.setRowsOfPage(searchVo.getSizeNumber());
        return find(hql,new Object[]{searchVo.getSchoolCode(),searchVo.getBuildingName()},pageInfo);
    }

    /**
     * 根据学校id查询教学楼
     * @param schoolCode
     * @return
     */
    public List<TeachBuilding> findBuildingBySchoolId(String schoolCode){
        String hql="FROM TeachBuilding WHERE schoolCode = ?";
        return find(hql,new Object[]{schoolCode});
    }

}
