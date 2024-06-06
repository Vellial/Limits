package com.example.limitsservice.services;

import com.example.limitsservice.entities.Limit;
import com.example.limitsservice.repositories.LimitRepoJPA;
import dto.PaymentDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitServiceImpl implements LimitService {

    private final LimitRepoJPA limitRepoJPA;

    public LimitServiceImpl(LimitRepoJPA limitRepoJPA) {
        this.limitRepoJPA = limitRepoJPA;
    }

    @Override
    public Limit setLimitByUserId(PaymentDto paymentDto) {
        Limit updatedLimit = new Limit();
        Long userId = paymentDto.getUserId();


        if (userId > 100) {
            // запись в БД нового юзера
            Limit new_user_limit = new Limit(userId, 10000.0);
            limitRepoJPA.save(new_user_limit);
        }

        Limit userLimit = limitRepoJPA.findByUserId(userId).orElseThrow();

        if (userLimit.getLimit() > paymentDto.getSum()) {
            // изменяем и сохраняем в БД новый лимит
            updatedLimit = new Limit(userId, userLimit.getLimit() - paymentDto.getSum());

            limitRepoJPA.save(updatedLimit);
        }

        return updatedLimit;
    }

    @Override
    public Limit revertLimit(PaymentDto paymentDto) {
        // отмена изменения лимита
        Long userId = paymentDto.getUserId();

        Limit userLimit = limitRepoJPA.findByUserId(userId).orElseThrow();

        Limit updatedLimit = new Limit(userId, userLimit.getLimit() + paymentDto.getSum());

        limitRepoJPA.save(updatedLimit);

        return updatedLimit;
    }

    @Override
    public Limit getLimitByUserId(Long userId) {
        return limitRepoJPA.findByUserId(userId).orElseThrow();
    }

    @Scheduled(cron = "@dayly")
    public void updateLimitBySchedule() {
        List<Limit> allLimits = limitRepoJPA.findAll();

        allLimits.forEach(limit -> {
            limitRepoJPA.save(new Limit(limit.getUserId(), 10000.0));
        });

    }

}
