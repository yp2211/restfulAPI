package com.yp36.common.zookeeper;

import com.yp36.common.util.HttpUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;

/**
 * Created by yangpeng on 2016/11/10.
 */
@Service
@Slf4j
@Getter
@Setter
public class ZookeeperListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final String PARENT_NODE = "/catering_index";

    private static final String CHILD_NODE = PARENT_NODE.concat("/").concat(HttpUtil.getLocalIP());

    @Autowired
    ZookeeperHandler handler;

    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        try {
            if (applicationEvent.getApplicationContext().getParent() == null) {
//                CuratorFramework client = handler.connect();
//                createParentNode(client);
//                addPersisListener(client);
//                createChildNode(client);
//                addPersisListenerChild(client);
            }
        } catch (Exception e) {
            log.error("Zookeeper initial failed...", e);
        }
    }

    /**
     *
     * @param client
     * @throws Exception
     */
    public void createParentNode(CuratorFramework client) throws Exception {
        if (!handler.checkExist(PARENT_NODE)) {
            client.create().withMode(CreateMode.PERSISTENT).forPath(PARENT_NODE, ZookeeperCommon.NodeState.CURRENT.getCode().getBytes());
        } else {
            handler.updateNodeDate(PARENT_NODE, ZookeeperCommon.NodeState.CURRENT.getCode());
            log.info(PARENT_NODE.concat(">>>>>>>>>>status...{}"), ZookeeperCommon.NodeState.CURRENT.getMessage());
        }
    }

    /**
     *
     * @param client
     * @throws Exception
     */
    public void addPersisListener(CuratorFramework client) throws Exception {
        Executors.newCachedThreadPool();
        final NodeCache nodeCache = new NodeCache(client, PARENT_NODE, false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                String data = new String(nodeCache.getCurrentData().getData());
                noticeChildNode(data);
                log.info("the node is changed and result is :");
                log.info("path : " + nodeCache.getCurrentData().getPath());
                log.info("data : " + data);
                log.info("stat : " + nodeCache.getCurrentData().getStat());
            }
        });
        nodeCache.start();
    }

    /**
     *
     * @param data
     */
    private void noticeChildNode(String data) {
        if (ZookeeperCommon.NodeState.PROCESSING.getCode().equals(data)) {
            handler.updateNodeDate(CHILD_NODE, ZookeeperCommon.NodeState.BACKUP.getCode());
        } else if (ZookeeperCommon.NodeState.CURRENT.getCode().equals(data)) {
            handler.updateNodeDate(CHILD_NODE, ZookeeperCommon.NodeState.CURRENT.getCode());
        }
    }

    /**
     * @param client
     * @throws Exception
     */
    public void createChildNode(CuratorFramework client) throws Exception {
        if (!handler.checkExist(CHILD_NODE)) {
            client.create().withMode(CreateMode.EPHEMERAL).forPath(CHILD_NODE, ZookeeperCommon.NodeState.CURRENT.getCode().getBytes());
        } else {
            log.info(CHILD_NODE.concat(">>>>>>>>>>status...{}"), ZookeeperCommon.NodeState.CURRENT.getMessage());
        }
    }

    /**
     *
     * @param client
     * @throws Exception
     */
    public void addPersisListenerChild(CuratorFramework client) throws Exception {
        Executors.newCachedThreadPool();
        final NodeCache nodeCache = new NodeCache(client, CHILD_NODE, false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {

            public void nodeChanged() throws Exception {
                String data = new String(nodeCache.getCurrentData().getData());
                if (ZookeeperCommon.NodeState.BACKUP.getCode().equals(data)) {
                    noticeParentNode();
                    ZookeeperCommon.status = ZookeeperCommon.NodeState.BACKUP.getCode();
                } else if (ZookeeperCommon.NodeState.CURRENT.getCode().equals(data)) {
                    ZookeeperCommon.status = ZookeeperCommon.NodeState.CURRENT.getCode();
                }
                log.info("the node is changed and result is :");
                log.info("path : " + nodeCache.getCurrentData().getPath());
                log.info("data : " + data);
                log.info("stat : " + nodeCache.getCurrentData().getStat());
            }
        });
        nodeCache.start();
    }

    /**
     */
    private void noticeParentNode() {
        handler.updateNodeDate(PARENT_NODE, ZookeeperCommon.NodeState.ACK.getCode());
    }
}
