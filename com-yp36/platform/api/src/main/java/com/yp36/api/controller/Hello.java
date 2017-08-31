package com.yp36.api.controller;

import com.yp36.common.http.vo.HttpCommons;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yp36.common.http.vo.BaseHttpResponse;

@Controller
@RequestMapping("/http")
public class Hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
	public BaseHttpResponse<String> selectAllFactor() {
        BaseHttpResponse response = new BaseHttpResponse();
        response.setCount(0);
        response.setData("Hello !");
        response.setStatus(HttpCommons.SUCCESS);
        return response;
    }
}
