package com.example.limitsservice.controllers;

import com.example.limitsservice.entities.Limit;
import com.example.limitsservice.services.LimitService;
import dto.PaymentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/getlimit")
public class LimitsController {

    private final LimitService limitService;

    public LimitsController(LimitService limitService) {
        this.limitService = limitService;
    }

    @PostMapping(value = "/limit/minLimit")
    public Limit getUserLimit(@RequestBody PaymentDto paymentDto) {
        return limitService.setLimitByUserId(paymentDto);
    }

    @PostMapping(value = "/limit/revertLimit")
    public Limit revertUserLimit(@RequestBody PaymentDto paymentDto) {
        return limitService.revertLimit(paymentDto);
    }

    @GetMapping(value = "/limit/getLimit/{userId}")
    public Limit getUserLimit(@PathVariable Long userId) {
        return limitService.getLimitByUserId(userId);
    }
}
