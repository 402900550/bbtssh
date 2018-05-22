package com.hfkj.bbt.assetmanage.service;


import com.hfkj.bbt.assetmanage.web.vo.AssetSearchVo;
import com.hfkj.bbt.assetmanage.web.vo.EquipmentVo;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.BundingEquipmentVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-10-26.
 */
public interface IEquipmentService {

    /**
     * 保存配套
     */
    ResponseEntity saveAccessoryType(EquipmentVo vo);


    Page loadEquimentAndClassRoom();

    /**
     * 查询学校设备统计
     * @param assetSearchVo
     * @return
     */
    Page selectEquimentClassRoom(AssetSearchVo assetSearchVo);

    /**
     * 查询学校设备
     * @param
     * @return
     */
    Page loadSchoolAc (SearchVo searchVo);

    /**
     * 查询学校年级班级设备
     * @param assetSearchVo
     * @return
     */
    Page loadClassRoomEquiment(AssetSearchVo assetSearchVo);

    /**
     * 查询学校班级年级
     * @param classId
     * @return
     */
    Page selectSchoolClassAndClassRoom(Long classId);

    /**
     * 查询班级所有设备
     * @param classId
     * @param startNumber
     * @param sizeNumber
     * @return
     */
    Page selectEquipmentClassRoomList(Long classId,int startNumber,int sizeNumber);


    void doBundingEquipments(BundingEquipmentVo vo);

    Map<String,Object> loadModifyAccessory(Long roomId);

    /**
     * 查询设备详情
     * @param ids
     * @return
     */
    Accessory findAccessoryClassList(Long ids);

    /**
     * 查询型号
     * @param id
     * @return
     */
    DevelopType getDevelop(Long id);

    /**
     * 设备类型
     * @param id
     * @return
     */
    Parameters getParametersById(Long id);


    void doModifyBundingEquipments(BundingEquipmentVo vo);

    List<Accessory> getClassRoomIdAccessriesById(Long roomId);
}

