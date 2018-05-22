package com.hfkj.bbt.systemanage.service.impl;

import com.hfkj.bbt.base.dao.*;
import com.hfkj.bbt.base.entity.*;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.base.util.ComUtil;
import com.hfkj.bbt.systemanage.service.IClassService;
import com.hfkj.bbt.systemanage.web.vo.ClassVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017-10-31.
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ClassServiceImpl implements IClassService {


    @Autowired
    private SystemanageDao systemanageDao;
    @Autowired
    private ClassDao classDao;


    @Autowired
    private GradeDao gradeDao;


    @Autowired
    private ClassRoomDao classRoomDao;

    @Override
    public Page selectClass(SearchVo searchVo) {
        return systemanageDao.selectClass(searchVo);
    }

    /**
     * 添加班级 并绑定
     *
     * @param classVo
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int doAddClasses(ClassVo classVo) {
        Classes classes=new Classes();
        classes.setGradeId(classVo.getGradeId());
        classes.setClassName(classVo.getClassName());
        classes.setCreateDate(new Date());
        classes.setSchoolCode(classVo.getSchoolCode());
        if (classVo.getRoomId()!=null){
            classes.setRoomId(classVo.getRoomId());
            //表示绑定了教室
            classes.setStatus(1);
            setRoomStatus(classVo.getRoomId(),1);
        }else {
            classes.setStatus(0);
        }
        classDao.save(classes);
        return 1;

    }

    @Override
    @Transactional(readOnly = false)
    public int deleteClasses(Classes classes) {
        Long roomId = classes.getRoomId();
        if (roomId!=null){
            return 0;
        }
        classDao.delete(classes);
        return 1;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateClassList(ClassVo classVo) {
        Long classId = classVo.getClassId();
        Classes aClass = classDao.getClass(classId);
        aClass.setClassName(classVo.getClassName());
        aClass.setGradeId(classVo.getGradeId());
        Long roomId = classVo.getRoomId();
        Long oldRoomId = aClass.getRoomId();

        if (roomId!=null){
            setClassStatus(classId,1);
        }else {
            setClassStatus(classId,0);
        }

        aClass.setRoomId(roomId);
        //还原以前房间的状态
        setRoomStatus(oldRoomId,0);
        //设置新房间状态
        setRoomStatus(roomId,1);
        return 1;
    }

    @Override
    public List<Grade> getGradeList(String schoolCode) {
        return gradeDao.getGrades(schoolCode);
    }

    @Override
    public List<Classes> getClassesByGrade(String schoolCode, Long gradeId) {
        return classDao.getClassBySchoolException(schoolCode,gradeId);
    }


    @Override
    public List<Grade> getGradeList() {
        return gradeDao.getGrades();
    }

    @Override
    public Classes getClassById(Long classId) {
        return classDao.getClass(classId);
    }



    private void setRoomStatus(Long classroomId, int status) {
        ClassRoom classRoom = classRoomDao.getClassRoom(classroomId);
        if (classRoom != null) {
            classRoom.setStatus(status);
            classRoomDao.update(classRoom);
        }
    }

    private void setClassStatus(Long classId, int status) {
        Classes aClass = classDao.getClass(classId);
        if (aClass != null) {
            aClass.setStatus(status);
            classDao.update(aClass);
        }
    }


    public Classes getClassRoomById(Long roomId){
        return classDao.getClassRoomId(roomId);
    }


}
