package com.example.limitsservice.services;

import com.example.limitsservice.entities.Limit;
import dto.PaymentDto;

public interface LimitService {
    Limit setLimitByUserId(PaymentDto paymentDto);

    Limit revertLimit(PaymentDto paymentDto);

    Limit getLimitByUserId(Long userId);
}
