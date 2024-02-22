package com.example.panic.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateOrderDTO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * spuId
     */
    private Long spuId;

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 订单金额
     */
    private BigDecimal price;
}
