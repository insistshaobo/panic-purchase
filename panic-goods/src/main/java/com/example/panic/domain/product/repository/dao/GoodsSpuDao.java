package com.example.panic.domain.product.repository.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.panic.domain.product.repository.entity.GoodsSpuEntity;
import org.springframework.stereotype.Repository;

/**
 * 商品spu信息表
 * 
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
@Repository("goodsSpuDao")
public interface GoodsSpuDao extends BaseMapper<GoodsSpuEntity> {
	
}
