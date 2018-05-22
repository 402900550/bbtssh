package com.hfkj.bbt.base.util;


import com.hfkj.bbt.base.entity.Role;
import com.hfkj.bbt.base.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-06-26.
 */
public class ComUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ComUtil.class);
    private ComUtil(){

    }


    public static boolean listNotNullAndEmpty(List list){
        return list!=null&&!list.isEmpty();
    }


    public static boolean stringIsNotNull(String... string){
        boolean flag=true;
        for (String str:string){
            boolean b = null != str && !"".equals(str);
            if(!b){
                flag=false;
                break;
            }
        }
        return flag;
    }

    /**
     * 设置数据库模糊查询的值
     * @param value
     * @return
     */
    public static String checkValue(String value){
        String str=value;
        if (stringIsNotNull(str)){
            return "%"+str+"%";
        }else {
            return "%%";
        }
    }


    /**
     * 判断当前角色是否有权限操作
     * @param operateSchoolCode 操作的学校
     * @return boolean true拥有 false没有权限
     */
    public static boolean haveAuthority(String operateSchoolCode){
        User currentUser = UserUtil.getCurrentUser();
        Role role = UserUtil.getCurrentMostHeighRole();
        Integer roleLevel = role.getRoleLevel();
        if (roleLevel>=Role.EDUCATION){
            return true;
        }

        String schoolCode = currentUser.getSchoolCode();

        if (stringIsNotNull(schoolCode)&&stringIsNotNull(operateSchoolCode)&&operateSchoolCode.equalsIgnoreCase(schoolCode)
                &&roleLevel>=Role.ADMIN){
            return true;
        }

        return false;
    }


















    /**
     * 将查询出来的数据转换为对象
     * @param list
     * @param clazz
     * @param <T>
     * @return
     */
    public  static <T> List<T> tranToObject(List<Object[]> list,Class<T> clazz) {
        List<T> ts=new ArrayList<T>();
        try {
            Field[] fields = clazz.getDeclaredFields();
            ArrayList<String > methods=new ArrayList<String >();
            ArrayList<Class<?> > types=new ArrayList<Class<?> >();
            for (Field field:fields){
                String name = field.getName();
                name=name.substring(0,1).toUpperCase()+name.substring(1);
                String methodName="set"+name;
                types.add(field.getType());
                methods.add(methodName);
            }

            for (int i=0;i<list.size();i++){
                Object[] obj=list.get(i);
                T t = clazz.newInstance();
                for (int j=0;j<obj.length;j++){
                    clazz.getDeclaredMethod(methods.get(j),types.get(j)).invoke(t,obj[j]);
                }
                ts.add(t);
            }

        } catch (InstantiationException e) {
            LOG.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
        } catch (NoSuchMethodException e){
            LOG.error(e.getMessage());
        } catch (InvocationTargetException e){
            LOG.error(e.getMessage());
        }
        return ts;
    }









}
