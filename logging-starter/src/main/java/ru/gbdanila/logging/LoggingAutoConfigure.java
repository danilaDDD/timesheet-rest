package ru.gbdanila.logging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingConfigurationProperties.class)
@ConditionalOnProperty(value = "application.logging.enabled", havingValue = "true")
public class LoggingAutoConfigure {

    @Bean
    public LoggingAspect loggingAspect(LoggingConfigurationProperties properties){
        return new LoggingAspect(properties);
    }
}
