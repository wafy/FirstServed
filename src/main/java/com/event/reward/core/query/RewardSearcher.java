package com.event.reward.core.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class RewardSearcher {
    private final RewardSearchRepository searchRepository;

    @Transactional
    public long countByCreated(LocalDate created) {
        return searchRepository.countByCreated(created.toString());
    }

    public long countByTodayUserNo(LocalDate created, long userNo) {
        return searchRepository.countByTodayUserNo(created, userNo);
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
