package com.event.reward.web.v1.user.command;

import lombok.Getter;

/**
 * 리워드 생성 요청.
 */
@Getter
public class RewardRequest {

    /**
     * 요청자 회원번호.
     */
    private long userNo;

    public RewardRequest(long userNo) {
        this.userNo = userNo;
    }

    public RewardRequest() {}
}
