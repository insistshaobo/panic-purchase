package com.example.panic.domain.product.repository.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.panic.domain.product.repository.entity.GoodsSkuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品sku信息表
 * 
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
@Repository("goodsSkuDao")
public interface GoodsSkuDao extends BaseMapper<GoodsSkuEntity> {

    List<GoodsSkuEntity> queryAll();
}
