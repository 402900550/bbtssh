package com.hfkj.bbt.systemanage.service;


import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.TeachBuilding;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
public interface IBuildingService {

    /**
     * 根据条件查询教学楼
     */
    Page selectTeachBuildingBySchoolName(SearchVo SearchVo);


    ResponseEntity deleteBuilding(Long buildingId);

    //添加教学楼
    int addTeachBuilding(String schoolCode, String buildingName);

    //修改教学楼名字
    int updateTeachBuilding(Long buildingId, String buildingName);

    //根据ID查询教学楼
    TeachBuilding getBuildingById(Long buildingId);

    List<TeachBuilding> getBuildingBySchoolCode(String schoolCode);
}
