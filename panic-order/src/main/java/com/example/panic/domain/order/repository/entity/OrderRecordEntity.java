package com.example.panic.domain.order.repository.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单记录表
 * 
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 18:18:23
 * @verion V0.1
 */
@Data
@TableName("order_record")
public class OrderRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */

	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * spu主键id
	 */
	private Long spuId;
	/**
	 * sku主键id
	 */
	private Long skuId;
	/**
	 * 金额
	 */
	private BigDecimal price;
	/**
	 * 状态标示：0-下单成功，1-已付款，2-退单成功
	 */
	private Integer state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建时间
	 */
	private Date updateTime;

















}
