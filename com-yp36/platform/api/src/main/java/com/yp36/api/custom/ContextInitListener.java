/**
 *
 */
package com.yp36.api.custom;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

/**
 * @author yangpeng 2017/06/15
 *
 */
@Slf4j
@Component
public class ContextInitListener implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            log.info("#####################################################################");
            log.info("##");
            log.info("##               Agriculture Crowdfonding Platform");
            log.info("##               Database Monitor http://IP:PORT/druid");
            log.info("##               API Doc http://IP:PORT/swagger-ui.html");
            log.info("##");
            log.info("#####################################################################");
        }
    }
}
