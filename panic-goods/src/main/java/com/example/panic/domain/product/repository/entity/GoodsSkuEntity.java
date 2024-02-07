package com.example.panic.domain.product.repository.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 商品sku信息表
 * 
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @verion V0.1
 */
@Data
@TableName("goods_sku")
public class GoodsSkuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
    //@ApiModelProperty(value = "主键id")
	@TableId
	private Long id;
	/**
	 * spu_id
	 */
    //@ApiModelProperty(value = "spu_id")
	private Long spuId;
	/**
	 * 商品名称
	 */
    //@ApiModelProperty(value = "商品名称")
	private String name;
	/**
	 * 商品编码
	 */
    //@ApiModelProperty(value = "商品编码")
	private String code;
	/**
	 * 原价
	 */
    //@ApiModelProperty(value = "原价")
	private BigDecimal origPrice;
	/**
	 * 折扣价
	 */
    //@ApiModelProperty(value = "折扣价")
	private BigDecimal discountPrice;
	/**
	 * 有效期-开始
	 */
    //@ApiModelProperty(value = "有效期-开始")
	private Date validityStart;
	/**
	 * 有效期-结束
	 */
    //@ApiModelProperty(value = "有效期-结束")
	private Date validityEnd;
	/**
	 * 删除状态：0-未删除，1-已删除
	 */
    //@ApiModelProperty(value = "删除状态：0-未删除，1-已删除")
	private Integer isDel;
	/**
	 * 创建人Id
	 */
    //@ApiModelProperty(value = "创建人Id")
	private Long createId;
	/**
	 * 修改人Id
	 */
    //@ApiModelProperty(value = "修改人Id")
	private Long updateId;
	/**
	 * 创建时间
	 */
    //@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 修改时间
	 */
    //@ApiModelProperty(value = "修改时间")
	private Date updateTime;



























}
