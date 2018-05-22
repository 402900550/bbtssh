package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.ScheduleDao;
import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.WorkSchedule;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.systemanage.service.IScheduleService;
import com.hfkj.bbt.systemanage.web.vo.WorkScheduleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017-12-11.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    @Transactional(readOnly = false)
    public ResponseEntity doSaveSchedule(List<WorkScheduleVo> vo) {
        if (ComUtil.listNotNullAndEmpty(vo)&&!ComUtil.stringIsNotNull(vo.get(0).getSchoolCode())) {
            return ResponseEntity.isFail("未找到你的学校,请联系管理员设置学校信息!");
        }
        if (ComUtil.listNotNullAndEmpty(vo)){
            for (WorkScheduleVo v:vo){
                WorkSchedule workSchedule=new WorkSchedule();
                workSchedule.setSchoolCode(v.getSchoolCode());
                workSchedule.setId(v.getId());
                workSchedule.setNumberCourse(v.getNumberCourse());
                workSchedule.setTimeType(v.getTimeType());
                workSchedule.setStart(transTime(v.getStart()));
                workSchedule.setEnd(transTime(v.getEnd()));
                workSchedule.setGradeId(v.getGradeId());
                scheduleDao.update(workSchedule);
            }
        }


        return ResponseEntity.isOk("操作成功!");
    }

    @Override
    public List<WorkSchedule> loadWorkSchedule(String schoolCode,String timeType, Long gradeId) {
        return scheduleDao.findSchedule(schoolCode,timeType,gradeId);
    }


    private Time transTime(String timeStr) {
        if (ComUtil.stringIsNotNull(timeStr)) {
            String[] timeArr = timeStr.split(":");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeArr[0]));
            calendar.set(Calendar.MINUTE, Integer.valueOf(timeArr[1]));
            calendar.set(Calendar.SECOND, 00);
            return new Time(calendar.getTime().getTime());
        }
        return null;

    }


}
