package com.sky.cloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.sky.myrule.MySelfRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: PassportApplication <tb>
 * @title: 用户服务 - consumer <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/12/18 16:21 <tb>
 */
@SpringBootApplication(scanBasePackages = {"com.sky.cloud"})
@EnableDiscoveryClient
@EnableEurekaClient
@RibbonClient(name = "ORDER-SERVICE",configuration = MySelfRule.class) //ribbon
@EnableFeignClients //openfeign
@EnableCircuitBreaker // hystrix 熔断配置
public class PassportApplication {
    private static final Logger logger = LoggerFactory.getLogger(PassportApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(PassportApplication.class);
        Map<String, Object> defProperties =  new HashMap<String, Object>();
        defProperties.put("spring.profiles.default", "local");
        app.setDefaultProperties(defProperties);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }

    /**
     *此配置是为服务监控而配置，与服务容错本身无关，springcloud升级后的员
     *ServletRegistrationBean因为springbooty新认路径不是"/hystrix.stream"，
     *只要在自己的项目里配置上下面的servLet就可以了
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet =new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean =new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsstreamservlet");
        return registrationBean;
    }

}
