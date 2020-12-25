package com.sky.cloud.service;

import com.sky.cloud.entity.OrderEntity;
import com.sky.cloud.service.impl.OrderFeignFallBackService;
import com.sky.cloud.view.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @name: OrderFeignService <tb>
 * @title: open-feign  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/12/21 15:27 <tb>
 */
@Component
@FeignClient(value = "${service-url.nacos-order-service}",fallback = OrderFeignFallBackService.class)
public interface OrderFeignService {

    @RequestMapping("order/list")
    public R list(@RequestParam Map<String, Object> params);

    @RequestMapping("order/info/{id}")
    public R info(@PathVariable("id") Long id);

    @RequestMapping("order/create")
    public R create(@RequestBody OrderEntity order);

    @RequestMapping("/order/openfeign/timeout")
    public R openfeignTimeout();

    @RequestMapping("/order/hystrix/circuitBreaker")
    public R hystrixCircuitBreaker(OrderEntity order);

}
