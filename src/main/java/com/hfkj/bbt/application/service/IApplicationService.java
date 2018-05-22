package com.hfkj.bbt.application.service;

import com.hfkj.bbt.application.web.vo.ApplicationVo;
import com.hfkj.bbt.application.web.vo.ControlVo;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.WorkSchedule;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.rest.web.vo.DriveByWireVo;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public interface IApplicationService {


    /**
     * 使用记录
     * @param applicationVo
     * @return
     */
    Page selectUsedRecord(ApplicationVo applicationVo);

    /**
     * 应用监测
     * @param applicationVo
     * @return
     */
    Page selectDistinctSchoolList(ApplicationVo applicationVo);

    Page selectEquipmentSingleSchoolList(ApplicationVo applicationVo);

    ResponseEntity doControlOrder(ControlVo vo);

    /**
     * 首页应用检测页面
     * @return
     */
    List loadEquipmentSingle(int qxmId);

    /**
     * 首页资产管理
     * @return
     */
    List LoadClassRoomEQuiMentSchool(int qxmId);

    /**
     * 首页资产管理图表数据
     * @param qxmId
     * @return
     */
    List loadChartDataAsset(int qxmId);

    List loadEnvironmentIndex(String qxmId);

    /**
     * 环境检测
     * @param applicationVo
     * @return
     */
    Page selectEnvironmentGradeClassList(ApplicationVo applicationVo);

    /**
     * 首页资产管理完好率
     * @return
     */
    List LoadClassRoomEQuiMent(int qxmId);


    /**
     * 根据年级id查询作息时间表
     * @param gradeId
     * @return
     */
    List getWorkSchedule(Long gradeId,Long classId,String schoolCode);

    /**
     * 根据年级id查询设备使用情况
     * @param gradeId
     * @return
     */
    List getWorkScheduleClassUser(Long gradeId, Long classId,String schoolCode);


    /**
     * 手机接口查询单个学校的数据
     * @param schoolCode
     * @return
     */
    List findSingleApplication(String schoolCode,String className);

    /**
     * 手机接口查询单个学校应用检查概览的数据
     * @param schoolCode
     * @return
     */
    List findSingleGeneralView(String schoolCode);

    /**
     * 手机接口 查询使用率概况
     * @param schoolCode
     * @return
     */
    List selectMobileDistinctSchoolList(String schoolCode);

    /**
     * 手机接口 分页查询单个教室详情数据
     * @param schoolCode
     * @return
     */
    Page findPageSingleApplication(String schoolCode,String className,int startNumber,int sizeNumber);

    int driveByWire(DriveByWireVo vo);

}
