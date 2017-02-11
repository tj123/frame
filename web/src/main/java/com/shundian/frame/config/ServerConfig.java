package com.shundian.frame.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

/**
 * tomcat优化配置
 */
//@Configuration
@Slf4j
public class ServerConfig {

    @Value("${custom.tomcat.max-connections}")
    private int maxConnections;

    @Value("${custom.tomcat.connection-timeout}")
    private int connectionTimeout;

    @Bean
    public EmbeddedServletContainerCustomizer createEmbeddedServletContainerCustomizer() {
        return new MyEmbeddedServletContainerCustomizer();
    }

    class MyEmbeddedServletContainerCustomizer implements EmbeddedServletContainerCustomizer {

        public void customize(ConfigurableEmbeddedServletContainer container) {
            TomcatEmbeddedServletContainerFactory tomcatFactory = (TomcatEmbeddedServletContainerFactory) container;
            tomcatFactory.getTomcatConnectorCustomizers().add(new MyTomcatConnectorCustomizer());
        }

    }

    class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {

        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            //设置最大连接数  
            //protocol.setMaxConnections(maxConnections);  
            //设置连接超时时间
            //protocol.setConnectionTimeout(connectionTimeout);  
            log.info("内嵌WEB容器：Tomcat;配置参数：{port:" + protocol.getPort() + ",maxThreads:" + protocol.getMaxThreads() +
                    ",minSpareThreads:" + protocol.getMinSpareThreads() + ",maxConnections:" + protocol.getMaxConnections() +
                    ",connectionTimeout:" + protocol.getConnectionTimeout() + "}");
        }

    }

}