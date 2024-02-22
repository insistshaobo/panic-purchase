package com.example.panic.api.facade;

import com.example.panic.application.dto.GoodInfoDTO;

public interface GoodInfoFacade {

    /**
     * 通过skuId查询
     * @param skuId
     * @return
     */
    GoodInfoDTO querBySkuId(Long skuId);
}
