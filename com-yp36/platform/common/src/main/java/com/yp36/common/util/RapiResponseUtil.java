package com.yp36.common.util;

import com.yp36.common.constant.ResponseInfo;
import com.yp36.common.http.vo.BaseHttpResponse;

public class RapiResponseUtil {

	public static String responseSuccess(ResponseInfo info, Object result) {
		BaseHttpResponse<Object> response = new BaseHttpResponse<Object>();
		response.setStatus(info.getCode());
		response.setMessage(info.getMsg());
		//return response;


		return response.toString();
	}

}
