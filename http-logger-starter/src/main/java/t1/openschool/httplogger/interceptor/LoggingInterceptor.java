package t1.openschool.httplogger.interceptor;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import t1.openschool.httplogger.config.LoggingProperties;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    private final LoggingProperties loggingProperties;

    private Consumer<String> logMethod;

    @PostConstruct
    public void init() {
        this.logMethod = setLoggerLevel(loggingProperties.getLevel());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String message = String.format("Incoming Request: Method: %s, URL: %s",
                request.getMethod(), request.getRequestURI());

        if (loggingProperties.isPrintRequestHeaders()) {
            message += ", Headers: " + getHeadersAsString(request);
        }

        logMethod.accept(message);
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
        long startTime = (Long) request.getAttribute("startTime");
        long timeTaken = System.currentTimeMillis() - startTime;

        String message = String.format("Outgoing Response: Status: %d",
                response.getStatus());

        if (loggingProperties.isPrintResponseHeaders()) {
            message += ", Headers: " + getHeadersAsString(response);
        }

        if (loggingProperties.isPrintResponseTime()) {
            message += ", Time Taken: " + timeTaken + " ms";
        }

        logMethod.accept(message);
    }

    private String getHeadersAsString(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> headers.add(headerName, request.getHeader(headerName)));
        return headers.toString();
    }

    private String getHeadersAsString(HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        response.getHeaderNames().forEach(headerName -> headers.add(headerName, response.getHeader(headerName)));
        return headers.toString();
    }

    private Consumer<String> setLoggerLevel(String level) {
        switch (level.toUpperCase()) {
            case "TRACE":
                return logger::trace;
            case "DEBUG":
                return logger::debug;
            case "INFO":
                return logger::info;
            default:
                logger.warn("Unexpected log level: {}. Available values are: TRACE, DEBUG, INFO. Log level is set to INFO", level.toUpperCase());
                return logger::info;
        }
    }
}