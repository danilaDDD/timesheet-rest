package ru.gbdanila.recover;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.recovering")
public class RecoverConfigurationProperties {
    private boolean enabled = true;
//    private Class<? extends Throwable>[] noRecoverFor;
}
