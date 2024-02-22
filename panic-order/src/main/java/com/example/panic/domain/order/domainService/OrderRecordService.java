package com.example.panic.domain.order.domainService;


import com.example.panic.application.dto.CreateOrderDTO;

/**
 * 订单记录表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 18:18:23
 * @version V0.1
 */
public interface OrderRecordService {

    /**
     * 用户下单
     * @param createOrderDTO
     * @return
     */
    String createOrder(CreateOrderDTO createOrderDTO);
}

