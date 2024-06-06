package com.example.limitsservice.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RTResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatusCode statusCode = response.getStatusCode();
        return statusCode.is4xxClientError() || statusCode.is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorDto executorErrorDto = objectMapper.readValue(response.getBody(), ErrorDto.class);
            throw new RuntimeException(executorErrorDto.message());
        }
    }
}
