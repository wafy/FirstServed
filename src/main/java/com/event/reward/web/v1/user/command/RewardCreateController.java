package com.event.reward.web.v1.user.command;

import com.event.reward.axiom.target.ForUser;
import com.event.reward.core.command.RewardCreator;
import com.event.reward.core.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController("userRewardSearchController")
@RequestMapping("/v1/user/reward")
@RequiredArgsConstructor
public class RewardSearchController implements ForUser {
    private final RewardCreator rewardCreator;

    /**
     * 리워드 생성을 요청받아 처리합니다.
     * @param rewardRequest 리워드 생성 요청
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createReward(@RequestBody RewardRequest rewardRequest) {
        if (rewardRequest.getUserNo() == 0) {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }
        final Reward reward = rewardCreator.create(LocalDate.now(), rewardRequest.getUserNo());
        return ResponseEntity.ok(new RewardResponse(reward));
    }

}
