package com.sky.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.sky.cloud.view.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @name: FlowLimitController <tb>
 * @title: Sentinel 流控测试  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2021/1/5 10:23<tb>
 */
@RestController
public class FlowLimitController {

    private static final Logger logger = LoggerFactory.getLogger(FlowLimitController.class);

    @GetMapping("/testA")
    public R testA(){
        logger.info("FlowLimitController ------testA");
        return R.ok("------testA");
    }

    @GetMapping("/testB")
    public R testB(){
        logger.info("FlowLimitController ------testB");
        return R.ok("------testB");
    }

    @GetMapping("/testC")
    public R testC(){
        logger.info("FlowLimitController ------testC");
        return R.ok("------testC");
    }

    @GetMapping("/testD")
    public R testD(){
        logger.info("FlowLimitController ------testD");
        return R.ok("------testD");
    }

    /**
     * @title: 测试热点可以限流配置方法 <tb>
     * @author: cuixinfu@51play.com <tb>
     * @date: 2021/1/5 15:35<tb>
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public R testHotKey(@RequestParam(value = "p1",required = false) String p1,
                        @RequestParam(value = "p1",required = false) String p2){
        logger.info("FlowLimitController ---测试热点限流---testHotKey,o(^_^)o");
        return R.ok("------testHotKey");
    }

    public R deal_testHotKey(String p1, String p2, BlockException exception){
        logger.info("FlowLimitController ---测试热点限流BlockException---deal_testHotKey,o(T-T)o");
        return R.ok("------testHotKey");
    }

}
