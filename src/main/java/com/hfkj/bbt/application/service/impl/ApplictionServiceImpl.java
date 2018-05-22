package com.hfkj.bbt.application.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hfkj.bbt.application.service.IApplicationService;
import com.hfkj.bbt.application.web.vo.ApplicationVo;
import com.hfkj.bbt.application.web.vo.ControlVo;
import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.HttpClientApi;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.rest.web.vo.DataVo;
import com.hfkj.bbt.rest.web.vo.DriveByWireVo;
import com.hfkj.bbt.rest.web.vo.Header;
import com.hfkj.bbt.rest.web.vo.RunTime;
import org.apache.log4j.Logger;
import org.hibernate.annotations.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ApplictionServiceImpl implements IApplicationService {

    private static final Logger log = Logger.getLogger(ApplictionServiceImpl.class);
    @Autowired
    private ApplicationDao applicationDao;

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private SwitchRecordDao switchRecordDao;

    @Autowired
    private WorkScheduleDao workScheduleDao;

    @Autowired
    private ClassRoomDao classRoomDao;

    @Autowired
    private BuildingDao buildingDao;

    /**
     * 查询设备使用记录
     *
     * @param applicationVo
     * @return
     */
    @Override
    public Page selectUsedRecord(ApplicationVo applicationVo) {
        return applicationDao.selectUsedRecord(applicationVo);
    }

    /**
     * 应用监测
     *
     * @param applicationVo
     * @return
     */
    public Page selectDistinctSchoolList(ApplicationVo applicationVo) {
        return applicationDao.selectDistinctSchoolList(applicationVo);
    }

    @Override
    public Page selectEquipmentSingleSchoolList(ApplicationVo applicationVo) {
        return applicationDao.selectEquipmentSingleSchoolList(applicationVo);
    }

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity doControlOrder(ControlVo vo) {

        School school = schoolDao.getSchool(vo.getSchoolCode());
        Equipment equipmentByNo = equipmentDao.findEquipmentByNo(vo.getEquipmentNo());
        if (school != null) {
            String ip = school.getIp();
            if (!ComUtil.stringIsNotNull(ip)) {
                return ResponseEntity.isFail("请联系幻方管理员设置网关IP!");
            }
            DataVo dataVo = new DataVo();
            Header header = new Header();
            RunTime runTime = new RunTime();
            header.setDataType("2");
            header.setEquipmentNo(vo.getEquipmentNo());
            header.setEquipmentType("3");
            header.setSchoolCode(vo.getSchoolCode());
            if (equipmentByNo != null) {
                header.setIp(equipmentByNo.getEquipmentIp());
            }
            dataVo.setHeader(header);
            if ("air_conditioning".equalsIgnoreCase(vo.getControlType())) {
                runTime.setAirConditioning(String.valueOf(vo.getOrder()));
            }
            if ("lights".equalsIgnoreCase(vo.getControlType())) {
                runTime.setLights(String.valueOf(vo.getOrder()));
            }
            if ("sockets".equalsIgnoreCase(vo.getControlType())) {
                runTime.setSockets(String.valueOf(vo.getOrder()));
            }
            if ("fan".equalsIgnoreCase(vo.getControlType())) {
                runTime.setFan(String.valueOf(vo.getOrder()));
            }
            dataVo.setRuntime(runTime);
            HttpClientApi clientApi = new HttpClientApi("http://" + school.getIp() + ":58890/send");
            String post = clientApi.post(JSON.toJSONString(dataVo));
            log.info("电控返回的数据:" + post);

            String schoolCode = school.getSchoolCode();
            String qxm = school.getQxm();
            String controlType = vo.getControlType();
            String equipmentNo = vo.getEquipmentNo();
            Integer equipmentType = vo.getEquipmentType();
            Integer order = vo.getOrder();

            //设备记录
            SwitchRecord switchRecord = new SwitchRecord();
            User user = UserUtil.getCurrentUser();
            switchRecord.setUserId(user.getId());
            switchRecord.setEquipmentNo(equipmentNo);
            switchRecord.setEquipmentType(equipmentType);
            switchRecord.setDeviceType(controlType);
            switchRecord.setQxm(qxm);
            switchRecord.setSchoolCode(schoolCode);
            switchRecord.setStatus(order);
            switchRecord.setNewTime(new Date());
            switchRecordDao.save(switchRecord);

            return ResponseEntity.isOk(post);
        }

        return ResponseEntity.isFail("操作失败!请检查学校单位代码是否正确!");
    }



    /**
     * 首页应用检测页面
     *
     * @return
     */
    public List loadEquipmentSingle(int qxmId) {
        return applicationDao.loadEquipmentSingle(qxmId);
    }

    /**
     * 首页资产管理
     *
     * @return
     */
    public List LoadClassRoomEQuiMentSchool(int qxmId) {
        return applicationDao.LoadClassRoomEQuiMentSchool(qxmId);
    }

    @Override
    public List loadChartDataAsset(int qxmId) {
        return applicationDao.loadChartDataAsset(qxmId);
    }

    @Override
    public List loadEnvironmentIndex(String qxmId) {
        List list = applicationDao.loadEnvironmentIndex(qxmId);
        if (!ComUtil.listNotNullAndEmpty(list)) {
            list = new ArrayList();
        }
        return list;
    }

    /**
     * 环境检测
     *
     * @param applicationVo
     * @return
     */
    public Page selectEnvironmentGradeClassList(ApplicationVo applicationVo) {
        return applicationDao.selectEnvironmentGradeClassList(applicationVo);
    }

    /**
     * 首页资产管理完好率
     *
     * @return
     */
    public List LoadClassRoomEQuiMent(int qxmId) {
        return applicationDao.LoadClassRoomEQuiMent(qxmId);
    }

    /**
     * 根据年级id查询作息时间表
     *
     * @param gradeId
     * @return
     */
    public List getWorkSchedule(Long gradeId, Long classId, String schoolCode) {
        return workScheduleDao.getWorkSchedule(gradeId, classId, schoolCode);
    }

    /**
     * 根据年级id查询设备使用情况
     *
     * @param gradeId
     * @return
     */
    public List getWorkScheduleClassUser(Long gradeId, Long classId, String schoolCode) {
        return workScheduleDao.getWorkScheduleClassUser(gradeId, classId, schoolCode);
    }

    /**
     * 手机接口查询单个学校的数据
     *
     * @param schoolCode
     * @return
     */
    @Override
    public List findSingleApplication(String schoolCode, String className) {
        return applicationDao.findSingleApplication(schoolCode, className);
    }

    /**
     * 手机接口查询单个学校应用监测概览
     *
     * @param schoolCode
     * @return
     */
    public List findSingleGeneralView(String schoolCode) {
        return applicationDao.findSingleGeneralView(schoolCode);
    }

    /**
     * 手机接口 查询使用率概况
     *
     * @param schoolCode
     * @return
     */
    @Override
    public List selectMobileDistinctSchoolList(String schoolCode) {
        return applicationDao.selectMobileDistinctSchoolList(schoolCode);
    }

    /**
     * 手机接口 分页查询单个教室详情数据
     *
     * @param schoolCode
     * @return
     */
    public Page findPageSingleApplication(String schoolCode, String className, int startNumber, int sizeNumber) {
        return applicationDao.findPageSingleApplication(schoolCode, className, startNumber, sizeNumber);
    }

    /**
     * 手机接口 电控
     *
     * @param vo 参数
     * @return
     */
    @Override
    public int driveByWire(DriveByWireVo vo) {
        String schoolIp = "";
        ClassRoom classRoom = classRoomDao.getClassRoom(vo.getEquipmentNo());
        if (classRoom != null) {
            Long buildingId = classRoom.getBuildingId();
            TeachBuilding building = buildingDao.findBuilding(buildingId);
            String schoolCode = building.getSchoolCode();
            School school = schoolDao.getSchool(schoolCode);
            schoolIp = school.getIp();
            if (!ComUtil.stringIsNotNull(schoolIp)) {
                //没有学校IP
                return 2;
            }
            Equipment equipmentByNo = equipmentDao.findEquipmentByNo(vo.getEquipmentNo());
            DataVo dataVo = new DataVo();
            Header header = new Header();
            RunTime runTime = new RunTime();
            header.setDataType("2");
            header.setEquipmentNo(vo.getEquipmentNo());
            header.setEquipmentType("3");
            header.setSchoolCode(schoolCode);
            if (equipmentByNo != null) {
                header.setIp(equipmentByNo.getEquipmentIp());
            }
            dataVo.setHeader(header);
            runTime.setAirConditioning(String.valueOf(vo.getAir()));
            runTime.setLights(String.valueOf(vo.getLight()));
            runTime.setSockets(String.valueOf(vo.getSocket()));
            runTime.setFan(String.valueOf(vo.getFan()));
            dataVo.setRuntime(runTime);
            HttpClientApi clientApi = new HttpClientApi("http://" + school.getIp() + ":58890/send");
            String post = clientApi.post(JSON.toJSONString(dataVo));
            log.info("电控返回的数据:" + post);
            if (post != null && post.contains("OK")) {
                return 1;
            }
        }
        return 3;
    }

}
