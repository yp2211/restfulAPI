package com.yp36.common.zookeeper;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Created by yangpeng on 2016/11/10.
 */
@Getter
@Setter
public class ZookeeperCommon {
    public static String status;

    @Getter
    public enum NodeState {
        CURRENT("0", "Initalizing status. Read current_index, waiting python. "),
        BACKUP("1", "Subnode change to backup_index. (Python backup is already finished)"),
        PROCESSING("2", "Python backup is already finished. Reset current_index when got ACK feedback from subnotes."),
        ACK("3", "Subnotes feedback ACK. Then reset parent data. Sibling notes keepping quiet. ");

        private String code;
        private String message;

        NodeState(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
