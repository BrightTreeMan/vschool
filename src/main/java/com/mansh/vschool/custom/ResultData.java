package com.mansh.vschool.custom;

import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 全局返回数据，所有REST接口都返回该类的实例
 */
@ApiModel(value="REST接口返回参数")
public class ResultData<T> {
    @ApiModelProperty(value = "连接成功标识:true 成功,false 失败")
    private boolean success;

    @ApiModelProperty(value = "返回状态码 1 正常  2 刷新token -1 包装异常，展示给前端 -2 系统出错，请联系管理员 -3 系统出错，请联系管理员 -4 不在库里的url")
    private String code;

    @ApiModelProperty(value = "描述信息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;

    public ResultData(){

    }

    public ResultData(T data){
        this.data = data;
    }

    public boolean getSuccess() {
        return success;
    }

    public ResultData setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResultData setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultData setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultData setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("success",success);
        json.put("message",message);
        json.put("code",code);
        json.put("data",data);
        return json.toString();
    }
}
