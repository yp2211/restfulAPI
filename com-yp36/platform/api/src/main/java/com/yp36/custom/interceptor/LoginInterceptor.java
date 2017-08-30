/**
 *
 */
package com.yp36.custom.interceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yp36.common.exception.BizException;
import com.yp36.common.http.vo.HttpCommons;
import com.yp36.common.session.WebSession;
import com.yp36.common.session.WebSessionManager;

/**
 * @author yangpeng
 *
 */
public class LoginInterceptor implements HandlerInterceptor, InitializingBean {

    private List<String> whiteList = Collections.emptyList();

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
/*
    	String uri = httpServletRequest.getRequestURI();

        if (!whiteList.contains(uri)) {
            WebSession session_ =
            		WebSessionManager.getInstance().findSession(httpServletRequest.getSession().getId());
            if (null == session_) {
                throw new BizException(HttpCommons.SUCCESS, "you haven't login yet");
            }
        }
*/
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //do nothing
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //do nothing
    }

    public void afterPropertiesSet() throws Exception {
        this.whiteList = Arrays.asList(
        		"/login",
                "/login/loginVerify",
                "/login/getVerifyCode",
                "/login/setVerifyCode");
    }
}
