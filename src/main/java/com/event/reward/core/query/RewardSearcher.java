package com.event.reward.core.query;

import com.event.reward.core.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardSearcher {
    private final RewardSearchRepository searchRepository;

    public long countByCreated(LocalDate created) {
        return searchRepository.countByCreated(created);
    }

    public long countByTodayUserNo(LocalDate created, long userNo) {
        return searchRepository.countByTodayUserNo(created, userNo);
    }

    public long countByAllUserNo(long userNo) {
        return searchRepository.countByAllUserNo(userNo);
    }

    public long findThreeDay(long userNo) {
        return searchRepository.findThreeDay(userNo);
    }

    public long findFiveDay(long userNo) {
        return searchRepository.findFiveDay(userNo);
    }

    public long findTenDay(long userNo) {
        return searchRepository.findTenDay(userNo);
    }


}
