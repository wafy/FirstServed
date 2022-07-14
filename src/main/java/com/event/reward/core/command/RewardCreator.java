package com.event.reward.core.command;

import com.event.reward.core.Reward;
import com.event.reward.core.query.RewardSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RewardCreator {

    private final RewardSaveRepository rewardSaveRepository;
    private final RewardSearcher rewardSearcher;

    private static final int MAXIMUM_COUNT = 10; //일일 최대 응모자 수
    private static final int TODAY_COUNT = 1; //사용자별일일 참여 제한 수
    private static final int DEFAULT_REWARD = 100; //일일 참여 기본 리워드
    private static final int THREE_COUNT_REWARD = 300; // 3일연속 참여 리워드
    private static final int FIVE_COUNT_REWARD = 500; // 5일연속 참여 리워드
    private static final
    int TEN_COUNT_REWARD = 1000; //10일연속 참여 리워드


    /**
     * 일별 리워드 총 등록 수.
     * @param created
     * @return
     */
    private long count(LocalDate created) {
        return rewardSearcher.countByCreated(created);
    }

    private long countByUserNo(LocalDate created, long userNo) {
        return rewardSearcher.countByTodayUserNo(created, userNo);
    }

    public Reward save(long userNo, int reward) {
        return rewardSaveRepository.save(new Reward(userNo, reward));
    }

    @Transactional
    public Reward create(LocalDate created, long userNo) {
        long count = this.count(created);
        if (count >= MAXIMUM_COUNT) {
            throw new IllegalArgumentException("일일 한정 최대 보상수 10명을 초과하였습니다.\n" +
                    "내일 다시 도전해주세요.");
        }

        long countByUserNo = this.countByUserNo(created, userNo);
        if (countByUserNo >= TODAY_COUNT) {
            throw new IllegalArgumentException("하루에 한번 보상을 받을 수 있습니다.\n" +
                    "내일 다시 도전해주세요.");
        }

        int reward = DEFAULT_REWARD;
        long threeDay = rewardSearcher.findThreeDay(userNo);
        if (threeDay == 2) {
            reward = THREE_COUNT_REWARD;
        }
        long fiveDay = rewardSearcher.findFiveDay(userNo);
        if (fiveDay == 4) {
            reward = FIVE_COUNT_REWARD;
        }
        long tenDay = rewardSearcher.findTenDay(userNo);
        if (tenDay == 9) {
            reward = TEN_COUNT_REWARD;
        }
        return this.save(userNo, reward);
    }
}
