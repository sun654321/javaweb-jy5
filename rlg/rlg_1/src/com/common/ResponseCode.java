package com.common;

public class ResponseCode<T> {

    private  Integer status;
    private  T data;
    private  String  msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


//成功的时候只要返回状态和成功信息
    public static <T> ResponseCode seccessRs(Integer status,T data){
        ResponseCode rs=new ResponseCode();
        rs.setStatus(status);
        rs.setData(data);
        return rs;
    }
    public static <T> ResponseCode seccessRs(T data){
        ResponseCode rs=new ResponseCode();
        rs.setStatus(0);
        rs.setData(data);
        return rs;
    }
    //失败返回状态码和失败信息
    public static <T> ResponseCode notseccessRs(Integer status,String mag){
        ResponseCode rs=new ResponseCode();
        rs.setStatus(status);
        rs.setData(mag);
        return rs;
    }


    @Override
    public String toString() {
        return "ResponseCode{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
