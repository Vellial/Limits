package com.example.limitsservice.services;

import com.example.limitsservice.entities.Limit;
import dto.PaymentDto;
import dto.ResponseDto;

public interface LimitService {
    Limit setLimitByUserId(PaymentDto paymentDto);

    ResponseDto revertLimit(PaymentDto paymentDto);

    Limit getLimitByUserId(Long userId);
}
