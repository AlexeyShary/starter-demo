package t1.openschool.httplogger.interceptor;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "logging.starter.enabled=true",
        "logging.starter.printRequestHeaders=true",
        "logging.starter.printResponseHeaders=true",
        "logging.starter.printResponseTime=true",
        "logging.starter.level=INFO"
})
public class LoggingInterceptorTest {
    @Autowired
    private MockMvc mockMvc;

    private LogCaptor logCaptor;

    @BeforeEach
    public void setUp() {
        logCaptor = LogCaptor.forClass(LoggingInterceptor.class);
    }

    @Test
    public void testInterceptor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andExpect(status().isNotFound());

        List<String> logs = logCaptor.getLogs();
        assertThat(logs.size()).isEqualTo(2);
        assertThat(logs.get(0)).contains("Incoming Request: Method: GET, URL: /test");
        assertThat(logs.get(1)).contains("Outgoing Response: Status: 404");
    }
}