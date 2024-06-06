package com.example.limitsservice.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "limits")
public class Limit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "limit")
    private Double limit;

//    @Column(name = "timeupdated")
//    private LocalDateTime timeupdated;

    public Limit() {}

    public Limit(Long userId, Double i) {
        this.userId = userId;
        this.limit = i;
//        this.timeupdated = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public LocalDateTime getTimeupdated() {
//        return timeupdated;
//    }
//
//    public void setTimeupdated(LocalDateTime timeupdated) {
//        this.timeupdated = timeupdated;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        this.limit = limit;
    }
}
