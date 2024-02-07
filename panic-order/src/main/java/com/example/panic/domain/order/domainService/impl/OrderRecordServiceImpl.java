package com.example.panic.domain.order.domainService.impl;

import com.example.panic.domain.order.domainService.OrderRecordService;
import com.example.panic.domain.order.repository.dao.OrderRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单记录表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 18:18:23
 * @version V0.1
 */
@Service
public class OrderRecordServiceImpl implements OrderRecordService {
	@Autowired
	private OrderRecordDao orderRecordDao;

	@Override
	public String createOrder() {
		return null;
	}
}
