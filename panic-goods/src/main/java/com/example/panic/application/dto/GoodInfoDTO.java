package com.example.panic.application.dto;

import lombok.Data;

@Data
public class GoodInfoDTO {

    /**
     * spuid
     */
    private Long spuId;

    /**
     * spu名称
     */
    private String spuName;

    /**
     * spu编码
     */
    private String spuCode;

    /**
     * spu状态：0-未上架，1-已上架
     */
    private Integer spuStatus;

    /**
     * skuid
     */
    private Long skuId;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * sku状态：0-未上架，1-已上架
     */
    private Integer skuStatus;
    /**
     * sku编码
     */
    private String skuCode;
    /**
     * 数量
     */
    private Long quantity;
}
