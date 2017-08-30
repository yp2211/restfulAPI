package com.yp36.common.util;

import com.yp36.common.session.WebSession;
import com.yp36.common.session.WebSessionManager;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

/**
 * Created by yangpeng on 2017/06/14.
 */
public class WebUtil {
    /**
     * @return
     */
    private static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        return request;
    }

    /**
     *
     *
     * @return
     */
    private static WebSession getSession() {
        String jsessionId = getRequest().getRequestedSessionId();
        Map<String, Object> defaultMap = Collections.emptyMap();

        if (StringUtil.isNotEmpty(jsessionId)) {
            WebSessionManager sessionManager = WebSessionManager.getInstance();
            WebSession session = sessionManager.findSession(jsessionId);
            if (null == session) {
                session = new WebSession(getRequest().getSession().getId(), defaultMap);
            }
            return session;
        }
        else {
        	return new WebSession(getRequest().getSession().getId(), defaultMap);
        }
    }

    /**
     * @param key
     * @return
     */
    public static void getAttribute(String key, Object object) throws Exception {
        BeanUtils.populate(object, (Map) getSession().getAttributes().get(key));
    }

    /**
     * @param key
     * @param object
     */
    public static void setAttribute(String key, Object object) {
        WebSession session = getSession();
        session.getAttributes().put(key, object);
        WebSessionManager.getInstance().restoreSession(session);
    }

    /**
     * @param attributes
     */
    public static void initSession(Map<String, Object> attributes) {
        WebSession session = getSession();
        session.setAttributes(attributes);
        WebSessionManager.getInstance().createSession(session);
    }

    /**
     */
    public static void destroySession() {
        WebSessionManager.getInstance().removeSession(getSession());
    }
}
