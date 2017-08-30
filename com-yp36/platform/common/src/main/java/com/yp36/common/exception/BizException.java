package com.yp36.common.exception;

import lombok.Getter;

/**
 * desc
 * Created by yangpeng on 2017/06/13.
 */
@Getter
public  class BizException extends RuntimeException {

    private int code;

    private String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
