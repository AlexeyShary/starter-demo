package t1.openschool.httplogger.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LoggingConfig.class)
@TestPropertySource(properties = {
        "logging.starter.enabled=false",
        "logging.starter.printRequestHeaders=true",
        "logging.starter.printResponseHeaders=true",
        "logging.starter.printResponseTime=false",
        "logging.starter.level=DEBUG"
})
class LoggingPropertiesTest {
    @Autowired
    private LoggingProperties loggingProperties;

    @Test
    void testLoggingPropertiesLoadedFromPropertiesFile() {
        assertThat(loggingProperties.isEnabled()).isFalse();
        assertThat(loggingProperties.isPrintRequestHeaders()).isTrue();
        assertThat(loggingProperties.isPrintResponseHeaders()).isTrue();
        assertThat(loggingProperties.isPrintResponseTime()).isFalse();
        assertThat(loggingProperties.getLevel()).isEqualTo("DEBUG");
    }
}