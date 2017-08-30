package com.yp36.common.redis.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * desc
 * Created by lu_mian on 2017/1/12.
 */
@Getter
@Setter
public class RedisNodeInfo {
    private String ip;

    private int port;

    private int timeout;

    public RedisNodeInfo(String ip, int port, int timeout) {
        this.ip = ip;
        this.port = port;
        this.timeout = timeout;
    }
}
