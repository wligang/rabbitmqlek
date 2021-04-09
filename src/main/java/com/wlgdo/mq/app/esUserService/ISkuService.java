package com.wlgdo.mq.app.esUserService;

import com.wlgdo.mq.app.model.Skus;

import java.util.List;

public interface ISkuService {
    Skus save(Skus sku);

    Skus get(Long skuId);

    List<Skus> getList(String goodsId);
}
