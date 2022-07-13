package com.event.reward.core.command;

import com.event.reward.core.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardSaveRepository extends JpaRepository<Reward, Long> {
    Reward save(Reward reward);
}
