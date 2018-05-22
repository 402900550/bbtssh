package com.hfkj.bbt.systemanage.service;

import com.hfkj.bbt.base.entity.Classes;
import com.hfkj.bbt.base.entity.Grade;
import com.hfkj.bbt.base.page.Page;
import com.hfkj.bbt.systemanage.web.vo.ClassVo;
import com.hfkj.bbt.systemanage.web.vo.SearchVo;

import java.util.List;

/**
 * Created by Administrator on 2017-10-31.
 */
public interface IClassService {

    Page selectClass(SearchVo searchVo);
    //添加班级
    int doAddClasses(ClassVo classVo);
    //删除班级
    int deleteClasses(Classes classes);
    //修改班级
    int updateClassList(ClassVo classVo);

    List<Grade> getGradeList(String schoolCode);

    List<Classes> getClassesByGrade(String schoolCode,Long gradeId);

    List<Grade> getGradeList();

    Classes getClassById(Long classId);

    /**
     * 根据roomId查询是否绑定班级
     * @param roomId
     * @return
     */
    Classes getClassRoomById(Long roomId);

}
