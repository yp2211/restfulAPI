package com.yp36.common.session;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

/**
 * Session object
 * Created by yangpeng on 2017/06/14.
 */
@Getter
@Setter
@NoArgsConstructor
public class WebSession {
    private static final String ATTR_NAME_CREATION_TIME = "creationTime";
    private static final String ATTR_NAME_LAST_ACCESSED_TIME = "lastAccessedTime";
    private String jsessionId;
    private long creationTime;
    private Map<String, Object> attributes = Collections.emptyMap();

    /**
     * @param jsessionId
     * @param attributes
     */
    public WebSession(String jsessionId, Map<String, Object> attributes) {
        this.jsessionId = jsessionId;
        this.creationTime = System.currentTimeMillis();
        this.attributes = attributes;
    }
}
