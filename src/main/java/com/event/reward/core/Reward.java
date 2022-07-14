package com.event.reward.core;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 리워드 지급 히스토리.
 */

@Getter
@Entity
@Table(name = "reward_history")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Reward {

    @Id
    @GeneratedValue
    private Long id;

    private long userNo;

    private int reward;

    @Column(columnDefinition="DATETIME(0) default CURRENT_TIMESTAMP")
    private LocalDateTime created;

    @Version
    private Long version;

    public Reward(long userNo, int reward) {
        this.userNo = userNo;
        this.reward = reward;
        this.created = LocalDateTime.now();
    }


    public Reward() {}
}
