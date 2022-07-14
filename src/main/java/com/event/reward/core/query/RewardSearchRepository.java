package com.event.reward.core.query;

import com.event.reward.core.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.time.LocalDate;

@Repository
public interface RewardSearchRepository extends JpaRepository<Reward, Long> {


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "select count(r) from Reward r where function('date_format', r.created , '%Y-%m-%d') = :created")
    long countByCreated(@Param("created") String created);

    @Query(value = "select count(id) from reward_history where date(created)= :created and user_no = :userNo",
            nativeQuery = true)
    long countByTodayUserNo(@Param("created") LocalDate created, @Param("userNo") long userNo);

    @Query(value = "select count(id) from reward_history where user_no = :userNo", nativeQuery = true)
    long countByAllUserNo(@Param("userNo") long userNo);

    @Query(value = "select count(id) from reward_history where user_no= :userNo and date(created) >= date(date_add(now(), interval -2 day))",
            nativeQuery = true)
    long findThreeDay(@Param("userNo") long userNo);

    @Query(value = "select count(id) from reward_history where user_no= :userNo and date(created) >= date(date_add(now(), interval -4 day))",
            nativeQuery = true)
    long findFiveDay(@Param("userNo") long userNo);

    @Query(value = "select count(id) from reward_history where user_no= :userNo and date(created) >= date(date_add(now(), interval -9 day))",
            nativeQuery = true)
    long findTenDay(@Param("userNo") long userNo);
}
