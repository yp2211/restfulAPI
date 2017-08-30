package com.yp36.common.poi.impl;

import com.yp36.common.poi.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yangpeng on 2017/06/12.
 */
@Slf4j
@Component
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    CommonsMultipartResolver multipartResolver;

    /**
     *
     * @param response
     * @param filename
     */
    public static void initialResponse(HttpServletResponse response, String filename) {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

	public void download(HttpServletResponse response, String filename, Map<String, Object> beans, String templatePath)
			throws IOException {

        initialResponse(response, filename);
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath);
        OutputStream os = response.getOutputStream();
        Context context = new Context();
        if (MapUtils.isNotEmpty(beans)) {
            for (String key : beans.keySet()) {
                context.putVar(key, beans.get(key));
            }
        }
        JxlsHelper.getInstance().processTemplate(is, os, context);
	}

    /**
     *
     * @param request
     * @param beans
     * @throws Exception
     */
    public void upload(HttpServletRequest request, Map beans, String templatePath) throws Exception {
        long start_time = System.currentTimeMillis();
        InputStream inputXML = Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath);
        XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator item = multiRequest.getFileNames();
            while (item.hasNext()) {
                MultipartFile file = multiRequest.getFile(item.next().toString());
                if (file != null) {
                    mainReader.read(file.getInputStream(), beans);
                }
            }
        }
        long end_time = System.currentTimeMillis();
        log.debug(">>>>>>>>>>>file upload takes {} ms", end_time - start_time);
    }

}