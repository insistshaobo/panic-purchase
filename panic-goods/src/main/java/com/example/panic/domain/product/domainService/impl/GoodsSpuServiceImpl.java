package com.example.panic.domain.product.domainService.impl;


import com.example.panic.domain.product.domainService.GoodsSpuService;
import com.example.panic.domain.product.repository.dao.GoodsSpuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品spu信息表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
@Service
public class GoodsSpuServiceImpl implements GoodsSpuService {
	@Autowired
	private GoodsSpuDao goodsSpuDao;

}
