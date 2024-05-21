package t1.openschool.httplogger.interceptor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import t1.openschool.httplogger.config.LoggingConfig;

@Configuration
@EnableAutoConfiguration
@Import({LoggingConfig.class})
public class TestConfig {
}
