package com.yp36.common.exception;

import com.yp36.common.http.vo.BaseHttpResponse;
import com.yp36.common.http.vo.HttpCommons;
import com.yp36.common.util.HttpUtil;
import com.yp36.common.exception.BizException;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * Created by yangpeng on 2017/06/13.
 */
public class SimpleMappingExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)	{
        if (ex instanceof BizException) {
            return this.bizExceptionHandler(request, response, handler, ex);
        } else {
            return this.defaultExceptionHandler(request, response, handler, ex);
        }
	}

    private ModelAndView bizExceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            BaseHttpResponse response_ = new BaseHttpResponse();
            BizException exception = (BizException) ex;
            int status = exception.getCode() == 0 ? HttpCommons.Response.BIZ_ERROR.getCode() : exception.getCode();
            response_.setStatus(status);
            response_.setMessage(ex.getMessage());
            response.setHeader("Content-type", HttpCommons.APPLICATION_JSON);
            ex.printStackTrace();
            output.write(response_.toString().getBytes(HttpCommons.UTF_8));
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtil.safeCloseStream(output);
        }
        return null;
    }

    private ModelAndView defaultExceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            BaseHttpResponse response_ = new BaseHttpResponse();
            response_.setStatus(HttpCommons.Response.UNKNOWN_ERROR.getCode());
            response_.setMessage(HttpCommons.Response.UNKNOWN_ERROR.getMessage());
            response.setHeader("Content-type", HttpCommons.APPLICATION_JSON);
            ex.printStackTrace();
            output.write(response_.toString().getBytes(HttpCommons.UTF_8));
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            HttpUtil.safeCloseStream(output);
        }
        return null;
    }

}
