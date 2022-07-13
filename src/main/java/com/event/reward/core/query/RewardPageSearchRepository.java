package com.event.reward.core.query;

import com.event.reward.core.Reward;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface RewardPageSearchRepository extends JpaRepository<Reward, Long> {
    @Query(value = "select * from reward_history where date(created) between date(:startAt) and date(:endAt)", nativeQuery = true)
    Page<Reward> findByCreated(@Param("startAt") LocalDate startAt,
                               @Param("endAt") LocalDate endAt,
                               Pageable pageable);

    Optional<Reward> findById(Long rewardId);
}
