package com.cqie.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }
    public static Result success() {
        return new Result(200, "success", null);
    }

    public static Result fail(String msg) {
        return new Result(500, msg, null);
    }
    public static Result fail() {
        return new Result(500, "程序错误", null);
    }
}

