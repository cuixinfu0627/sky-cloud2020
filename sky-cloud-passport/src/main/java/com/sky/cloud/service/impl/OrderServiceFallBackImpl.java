package com.sky.cloud.service.impl;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.OrderService;
import com.sky.cloud.view.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @name: OrderServiceImpl <tb>
 * @title: 兜底的方法  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/1/8 16:30 <tb>
 */
@Component
public class OrderServiceFallBackImpl implements OrderService {

    @Override
    public R create(OrderEntity order) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("-----------------------服务返回 create OrderServiceFallBackImpl fall back-list");
    }

    @Override
    public R update(OrderEntity order) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("----------------------- 服务返回 update OrderServiceFallBackImpl fall back-list");
    }

    @Override
    public R updateStatus(OrderEntity order) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("----------------------- 服务返回 updateStatus OrderServiceFallBackImpl fall back-list");
    }


    @Override
    public R list(Map<String, Object> params) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("-----------------------服务返回 OrderFeignFallBackService fall back-list");
    }

    @Override
    public R info(Long id) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("-----------------------服务返回 OrderFeignService fall back-info");
    }

    @Override
    public R openfeignTimeout() {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("-----------------------服务返回 OrderFeignService fall back-openfeignTimeout");
    }

    @Override
    public R hystrixCircuitBreaker(OrderEntity order) {
        //TODO 记录日志信息，后续自动补偿，根据实际业务情况处理
        return R.error("-----------------------服务返回 OrderFeignService fall back-hystrixCircuitBreaker");
    }

}
