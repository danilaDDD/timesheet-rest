package ru.gbdanila.recover;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;

@Data
@ConfigurationProperties("application.recovering")
public class RecoverConfigurationProperties {
    private boolean enabled = true;
    private Class<? extends Throwable>[] noRecoverFor;
}
