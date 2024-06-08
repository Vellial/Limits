package com.example.limitsservice.repositories;

import com.example.limitsservice.entities.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

    @NonNull
    List<Limit> findAll();

//    Optional<Limit> findByUserId(Long userId);

    Limit findByUserId(Long userId);

}
