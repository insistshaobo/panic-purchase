package com.example.panic.domain.product.domainService.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.panic.domain.product.repository.dao.GoodsSkuDao;
import com.example.panic.domain.product.repository.entity.GoodsSkuEntity;

import com.example.panic.domain.product.domainService.GoodsSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品sku信息表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
@Service
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuDao, GoodsSkuEntity> implements GoodsSkuService {
	@Autowired
	private GoodsSkuDao goodsSkuDao;

	@Override
	public List<GoodsSkuEntity> queryAll() {
		return goodsSkuDao.queryAll();
	}
}
