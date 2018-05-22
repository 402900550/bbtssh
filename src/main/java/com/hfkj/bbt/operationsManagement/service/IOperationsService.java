package com.hfkj.bbt.operationsManagement.service;

import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.operationsManagement.web.vo.ExceptionVo;
import com.hfkj.bbt.operationsManagement.web.vo.OperationVo;
import com.hfkj.bbt.operationsManagement.web.vo.PendingVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/3 0003.
 */
public interface IOperationsService {

    /**
     * 根据用户id查询设备报修情况
     * @return
     */
    List<OperationVo> selectOperationsPending(String schoolCode,String roleName,Long userId);

    /**
     * 查询我上报的异常
     * @return
     * @param toPage
     * @param pageSize
     */
    Page selectOperationsMyList(Integer toPage, Integer pageSize,Integer state,String schoolCode);



    void addHandException(ExceptionVo vo);


    Map<String,Object> lookProcessDetailHis(String processId, String taskId);



    void doCompleteHandTask(PendingVo vo);

    /**
     * 运维首页显示（图）
     * @return
     */
    List getOperations();

    void applicationForScrap(Long accessoryId, Long classId);

    /**
     * 是否能申请报废
     * @param accessoryId
     * @return
     */
    boolean isCanScrap(Long accessoryId);
}
