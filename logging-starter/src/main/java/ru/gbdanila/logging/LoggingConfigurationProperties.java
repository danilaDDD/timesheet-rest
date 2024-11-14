package ru.gbdanila.logging;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("application.logging")
public class LoggingConfigurationProperties {
    private Level level = Level.DEBUG;
    private boolean enabled = true;
    private boolean printArgs = false;
}
