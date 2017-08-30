package com.yp36.common.poi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yangpeng on 2017/06/12.
 */
public interface ExcelService {
    void download(HttpServletResponse response, String filename, Map<String, Object> beans, String templatePath) throws IOException;

    void upload(HttpServletRequest request, Map beans, String templatePath) throws Exception;
}
