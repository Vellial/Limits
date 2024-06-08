package com.example.limitsservice.services;

import com.example.limitsservice.entities.Limit;
import com.example.limitsservice.repositories.LimitRepository;
import dto.ErrorDto;
import dto.PaymentDto;
import dto.ResponseDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;

    public LimitServiceImpl(LimitRepository limitRepo) {
        this.limitRepository = limitRepo;
    }

    @Override
    public Limit setLimitByUserId(PaymentDto paymentDto) {
        Limit updatedLimit = new Limit();
        Long userId = paymentDto.getUserId();

        saveNewUserIfNotFound(userId);

        Limit userLimit = limitRepository.findByUserId(userId);

        if (userLimit.getLimit() > paymentDto.getSum()) {
            // изменяем и сохраняем в БД новый лимит
            updatedLimit = new Limit(userId, userLimit.getLimit() - paymentDto.getSum());

            limitRepository.save(updatedLimit);
        }

        return updatedLimit;
    }

    @Override
    public ResponseDto revertLimit(PaymentDto paymentDto) {
        // отмена изменения лимита
        Long userId = paymentDto.getUserId();

        Limit userLimit = limitRepository.findByUserId(userId);

        if (userLimit == null) {
            return new ResponseDto("404", "User limit not found for user: " + paymentDto.getUserId());
        }

        Limit updatedLimit = new Limit(userId, userLimit.getLimit() + paymentDto.getSum());

        limitRepository.save(updatedLimit);

        return new ResponseDto("200", "Limit was reverted");
    }

    @Override
    public Limit getLimitByUserId(Long userId) {
        saveNewUserIfNotFound(userId);
        return limitRepository.findByUserId(userId);
    }

    @Scheduled(cron = "@dayly")
    public void updateLimitBySchedule() {
        List<Limit> allLimits = limitRepository.findAll();

        allLimits.forEach(limit -> {
            limit.setLimit(10000.0);
        });

        limitRepository.saveAll(allLimits);

    }

    private void saveNewUserIfNotFound(Long userId) {
        if (userId > 100) {
            // запись в БД нового юзера
            Limit newUserLimit = new Limit(userId, 10000.0);
            limitRepository.save(newUserLimit);
        }
    }

}
