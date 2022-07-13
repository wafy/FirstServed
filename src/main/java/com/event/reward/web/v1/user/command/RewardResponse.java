package com.event.reward.web.v1.user.command;

import com.event.reward.core.Reward;
import lombok.Getter;

@Getter
public class RewardResponse {
    private boolean success;
    private Object reason;
    public RewardResponse(Reward reward) {
        this.success = true;
        this.reason = reward;
    }
}
