package com.sky.cloud.service.impl;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.OrderFeignService;
import com.sky.cloud.view.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name: OrderFeignServiceImpl <tb>
 * @title: 全局处理服务降级 逻辑 <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/12/22 9:48 <tb>
 */
@Component
public class OrderFeignFallBackService implements OrderFeignService {

    @Override
    public R list(Map<String, Object> params) {
        return R.error("-----------------------服务返回 OrderFeignFallBackService fall back-list");
    }

    @Override
    public R info(Long id) {
        return R.error("-----------------------服务返回 OrderFeignService fall back-info");
    }

    @Override
    public R create(OrderEntity order) {
        return R.error("-----------------------服务返回 OrderFeignService fall back-create");
    }

    @Override
    public R openfeignTimeout() {
        return R.error("-----------------------服务返回 OrderFeignService fall back-openfeignTimeout");
    }

    @Override
    public R hystrixCircuitBreaker(OrderEntity order) {
        return R.error("-----------------------服务返回 OrderFeignService fall back-hystrixCircuitBreaker");
    }

}
