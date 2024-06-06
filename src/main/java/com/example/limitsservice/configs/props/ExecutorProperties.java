package com.example.limitsservice.configs.props;

import com.example.limitsservice.configs.RestTemplateProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations")
public class ExecutorProperties {
    private final RestTemplateProperties restTemplateProperties;

    @ConstructorBinding
    public ExecutorProperties(RestTemplateProperties restTemplateProperties) {
        this.restTemplateProperties = restTemplateProperties;
    }

    public RestTemplateProperties getRestTemplateProps() {
        return restTemplateProperties;
    }
}
