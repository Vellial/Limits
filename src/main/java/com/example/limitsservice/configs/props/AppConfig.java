package com.example.limitsservice.configs.props;

import com.example.limitsservice.configs.RTResponseErrorHandler;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableScheduling
@EnableConfigurationProperties({
        ExecutorProperties.class
})
public class AppConfig {

    @Bean
    public RestTemplate executorClient(
            ExecutorProperties executorProperties,
            RTResponseErrorHandler errorHandler) {

        return new RestTemplateBuilder()
                .rootUri(executorProperties.getRestTemplateProps().url())
                .setConnectTimeout(executorProperties.getRestTemplateProps().connectionTimeout())
                .setReadTimeout(executorProperties.getRestTemplateProps().readTimeout())
                .errorHandler(errorHandler)
                .build();
    }
}
