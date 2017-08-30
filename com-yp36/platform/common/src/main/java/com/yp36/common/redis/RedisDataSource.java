package com.yp36.common.redis;

import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

/**
 * Created by yangpeng on 2017/6/12.
 */
@Service
public interface RedisDataSource {
    /**
     *
     * @return
     */
    ShardedJedis getRedisClient() throws Exception;
}
