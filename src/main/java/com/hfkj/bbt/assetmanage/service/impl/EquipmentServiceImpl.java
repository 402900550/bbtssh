package com.hfkj.bbt.assetmanage.service.impl;


import com.hfkj.bbt.assetmanage.web.vo.AssetSearchVo;
import com.hfkj.bbt.assetmanage.web.vo.EquipmentVo;
import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.assetmanage.service.IEquipmentService;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.base.util.DateUtil;
import com.hfkj.bbt.base.util.UserUtil;
import com.hfkj.bbt.systemanage.web.vo.BundingEquipmentVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.*;


/**
 * Created by Administrator on 2017-10-26.
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class EquipmentServiceImpl implements IEquipmentService {

    @Autowired
    private EquipmentDao equipmentDao;

    @Autowired
    private AccessoryTemplateDao accessoryTemplateDao;


    @Autowired
    private AccessoryDao accessoryDao;

    @Autowired
    private ClassDao classDao;

    @Autowired
    private ClassRoomDao classRoomDao;

    @Autowired
    private DevelopTypeDao developTypeDao;

    @Autowired
    private ParametersDao parametersDao;

    @Autowired
    private BuildingDao buildingDao;


    /**
     * 执行设备绑定
     *
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public void doBundingEquipments(BundingEquipmentVo vo){
        ClassRoom classRoom = classRoomDao.getClassRoom(vo.getRoomId());
        classRoom.setEquipmentNo(vo.getEquipmentNo());
        classRoomDao.update(classRoom);
    }




    public AccessoryTemplate getAccessorytTemplateById(Long saveId) {
        return accessoryTemplateDao.findAccessoryTemplateById(saveId);
    }

    /**
     * 保存配套并生成模板
     *
     * @param vo
     *
     */
    @Transactional(readOnly = false)
    public ResponseEntity saveAccessoryType(EquipmentVo vo) {
        List<Accessory> accessories = vo.getAccessories();
        ClassRoom classRoom = classRoomDao.getClassRoom(vo.getRoomId());
        classRoom.setCost(vo.getDatumCost() + vo.getPersonCost());
        classRoom.setEquipmentNo(vo.getEquipmentNo());
        classRoom.setManager(vo.getManager());
        classRoom.setDevelopTypeId(vo.getTypeLong());
        for (Accessory accessory : accessories) {
            Integer price = accessory.getPrice();
            Long sizeType = accessory.getSizeType();
            Long brand = accessory.getBrand();
            if ((brand != null && sizeType != null && price != null)||(brand == null && sizeType == null && price == null)) {

                if (brand != null && sizeType != null && price != null){
                    List<Accessory> accessoryByRoom = accessoryDao.findAccessoryByRoom(vo.getRoomId());
                    if (ComUtil.listNotNullAndEmpty(accessoryByRoom)) {
                        for (Accessory ca : accessoryByRoom) {
                            if (ca.getEquipmentType().equals(accessory.getEquipmentType())&&ca.getFacilities()!=3){
                                DevelopType develop = developTypeDao.getDevelop(ca.getEquipmentType());
                                return ResponseEntity.isFail(develop.getName()+"设备重复录入，请先将原来的报废处理!");
                            }

                        }
                    }
                    accessory.setRoomId(vo.getRoomId());
                    accessory.setFacilities(1);
                    accessory.setSchoolCode(vo.getSchoolCode());
                    accessory.setRoomId(vo.getRoomId());
                    accessory.setPurchaseDate(DateUtil.tranStringToDate("yyyy-MM-dd", vo.getInputDate()));
                    accessoryDao.update(accessory);
                }

            }else {
                return ResponseEntity.isFail("请将一套设备录入完整!");
            }

        }



        return ResponseEntity.isOk("保存成功!");

    }

    public Page loadEquimentAndClassRoom(){
        return equipmentDao.loadEquimentSchoolAndClassRoom();
    }

    @Override
    public Page selectEquimentClassRoom(AssetSearchVo assetSearchVo) {
        return equipmentDao.selectEquimentSchoolAndClassRoom(assetSearchVo);
    }

    @Override
    public Page loadSchoolAc(SearchVo searchVo) {
        return accessoryDao.findAccessoryList(searchVo);
    }

    /**
     * 查询学校年级班级设备
     * @param assetSearchVo
     * @return
     */
    public Page loadClassRoomEquiment(AssetSearchVo assetSearchVo) {
        return equipmentDao.selectClassRoomEquiment(assetSearchVo);
    }

    public Page selectSchoolClassAndClassRoom(Long classId) {
        return equipmentDao.selectSchoolClassAndClassRoom(classId);
    }

    public Page selectEquipmentClassRoomList(Long classId, int startNumber, int sizeNumber) {
        return equipmentDao.selectEquipmentClassRoomList(classId, startNumber, sizeNumber);
    }

    /**
     * 加载修改绑定设备需要的数据
     *
     * @param roomId
     * @return
     */
    @Override
    public Map<String, Object> loadModifyAccessory(Long roomId) {
        Map<String,Object> map=new HashMap<String,Object>();
        List<Accessory> accessories = accessoryDao.findAccessoryByRoom(roomId);
        ClassRoom classRoom = classRoomDao.getClassRoom(roomId);
        TeachBuilding building = buildingDao.findBuilding(classRoom.getBuildingId());
        map.put("accessories",accessories);
        map.put("classRoom",classRoom);
        DevelopType develop = developTypeDao.getDevelop(classRoom.getDevelopTypeId());
        List<DevelopType> allDevelops = developTypeDao.getDevelops();
        List<DevelopType> develops=new ArrayList<DevelopType>();
        if (develop!=null){
            develops = developTypeDao.getDevelops(develop.getId());
        }
        List<Accessory> allAccessories = accessoryDao.findAccessoryBySchoolAndDevelopType(building.getSchoolCode());
        map.put("allAccessories",allAccessories);
        map.put("develops",develops);
        map.put("allDevelops",allDevelops);
        return map;
    }

    /**
     * 查询设备详情
     * @param ids
     * @return
     */
    public Accessory findAccessoryClassList(Long ids) {
        return accessoryDao.findAccessoryClassList(ids);
    }

    /**
     * 查询型号
     * @param id
     * @return
     */
    public DevelopType getDevelop(Long id) {
        return developTypeDao.getDevelop(id);
    }

    /**
     * 设备类型
     * @param id
     * @return
     */
    public Parameters getParametersById(Long id){
        return parametersDao.getParametersById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void doModifyBundingEquipments(BundingEquipmentVo vo) {
        //先将设备初始化
        List<Accessory> accessoryByRoom = accessoryDao.findAccessoryByRoom(vo.getRoomId());
        if (accessoryByRoom!=null){
            for (Accessory accessory:accessoryByRoom){
                accessory.setRoomId(null);
            }
        }
        accessoryDao.update(accessoryByRoom);
        //再重新绑定
        List<Long> idList=new ArrayList<Long>();
        String ids = vo.getIds();
        if (ComUtil.stringIsNotNull(ids)){
            String[] idstrings = ids.split(",");
            for (String id:idstrings){
                idList.add(Long.valueOf(id));
            }
        }
        if (ComUtil.listNotNullAndEmpty(idList)){
            List<Accessory> accessoryList = accessoryDao.findAccessoryList(idList);
            for (Accessory accessory:accessoryList){
                accessory.setRoomId(vo.getRoomId());
            }
            accessoryDao.update(accessoryList);
        }
        ClassRoom classRoom = classRoomDao.getClassRoom(vo.getRoomId());
        classRoom.setCost(vo.getPersonCost());
        classRoom.setEquipmentNo(vo.getEquipmentNo());
        classRoom.setDevelopTypeId(vo.getDevelopType());
        classRoom.setManager(vo.getManager());
        classRoomDao.update(classRoom);
    }

    @Override
    public List<Accessory> getClassRoomIdAccessriesById(Long roomId) {
        return accessoryDao.findAccessoryByRoom(roomId);
    }

}







