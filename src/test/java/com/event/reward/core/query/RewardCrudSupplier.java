package com.event.reward.core.query;

import com.event.reward.axiom.target.ForTestOnly;
import com.event.reward.core.Reward;
import com.event.reward.core.command.RewardCreator;
import com.event.reward.core.command.RewardSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 테스트 전용의 리워드 등록 서비스를 제공합니다.
 */
public abstract class RewardCrudSupplier implements ForTestOnly {


    protected  static final LocalDateTime givenCreatedDateTime = LocalDateTime.now();
    protected static final LocalDate givenCreatedDate = LocalDate.now();
    protected static final int givenUsers = 10;
    protected static final int givenDefaultReward = 100;

    @Autowired
    private RewardSaveRepository saveRepository;

    @Autowired
    private RewardSearchRepository searchRepository;

    @Autowired
    private RewardPageSearchRepository pageSearchRepository;

    private RewardCreator rewardCreator;

    private RewardSearcher rewardSearcher;

    private RewardPageSearcher rewardPageSearcher;

    protected RewardSearcher getRewardSearcher() {
        return rewardSearcher == null ? new RewardSearcher(searchRepository) : rewardSearcher;
    }

    protected RewardCreator getRewardCreator() {
        return rewardCreator == null ? new RewardCreator(saveRepository, rewardSearcher) : rewardCreator;
    }

    protected Reward getRewardSave(long userNo, int reward) {
        return saveRepository.save(new Reward(userNo, reward));
    }

    protected Reward getRewardCreatedSave(long userNo, int reward, LocalDateTime created) {
        return saveRepository.save(new Reward(userNo, reward, created));
    }
    protected void getRewardDeleteAll() {
        saveRepository.deleteAll();
    }

    protected RewardPageSearcher getRewardPageSearcher() {
        return rewardPageSearcher == null ? new RewardPageSearcher(pageSearchRepository) : rewardPageSearcher;
    }
}
