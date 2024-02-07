package com.example.panic.domain.order.repository.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.panic.domain.order.repository.entity.OrderRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * 订单记录表
 * 
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 18:18:23
 * @version V0.1
 */
@Repository("orderRecordDao")
public interface OrderRecordDao extends BaseMapper<OrderRecordEntity> {
	
}
