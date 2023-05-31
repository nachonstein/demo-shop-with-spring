package com.luxoft.demoshopwithspring.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "kafka.producer")
@Getter
@Setter
public class KafkaProperties {
    private String productTopic;
    private String bootstrapAddress;
    private String maxRequestSizeConfig;
}
