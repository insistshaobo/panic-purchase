CREATE TABLE `goods_sku` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `spu_id` bigint NOT NULL COMMENT 'spu_id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `code` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `orig_price` decimal(20,8) NOT NULL COMMENT '原价',
  `discount_price` decimal(20,8) NOT NULL COMMENT '折扣价',
  `validity_start` datetime DEFAULT NULL COMMENT '有效期-开始',
  `validity_end` datetime DEFAULT NULL COMMENT '有效期-结束',
  `is_del` tinyint DEFAULT NULL COMMENT '删除状态：0-未删除，1-已删除',
  `create_id` bigint DEFAULT NULL COMMENT '创建人Id',
  `update_id` bigint DEFAULT NULL COMMENT '修改人Id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品sku信息表';


CREATE TABLE `goods_spu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `code` varchar(255) DEFAULT NULL COMMENT '商品编码',
  `validity_start` datetime DEFAULT NULL COMMENT '有效期-开始',
  `validity_end` datetime DEFAULT NULL COMMENT '有效期-结束',
  `is_del` tinyint DEFAULT NULL COMMENT '删除状态：0-未删除，1-已删除',
  `create_id` bigint DEFAULT NULL COMMENT '创建人Id',
  `update_id` bigint DEFAULT NULL COMMENT '修改人Id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='商品spu信息表';


CREATE TABLE `order_record`(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id`     varchar(120) NOT NULL COMMENT '用户id',
    `spu_id`      bigint(20)   NOT NULL COMMENT 'spu主键id',
    `sku_id`      bigint(20)   NOT NULL COMMENT 'sku主键id',
    `price`       decimal(22, 8)        DEFAULT NULL COMMENT '金额',
    `state`       tinyint(4)   NOT NULL COMMENT '状态标示：0-下单成功，1-已付款，2-退单成功',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB COMMENT ='订单记录表';