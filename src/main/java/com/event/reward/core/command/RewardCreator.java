package com.event.reward.core.command;

import com.event.reward.core.Reward;
import com.event.reward.core.query.RewardSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RewardCreator {

    private final RewardSaveRepository rewardSaveRepository;
    private final RewardSearchRepository rewardSearchRepository;


    /**
     * 일별 리워드 총 등록 수.
     * @param created
     * @return
     */
    private long count(LocalDate created) {
        return rewardSearchRepository.countByCreated(created);
    }

    private long countByUserNo(LocalDate created, long userNo) {
        return rewardSearchRepository.countByTodayUserNo(created, userNo);
    }

    public Reward save(long userNo, int reward) {
        return rewardSaveRepository.save(new Reward(userNo, reward));
    }

    public Reward create(LocalDate created, long userNo) {
        long count = this.count(created);
        if (count >= 10) {
            throw new IllegalArgumentException("일일 한정 최대 보상수 10명을 초과하였습니다.\n" +
                    "내일 다시 도전해주세요.");
        }

        long countByUserNo = this.countByUserNo(created, userNo);
        if (countByUserNo >= 1) {
            throw new IllegalArgumentException("하루에 한번 보상을 받을 수 있습니다.\n" +
                    "내일 다시 도전해주세요.");
        }

        int reward = 100;
        long threeDay = rewardSearchRepository.findThreeDay(userNo);
        if (threeDay == 2) {
            reward = 300;
        }
        long fiveDay = rewardSearchRepository.findFiveDay(userNo);
        if (fiveDay == 4) {
            reward = 500;
        }
        long tenDay = rewardSearchRepository.findTenDay(userNo);
        if (tenDay == 9) {
            reward = 1000;
        }
        return this.save(userNo, reward);
    }
}
