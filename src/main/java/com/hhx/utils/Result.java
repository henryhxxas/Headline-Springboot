package com.hhx.utils;

/**
 * @Author: hhx
 * @Date: 2024/5/15 10:03
 * @Description: TODO 全局统一返回结果类
 * @Version: 1.0
 */
public class Result<T> {
    //返回码
    private Integer code;
    //返回消息
    private String message;
    //返回数据
    private T data;
    public Result(){}

    protected static <T> Result<T> build(T data){
        Result<T> result = new Result<T>();
        if(data!=null) result.setData(data);
        return result;
    }
    public static <T> Result<T> build(T body,Integer code,String message){
        Result<T> result = build(body);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
    public static <T> Result<T> build(T body,ResultCodeEnum resultCodeEnm){
        Result<T> result = build(body);
        result.setCode(resultCodeEnm.getCode());
        result.setMessage(resultCodeEnm.getMessage());
        return result;
    }
    /**
     * 操作成功
     */
    public static <T> Result<T> ok(T data){
        Result<T> result=build(data);
        return build(data,ResultCodeEnum.SUCCESS);
    }
    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    public  Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 获取
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * 设置
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }


}
