package t1.openschool.httplogger.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import t1.openschool.httplogger.interceptor.LoggingInterceptor;

import static org.assertj.core.api.Assertions.assertThat;

class LoggingConfigTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(LoggingConfig.class));

    @Test
    void testInterceptorRegistration() {
        this.contextRunner.withPropertyValues("logging.starter.enabled=true")
                .run(context -> {
                    assertThat(context).hasSingleBean(LoggingInterceptor.class);
                    assertThat(context).hasSingleBean(LoggingConfig.class);
                });
    }

    @Test
    void testInterceptorNotRegisteredWhenDisabled() {
        this.contextRunner.withPropertyValues("logging.starter.enabled=false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(LoggingInterceptor.class);
                });
    }
}