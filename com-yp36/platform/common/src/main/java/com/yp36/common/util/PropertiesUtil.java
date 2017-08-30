package com.yp36.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Properties;

/**
 * Created by yangpeng on 2017/06/11.
 */
@Slf4j
public class PropertiesUtil {
    /**
     *
     */
    public static void reLoad(Properties properties, String filePath) {

        try {
            InputStream inputStream = new FileInputStream(filePath);
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "gbk"));
                properties.load(bf);
            } catch (IOException e) {
                log.error("Config loading: IO exception. ", e);
            }
        } catch (FileNotFoundException e) {
            log.error("Config loading: File not found exception. ", e);
        }
    }

    /**
     *
     *
     * @return
     */
    public static String getFilePath(String fileName) {
        String path = Thread.currentThread().getContextClassLoader().getResource(fileName).getPath();
        return path;
    }
}
