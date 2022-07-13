package com.event.reward.core;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 리워드 지급 히스토리.
 */

@Getter
@Entity
@Table(name = "reward_history")
public class Reward {

    @Id
    @GeneratedValue
    private Long id;

    private long userNo;

    private int reward;

    @Column(columnDefinition="DATETIME(0) default CURRENT_TIMESTAMP")
    private LocalDateTime created;

    public Reward(long userNo, int reward) {
        this.userNo = userNo;
        this.reward = reward;
        this.created = LocalDateTime.now();
    }


    public Reward() {}
}
