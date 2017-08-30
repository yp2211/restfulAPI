package com.yp36.api.controller;

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
        //response.setCount(1);
        //if (response.getCount() != 0)
        response.setData("Hello agri. ");
        response.setStatus(200);
        return response;
    }
}
