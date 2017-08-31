package com.yp36.common.session;

import com.yp36.common.redis.Yp36RedisClient;
import com.yp36.common.redis.exception.RedisAccessException;
import com.yp36.common.util.BeanUtil;
import com.yp36.common.util.JsonUtil;
import com.yp36.common.util.LoaderConfig;
import com.yp36.common.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 *
 * Created by yangpeng on 2017/06/14.
 */
@Getter
@Setter
@Slf4j
public class WebSessionManager {
    private static final int DEFAULT_SESSION_TIME_OUT = 12 * 60 * 60;
    private static WebSessionManager instance = new WebSessionManager();
    private Yp36RedisClient accfRedisClient = (Yp36RedisClient) BeanUtil.getBean("yp36RedisClient");

    /**
     * @return
     */
    public static WebSessionManager getInstance() {
        return instance;
    }

    /**
     * @return
     * @throws Exception
     */
    public void createSession(WebSession session) {
        try {
            accfRedisClient.setex(session.getJsessionId(), LoaderConfig.getObjectInt("cf.session.timeout", DEFAULT_SESSION_TIME_OUT), JsonUtil.beanToJson(session));
        } catch (Exception e) {
            log.error("failed to createSession.", e);
        }
    }

    /**
     * @param jsessionId
     * @return
     * @throws Exception
     */
    public WebSession findSession(String jsessionId) {
        String json_str = null;
        try {
            json_str = accfRedisClient.get(jsessionId);
        } catch (RedisAccessException e) {
            log.error("failed to findSession", e);
        }

        if (StringUtil.isEmpty(json_str)) {
            return null;
        }

        try {
            return JsonUtil.jsonToBean(json_str, WebSession.class);
        } catch (IOException e) {
            log.error("failed to findSession", e);
        }
        return null;
    }

    /**
     * @param session
     * @throws Exception
     */
    public void removeSession(WebSession session) {
        try {
            accfRedisClient.del(session.getJsessionId());
        } catch (RedisAccessException e) {
            log.error("failed to removeSession", e);
        }
    }

    /**
     *
     *
     * @param session
     */
    public void restoreSession(WebSession session) {
        try {
            accfRedisClient.set(session.getJsessionId(), JsonUtil.beanToJson(session));
        } catch (Exception e) {
            log.error("restoreSession failed.", e);
        }
    }
}
