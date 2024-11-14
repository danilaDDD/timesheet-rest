package ru.gbdanila.recover;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RecoverConfigurationProperties.class)
@ConditionalOnProperty(value = "application.recovering.enabled", havingValue = "true")
public class RecoverAutoConfigure {

    @Bean
    public RecoverAspect recoverAspect(RecoverConfigurationProperties properties){
        return new RecoverAspect(properties);
    }

}
