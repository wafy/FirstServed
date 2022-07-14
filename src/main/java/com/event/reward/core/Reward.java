package com.event.reward.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 리워드 지급 히스토리.
 */

@Getter
@Entity
@Table(name = "reward_history")
@Inheritance(strategy = InheritanceType.JOINED)
public class Reward {

    @Id
    @GeneratedValue
    private Long id;

    private long userNo;

    private int reward;

    @Column(columnDefinition="DATETIME(0) default CURRENT_TIMESTAMP")
    private LocalDateTime created;

    @Version
    @JsonIgnore
    private Long version;

    public Reward(long userNo, int reward) {
        this.userNo = userNo;
        this.reward = reward;
        this.created = LocalDateTime.now();
    }

    public Reward(long userNo, int reward, LocalDateTime created) {
        this.userNo = userNo;
        this.reward = reward;
        this.created = created;
    }

    public Reward() {}
}
