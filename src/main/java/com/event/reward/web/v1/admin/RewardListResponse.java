package com.event.reward.web.v1.admin;

import com.event.reward.core.Reward;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RewardListResponse {
    private Long id;
    private long userNo;
    private int reward;
    private LocalDateTime created;

    public RewardListResponse() {}

    public RewardListResponse(Reward reward) {
        this.id = reward.getId();
        this.userNo = reward.getUserNo();
        this.reward = reward.getReward();
        this.created = reward.getCreated();
    }
}
