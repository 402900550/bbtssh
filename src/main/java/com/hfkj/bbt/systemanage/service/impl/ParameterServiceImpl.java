package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.ParameterDao;
import com.hfkj.bbt.base.dao.ParameterTypeDao;
import com.hfkj.bbt.base.entity.ParameterType;
import com.hfkj.bbt.base.entity.Parameters;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.service.IParameterService;
import com.hfkj.bbt.systemanage.web.vo.ParameterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/11/7 0007.
 */
@Service
@Transactional(rollbackFor = Exception.class,readOnly = true)
public class ParameterServiceImpl implements IParameterService {


    @Autowired
    private ParameterDao parameterDao;

    @Autowired
    private ParameterTypeDao parameterTypeDao;


    @Override
    @Transactional(readOnly = false)
    public int addParameter(String detail,Long typeId,String remark ){

        Parameters p = new Parameters();
        p.setDetail(detail);
        p.setTypeId(typeId);
        p.setRemark(remark);
        parameterDao.save(p);
        return  1 ;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateParameter(Long PId ,String detail,Long typeId,String remark ){

        Parameters p = parameterDao.getParameters(PId);
        p.setDetail(detail);
        p.setTypeId(typeId);
        p.setRemark(remark);
        parameterDao.update(p);
        return  1 ;
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteParameter(Long PId ){
        Parameters p = parameterDao.getParameters(PId);
        parameterDao.delete(p);
        return  1 ;
    }


    @Override
    @Transactional(readOnly = false)
    public int addParameterType(String typeName){

        ParameterType pt = new ParameterType();
        pt.setName(typeName);
        parameterTypeDao.save(pt);
        return  1 ;
    }

    @Override
    public Page selectParameterList(ParameterVo vo) {
        return parameterDao.selectParameterList(vo);
    }

}
