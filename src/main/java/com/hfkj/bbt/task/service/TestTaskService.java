package com.hfkj.bbt.task.service;

import com.hfkj.bbt.base.dao.ObjectDao;
import com.hfkj.bbt.base.entity.TestTaskEntity;
import com.hfkj.bbt.base.util.ComUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@EnableScheduling
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class TestTaskService {

    private final static Logger log=Logger.getLogger(TestTaskService.class);

    @Autowired
    private ObjectDao objectDao;

    /**
     * 每天8点30开启设备 记录没有开启的设备号
     */
    @Scheduled(cron = "0 32 08 * * ?")
    @Transactional(readOnly = false)
    public void run0830(){
        log.info("开始记录9点半没有使用的设备");
        write(0);
        log.info("记录结束");
    }

    /**
     * 每天9点10开启设备 记录没有关闭的设备号
     */
    @Scheduled(cron = "0 12 09 * * ?")
    @Transactional(readOnly = false)
    public void run0910(){
        log.info("开始记录9点半没有使用的设备");
        write(1);
        log.info("记录结束");
    }


    /**
     * 每天9点半开启设备 记录没有开启的设备号
     */
    @Scheduled(cron = "0 32 09 * * ?")
    @Transactional(readOnly = false)
    public void run0930(){
        log.info("开始记录9点半没有使用的设备");
        write(0);
        log.info("记录结束");
    }

    /**
     * 每天10点10开启设备 记录没有关闭的设备号
     */
    @Scheduled(cron = "0 12 10 * * ?")
    @Transactional(readOnly = false)
    public void run1010(){
        log.info("开始记录9点半没有使用的设备");
        write(1);
        log.info("记录结束");
    }

    /**
     * 每天10点半开启设备 记录没有开启的设备号
     */
    @Scheduled(cron = "0 32 10 * * ?")
    @Transactional(readOnly = false)
    public void run1030(){
        log.info("开始记录9点半没有使用的设备");
        write(0);
        log.info("记录结束");
    }

    /**
     * 每天11点10开启设备 记录没有关闭的设备号
     */
    @Scheduled(cron = "0 12 11 * * ?")
    @Transactional(readOnly = false)
    public void run1110(){
        log.info("开始记录9点半没有使用的设备");
        write(1);
        log.info("记录结束");
    }

    /**
     * 每天14点半开启设备 记录没有开启的设备号
     */
    @Scheduled(cron = "0 32 14 * * ?")
    @Transactional(readOnly = false)
    public void run1430(){
        log.info("开始记录9点半没有使用的设备");
        write(0);
        log.info("记录结束");
    }

    /**
     * 每天15点半开启设备 记录没有关闭的设备号
     */
    @Scheduled(cron = "0 12 15 * * ?")
    @Transactional(readOnly = false)
    public void run1510(){
        log.info("开始记录9点半没有使用的设备");
        write(1);
        log.info("记录结束");
    }

    /**
     * 每天15点半开启设备 记录没有开启的设备号
     */
    @Scheduled(cron = "0 32 15 * * ?")
    @Transactional(readOnly = false)
    public void run1530(){
        log.info("开始记录9点半没有使用的设备");
        write(0);
        log.info("记录结束");
    }

    /**
     * 每天16点10开启设备 记录没有关闭的设备号
     */
    @Scheduled(cron = "0 12 16 * * ?")
    @Transactional(readOnly = false)
    public void run1610(){
        log.info("开始记录9点半没有使用的设备");
        write(1);
        log.info("记录结束");
    }


    public void write(Integer workStatus){
        TestTaskEntity entity;
        List<Object[]> list = objectDao.testTaskEq(workStatus);
        if (ComUtil.listNotNullAndEmpty(list)){
            for (Object[] object:list){
                entity=new TestTaskEntity();
                entity.setEquipmentNo(String.valueOf(object[0]));
                if (object[1]!=null){
                    entity.setIp(String.valueOf(object[1]));
                }
                if (object[2]!=null){
                    entity.setTime((Date)object[2]);
                }
                if (object[3]!=null){
                    entity.setWorkStatus(Integer.valueOf(String.valueOf(object[3])));
                }
                entity.setCollectTime(new Date());
                objectDao.save(entity);
            }
        }
    }
}














