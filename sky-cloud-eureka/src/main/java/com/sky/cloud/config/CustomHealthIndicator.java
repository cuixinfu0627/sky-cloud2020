package com.sky.cloud.config;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @name: CustomHealthIndicator <tb>
 * @title: 监控状态  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/1 9:50 <tb>
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    private boolean status = true ;

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if(status) {
            //builder.up() ;
            builder.up().withDetail("customInfo","自定义信息-builder.up()");
        }else {
            //builder.down() ;
            builder.down().withDetail("customInfo","自定义信息-builder.down()");
        }
    }
}
