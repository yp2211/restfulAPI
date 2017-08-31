CREATE TABLE IF NOT EXISTS `test` (
  `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username`      VARCHAR(128)   NOT NULL DEFAULT '' COMMENT '用户名',
  `nickname`       VARCHAR(128) NOT NULL DEFAULT '' COMMENT '昵称',
  `updated_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_time`  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `created_by`    VARCHAR(30)  DEFAULT NULL COMMENT '创建者',
  `updated_by`    VARCHAR(30)  DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_time`),
  KEY `ix_updated_at` (`updated_time`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '测试表';