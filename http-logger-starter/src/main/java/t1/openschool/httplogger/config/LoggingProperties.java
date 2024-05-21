package t1.openschool.httplogger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "logging.starter")
public class LoggingProperties {
    private boolean enabled = true;

    private boolean printRequestHeaders = true;

    private boolean printResponseHeaders = true;
    private boolean printResponseTime = true;

    private String level = "INFO";
}
