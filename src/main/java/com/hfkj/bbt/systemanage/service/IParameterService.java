package com.hfkj.bbt.systemanage.service;


import com.hfkj.bbt.base.entity.TeachBuilding;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.ParameterVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
public interface IParameterService {

    /**
     * 添加参数
     */
     int addParameter(String detail,Long typeId,String remark );


    /**
     * 修改参数
     */
    int updateParameter(Long PId, String detail,Long typeId,String remark);

    /**
     * 删除参数
     */
    int deleteParameter(Long PId );

    /**
     * 添加参数类型
     */
    int addParameterType(String typeName);


    Page selectParameterList(ParameterVo vo);
}
