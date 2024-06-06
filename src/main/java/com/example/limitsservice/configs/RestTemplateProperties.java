package com.example.limitsservice.configs;

import java.time.Duration;

public record RestTemplateProperties(String url, Duration connectionTimeout, Duration readTimeout) {
}
