package com.yp36.common.redis.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * desc
 * Created by lu_mian on 2017/1/12.
 */
@Getter
@Setter
public class RedisCommon {
    public static final String SHARD_HOST_SEPARATOR = "_";

    public static final String MASTER_SLAVE_SEPARATOR = "#";

    public static final String COMMON_SEPARATOR = ":";

    public static final String CONF_ERROR = "host configuration error, sample as ip:port#ip:port#timeout_ip:port#ip:port#timeout";
}
