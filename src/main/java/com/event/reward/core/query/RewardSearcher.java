package com.event.reward.core.query;

import com.event.reward.core.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewardSearcher {
    private final RewardPageSearchRepository searchRepository;

    public Page<Reward> find(LocalDate startAt, LocalDate endAt, Pageable pageable) {
        return searchRepository.findByCreated(startAt, endAt, pageable);
    }

    public Optional<Reward> find(Long rewardId) {
        return searchRepository.findById(rewardId);
    }
}
