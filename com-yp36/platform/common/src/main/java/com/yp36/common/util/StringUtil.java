package com.yp36.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by yangpeng on 2016/10/9.
 */
@Slf4j
public class StringUtil {

    public static boolean isEmpty(String str) {
    	if (str == null || str.equals("") || "undefined".equals(str)|| "null".equals(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     *
     *
     * @param name
     * @return
     */
    public static String genarateFileName(String name, String postfix) {
        String random_str = TimeUtil.getCurrentDate("yyyyMMddHHmmss");
        return name.concat("_").concat(random_str).concat(postfix);
    }

    /**
     *
     *
     * @param stubFileName
     * @return
     */
    private static String getStubFilePath(String stubFileName) {
        return Thread.currentThread().getContextClassLoader().getResource("stub/".concat(stubFileName)).getPath();
    }

    /**
     *
     *
     * @param stubFileName
     * @return
     */
    public static String getResponseFromStub(String stubFileName) {
        String filePath = getStubFilePath(stubFileName);
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        String str = null;
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
            reader = new BufferedReader(isr);
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            reader.close();
        } catch (IOException e) {
            log.error("Response from stub IO exception. " + filePath);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("IO steam close failed. ", e);
                }
            }
        }
        return sb.toString();
    }
}
