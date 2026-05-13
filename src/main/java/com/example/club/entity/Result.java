package com.example.club.entity;


import lombok.Data;


@Data
public class Result {

    private Integer code;   // 状态码
    private String msg;     // 提示信息
    private Object data;         // 数据

    public Result() {}

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 成功返回（不带数据）
    public static  Result success() {
        Result result = new Result();
        result.code = 200;
        result.msg = "success";
        return result;
    }
    public static  Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 200;
        result.msg = "success";
        return result;
    }
    public static  Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 500;
        return result;
    }



}
