package com.hfkj.bbt.task.service;

import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.DateUtil;
import com.hfkj.bbt.operationsManagement.activiti.ActivitiService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.valueOf;

/**
 * Created by Administrator on 2017-12-05.
 */
@Service
@EnableScheduling
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class BbtTaskService {

    private static final Logger LOG=Logger.getLogger(BbtTaskService.class);

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private ActivitiService activitiService;

    @Autowired
    private UsedRecordDao usedRecordDao;

    @Autowired
    private EquipmentSchoolDao equipmentSchoolDao;
    @Autowired
    private EquipmentClassDao equipmentClassDao;
    @Autowired
    private EquipmentTeacherDao equipmentTeacherDao;
    @Autowired
    private EquipmentSubjectDao equipmentSubjectDao;
    @Autowired
    private OperationsDao operationsDao;
    @Autowired
    private RoleToUserDao roleToUserDao;
    @Autowired
    private ClassDao classDao;
    @Autowired
    private ClassRoomDao classRoomDao;
    /**
     * 当天23点59分触发 初始化当天未使用
     */
    @Scheduled(cron = "0 59 23 * * ?")
    @Transactional(readOnly = false)
    public void setUsedFalse(){
        LOG.info("开始初始化未使用状态------------开始");
        List<Equipment> equipments = equipmentDao.findEquipment();
        if (ComUtil.listNotNullAndEmpty(equipments)){
            for (Equipment equipment:equipments){
                equipment.setIsUsed(0);
            }
        }
        equipmentDao.update(equipments);
        LOG.info("初始化未使用状态------------结束");
    }


    /**
     * 当天22点10分触发 保存每个学校每天的使用课时
     */
    @Scheduled(cron = "0 10 23 * * ?")
    @Transactional(readOnly = false)
    public void workScheduleUsed(){
        LOG.info("保存每天每个学校使用设备的课时------------开始");
        List<Object[]> list = usedRecordDao.getSchoolClassWorkSchedule();
        List<Object[]> list1 = usedRecordDao.getSchoolClassWorkScheduleAll();
        EquipmentSchool equipmentSchool;
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] objects:list){
                equipmentSchool = new EquipmentSchool();
                equipmentSchool.setSchoolCode(valueOf(objects[0]));
                equipmentSchool.setNumber(valueOf(objects[1]));
                equipmentSchool.setNewDate(new Date());
                for (Object[] objects1:list1){
                    equipmentSchool.setNumberAll(valueOf(objects1[0]));
                }
                equipmentSchoolDao.save(equipmentSchool);
            }
        }
        LOG.info("保存每天每个学校使用设备的课时------------结束");
    }

    /**
     * 当天23点20分触发 保存每个教师每天的使用课时
     */
    @Scheduled(cron = "0 20 23 * * ?")
    @Transactional(readOnly = false)
    public void workScheduleTeacherUsed(){
        LOG.info("保存每天每个教师使用设备的课时------------开始");
        List<Object[]> list = usedRecordDao.getTeacherWorkSchedule();
        EquipmentTeacher equipmentTeacher;
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] objects:list){
                equipmentTeacher = new EquipmentTeacher();
                equipmentTeacher.setSchoolCode(valueOf(objects[0]));
                equipmentTeacher.setUserId(valueOf(objects[1]));
                equipmentTeacher.setNumber(valueOf(objects[2]));
                equipmentTeacher.setNumberAll(valueOf(objects[3]));
                equipmentTeacher.setNewDate(new Date());
                equipmentTeacherDao.save(equipmentTeacher);
            }
        }
        LOG.info("保存每天每个教师使用设备的课时------------结束");
    }

    /**
     * 当天23点30分触发 保存每个班级每天的使用课时
     */
    @Scheduled(cron = "0 30 23 * * ?")
    @Transactional(readOnly = false)
    public void workScheduleClassUsed(){
        LOG.info("保存每天每个班级使用设备的课时------------开始");
        List<Object[]> list = usedRecordDao.getClassWorkSchedule();
        EquipmentClass equipmentClass;
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] objects:list){
                equipmentClass = new EquipmentClass();
                equipmentClass.setSchoolCode(valueOf(objects[0]));
                equipmentClass.setGradeId(valueOf(objects[1]));
                equipmentClass.setClassId(valueOf(objects[2]));
                equipmentClass.setNumber(valueOf(objects[3]));
                equipmentClass.setNumberAll(valueOf(objects[4]));
                equipmentClass.setNewDate(new Date());
                equipmentClassDao.save(equipmentClass);
            }
        }
        LOG.info("保存每天每个班级使用设备的课时------------结束");
    }

    /**
     * 当天23点40分触发 保存每个科目每天的使用课时
     */
    @Scheduled(cron = "0 40 23 * * ?")
    @Transactional(readOnly = false)
    public void workScheduleSubjectUsed(){
        LOG.info("保存每天每个科目使用设备的课时------------开始");
        List<Object[]> list = usedRecordDao.getSubjectWorkSchedule();
        EquipmentSubject equipmentSubject;
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] objects:list){
                equipmentSubject = new EquipmentSubject();
                equipmentSubject.setSubjectId(valueOf(objects[0]));
                equipmentSubject.setNumber(valueOf(objects[1]));
                equipmentSubject.setSchoolCode(valueOf(objects[2]));
                equipmentSubject.setNewDate(new Date());
                equipmentSubjectDao.save(equipmentSubject);
            }
        }
        LOG.info("保存每天每个科目使用设备的课时------------结束");
    }

    /**
     * 当天23点50分触发 保存七天未使用设备异常
     */
    @Scheduled(cron = "0 50 23 * * ?")
    @Transactional(readOnly = false)
    public void workOperationsEquipment(){
        LOG.info("保存七天未使用设备异常------------开始");
        List<Object[]> list = equipmentDao.getOperationsEquipment();

        Operations operations ;
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] objects:list) {
                if(objects[0]!=null && objects[1]!=null && objects[2]!=null ){
                    if ("0".equals(String.valueOf(objects[3]))) {
                        operations = new Operations();
                        operations.setExceptionType("七天未使用设备");
                        operations.setStartDate(new Date());
                        operations.setSchoolCode(String.valueOf(objects[0]));
                        operations.setClassId(Long.valueOf(String.valueOf(objects[2])));
                        operations.setExceptionDescription("由于7天未使用所有检测一下");
                        operations.setBirthType("auto");
                        operations.setProcessId(stateProcess(operations));
                        operationsDao.save(operations);
                        LOG.info("生成一个异常信息!"+operations.getId()+operations.getExceptionType());
                    }
                }
            }
        }
        LOG.info("保存七天未使用设备异常------------结束");
    }


    private String stateProcess(Operations operations){
        Map<String,Object> values=new HashMap<String,Object>();
        List list =roleToUserDao.getSchoolManagerBySchool(operations.getSchoolCode());
        //上报学校管理员
        values.put(Operations.SCHOOLMANAGERS,String.valueOf(list.get(0)));

        return activitiService.startHandException(values,Operations.AUTOEXCEPTIONAKEY);
    }

    /**
     * 当天23点55分触发 保存频繁使用设备异常
     */
    @Scheduled(cron = "0 55 23 * * ?")
    @Transactional(readOnly = false)
    public void workFrequentlyEquipment(){
        LOG.info("保存频繁使用设备异常------------开始");
        List<Object[]> list = equipmentDao.getFrequentlyEquipment();
        Operations operations;
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] objects:list) {
                if(objects[0]!=null && objects[1]!=null && objects[2]!=null ) {
                    if (Long.valueOf(String.valueOf(objects[3])) >= 20) {
                        operations = new Operations();
                        operations.setExceptionType("频繁使用设备");
                        operations.setStartDate(new Date());
                        operations.setSchoolCode(String.valueOf(objects[0]));
                        operations.setClassId(Long.valueOf(String.valueOf(objects[2])));
                        operations.setBirthType("auto");
                        operations.setExceptionDescription("由于该设备使用太过频繁请管理员去检测一下");
                        operations.setProcessId(stateProcess(operations));
                        operationsDao.save(operations);
                    }
                }
            }
        }
        LOG.info("保存频繁使用设备异常------------结束");
    }


    /**
     * 当天00点10分触发 初始化状态
     */
    @Scheduled(cron = "0 10 00 * * ?")
    @Transactional(readOnly = false)
    public void workEquipment(){
        LOG.info("开始设备没有关异常------------开始");
        Operations operations;
        List<Equipment> list = equipmentDao.findEquipment();
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Equipment equipment:list){
                ClassRoom classRoom = classRoomDao.loadClassRoom(equipment.getEquipmentNo());
                if (classRoom!=null){
                    Classes classes = classDao.getClassRoomId(classRoom.getId());
                    if (classes!=null){
                        if(equipment.getWorkStatus()==1){
                            operations = new Operations();
                            operations.setExceptionType("设备没有关");
                            operations.setStartDate(new Date());
                            operations.setSchoolCode(classes.getSchoolCode());
                            operations.setClassId(classes.getId());
                            operations.setBirthType("auto");
                            operations.setExceptionDescription("由于该设备没有关请管理员去检测一下");
                            operations.setProcessId(stateProcess(operations));
                            operationsDao.save(operations);
                        }
                    }

                }
            }
        }
        LOG.info("开始设备没有关异常------------结束");
    }


}
