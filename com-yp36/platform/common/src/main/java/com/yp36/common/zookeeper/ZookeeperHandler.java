package com.yp36.common.zookeeper;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 *
 * Created by yangpeng on 2016/11/11.
 */
@Slf4j
@Getter
@Setter
public class ZookeeperHandler {

    private String zookeeperConnectionString;

    private int interval;

    private int retryTime;

    private int connectionTimeoutMs;

    private int sessionTimeoutMs;

    private CuratorFramework client;

    /**
     *
     *
     * @param retryPolicy
     * @return
     */
    public CuratorFramework createWithOptions(RetryPolicy retryPolicy) {
        return CuratorFrameworkFactory.builder()
                .connectString(zookeeperConnectionString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }

    /**
     *
     *
     * @return
     */
    public CuratorFramework createSimple() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(interval, retryTime);
        return CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
    }

    /**
     *
     */
    public CuratorFramework connect() {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(interval, retryTime);
        client = createWithOptions(retryPolicy);
        client.start();
        return client;
    }

    /**
     *
     *
     * @param path
     * @return
     * @throws Exception
     */
    public boolean checkExist(String path) throws Exception {
        if (client.checkExists().forPath(path) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     */
    public void close() {
        CloseableUtils.closeQuietly(client);
    }


    /**
     *
     * @param path
     * @return
     */
    public String getData(String path) {
        try {
            return new String(client.getData().forPath(path));
        } catch (Exception e) {
            log.error("Getting data failed." + e.getMessage());
        }
        return null;
    }

    /**
     *
     * @param path
     * @param data
     */
    public void updateNodeDate(String path, String data) {
        try {
            client.setData().forPath(path, data.getBytes());
        } catch (Exception e) {
            log.error("Data updating failed" + e.getMessage());
        }
    }

    /**
     *
     * @param path
     */
    public void deleteNode(String path) {
        try {
            client.delete().forPath(path);
        } catch (Exception e) {
            log.error("Data deleting failed." + e.getMessage());
        }
    }
}
