package com.hfkj.bbt.systemanage.service;

import com.hfkj.bbt.base.entity.ResponseEntity;
import com.hfkj.bbt.base.entity.WorkSchedule;
import com.hfkj.bbt.systemanage.web.vo.WorkScheduleVo;

import java.util.List;

/**
 * Created by Administrator on 2017-12-11.
 */

public interface IScheduleService {

    ResponseEntity doSaveSchedule(List<WorkScheduleVo> vo);


    List<WorkSchedule> loadWorkSchedule(String schoolCode,String timeType, Long gradeId);

}
