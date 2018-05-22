package com.hfkj.bbt.base.entity;

/**
 * Created by Administrator on 2017-10-25.
 */
public class ResponseEntity {

    private  boolean success;

    private  String message;

    private  Object data;

    private static ResponseEntity ENTITY=new ResponseEntity();


    public static ResponseEntity isOk(String message){
        ENTITY.setMessage(message);
        ENTITY.setSuccess(true);
        return ENTITY;
    }


    public static ResponseEntity isOk(String message,Object data){
        ENTITY.setMessage(message);
        ENTITY.setData(data);
        ENTITY.setSuccess(true);
        return ENTITY;
    }


    public static ResponseEntity isFail(String message){
        ENTITY.setMessage(message);
        ENTITY.setSuccess(false);
        return ENTITY;
    }


    public static ResponseEntity isFail(String message,Object data){
        ENTITY.setMessage(message);
        ENTITY.setData(data);
        ENTITY.setSuccess(false);
        return ENTITY;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
