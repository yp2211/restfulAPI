package com.yp36.common.http.impl;

import com.yp36.common.exception.BizException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class AbstractHttpService<R> {
	private static final String CHARSET_UTF8 = "UTF-8";
	@Autowired
	private RestTemplate restTemplate;

	private static List<NameValuePair> getParamsList(Map<String, Object> paramsMap) {
		if (paramsMap != null && paramsMap.size() != 0) {
			ArrayList params = new ArrayList();
			Iterator arg2 = paramsMap.entrySet().iterator();

			while (arg2.hasNext()) {
				Entry map = (Entry) arg2.next();
				params.add(new BasicNameValuePair((String) map.getKey(), String.valueOf(map.getValue())));
			}

			return params;
		} else {
			return null;
		}
	}

	public R executeHttp(Map<String, Object> arg0, Map<String, Object> arg1, HttpHeaders arg2, Class<R> arg3)
			throws BizException {
		throw new Error(
				"Unresolved compilation problems: \n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tBaseHttpResponse を型に解決できません\n\tBaseHttpResponse を型に解決できません\n\tHttpCommons を解決できません\n\tBaseHttpResponse を型に解決できません\n\tHttpCommons を解決できません\n");
	}

	protected abstract void requestValidator(Map<String, Object> arg0) throws BizException;

	private R executeInternal(Map<String, Object> arg0, Map<String, Object> arg1, HttpHeaders arg2, Class<R> arg3) {
		throw new Error(
				"Unresolved compilation problems: \n\tHttpCommons を変数に解決できません\n\tlog を解決できません\n\tHttpCommons を変数に解決できません\n\tStringUtil を解決できません\n\tHttpCommons を変数に解決できません\n\tlog を解決できません\n\tJsonUtil を解決できません\n\tlog を解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tHttpCommons を変数に解決できません\n\tlog を解決できません\n\tlog を解決できません\n");
	}

	private String genTempUrl(String url_, Map<String, Object> params) {
		List q_params = getParamsList(params);
		if (q_params != null && q_params.size() > 0) {
			String formatParams = URLEncodedUtils.format(q_params, "UTF-8");
			int index = url_.indexOf("?");
			url_ = index < 0 ? url_ + "?" + formatParams : url_.substring(0, index + 1) + formatParams;
		}

		return url_;
	}
}
