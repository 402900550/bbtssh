package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.ClassRoomDao;
import com.hfkj.bbt.base.entity.ClassRoom;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.TeachBuilding;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.dao.BuildingDao;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.IBuildingService;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BuildingServiceImpl implements IBuildingService {


    @Autowired
    private BuildingDao buildingDao;

    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    public Page<TeachBuilding> selectTeachBuildingBySchoolName(SearchVo searchVo) {
        return buildingDao.findBuilding(searchVo);
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity deleteBuilding(Long buildingId) {
        List<ClassRoom> classRoomByBuilding = classRoomDao.getClassRoomByBuilding(buildingId);
        if (ComUtil.listNotNullAndEmpty(classRoomByBuilding)){
            return ResponseEntity.isFail("删除失败,该教学楼内存在未删除的教室!");
        }else {
            buildingDao.deleteBuilding(buildingId);
            return ResponseEntity.isOk("删除成功!");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public int addTeachBuilding(String schoolCode, String buildingName) {

        TeachBuilding teachBuilding = new TeachBuilding();
        teachBuilding.setSchoolCode(schoolCode);
        teachBuilding.setBuildingName(buildingName);
        buildingDao.save(teachBuilding);
        return 1;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateTeachBuilding(Long buildingId, String buildingName) {

        TeachBuilding teachBuilding = buildingDao.findBuilding(buildingId);
        teachBuilding.setBuildingName(buildingName);
        buildingDao.update(teachBuilding);
        return 1;
    }

    @Override
    public TeachBuilding getBuildingById(Long buildingId) {
        return buildingDao.findBuilding(buildingId);
    }

    @Override
    public List<TeachBuilding> getBuildingBySchoolCode(String schoolCode) {
        return buildingDao.findBuildingBySchool(schoolCode);
    }

}
