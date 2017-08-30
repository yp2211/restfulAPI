package com.yp36.common.redis.exception;

/**
 * desc
 * Created by yangpeng on 2017/1/12.
 */
public class RedisAccessException extends Exception {
    private static final long serialVersionUID = 5148369359519294663L;

    public RedisAccessException(String msg) {
        super(msg);
    }

    public RedisAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RedisAccessException(Throwable cause) {
        super(cause);
    }
}
