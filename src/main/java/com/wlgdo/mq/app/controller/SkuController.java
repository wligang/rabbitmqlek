package com.wlgdo.mq.app.controller;

import com.wlgdo.mq.app.esUserService.ISkuService;
import com.wlgdo.mq.app.model.Skus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class SkuController {

    private final ISkuService skuServiceImpl;


    @GetMapping("/sku")
    public Skus getSku(@RequestParam(required = false, name = "skuId") Long skuId) {
        log.info("查询SKU:{}", skuId);
        log.debug("Debugger开始查询：{}", skuId);
        return skuServiceImpl.get(skuId);

    }

    @GetMapping("/skus")
    public Skus getUserInfo(@RequestParam(required = false, name = "skuId") String skuId) {
        Skus skus = new Skus(2L, Long.valueOf(skuId), "黑白");
        skus.setCreateTime(LocalDateTime.now());
        log.info("添加新的SKU:{}", skus);
        return skuServiceImpl.save(skus);

    }

    @GetMapping("/list")
    public List<Skus> getUserInfoList(@RequestParam(required = false, name = "goodsId") String goodsId) {
        log.info("开始获取所有的goodsId: {} 的SKU", goodsId);
        return skuServiceImpl.getList(goodsId);

    }
}
