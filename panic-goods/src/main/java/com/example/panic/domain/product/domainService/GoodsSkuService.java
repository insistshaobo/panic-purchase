package com.example.panic.domain.product.domainService;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.panic.domain.product.repository.entity.GoodsSkuEntity;

import java.util.List;

/**
 * 商品sku信息表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
public interface GoodsSkuService extends IService<GoodsSkuEntity> {

    List<GoodsSkuEntity> queryAll();
}

