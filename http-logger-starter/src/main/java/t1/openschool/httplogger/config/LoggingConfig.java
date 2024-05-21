package t1.openschool.httplogger.config;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import t1.openschool.httplogger.interceptor.LoggingInterceptor;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@RequiredArgsConstructor
public class LoggingConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);

    private final LoggingProperties loggingProperties;

    @Bean
    @ConditionalOnProperty(name = "logging.starter.enabled", matchIfMissing = true, havingValue = "true")
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor(loggingProperties);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (!loggingProperties.isEnabled()) {
            return;
        }

        logger.info("Registering T1 LoggingInterceptor");
        registry.addInterceptor(loggingInterceptor());
    }
}
