package com.event.reward.core.query;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("RewardSearcher")
class RewardSearcherTest extends RewardCrudSupplier {


    @BeforeEach
    void prepareData() {
        getRewardDeleteAll();
    }

    @Nested
    @DisplayName("countByCreated 메소드")
    class Describe_countByCreated {

        @Nested
        @DisplayName("일일참여자 수 조회 요청")
        class Context_today_maximum {

            @BeforeEach
            void prepareData() {
                for (int i = 1; i <= givenUsers; i++) {
                    getRewardSave(i, givenDefaultReward);
                }
            }

            @Test
            @DisplayName("등록된 참여자수를 반환한다")
            void it_return_count() {
                long result = getRewardSearcher().countByCreated(givenCreatedDate);
                assertEquals(givenUsers, result);
            }
        }
    }

    @Nested
    @DisplayName("countByTodayUserNo")
    class Describe_countByTodayUserNo {

        @Nested
        @DisplayName("사용자의 오늘 참여 여부")
        class Context_user_today_maximum {

            private int givenTodayUserNoCount = 1;
            private int givenUserNo = 9999999;

            @BeforeEach
            void prepareData() {
                getRewardSave(givenUserNo, givenDefaultReward);
            }

            @Test
            @DisplayName("오늘 참여수를 반환한다")
            void it_return_count() {
                long result = getRewardSearcher().countByTodayUserNo(givenCreatedDate, givenUserNo);
                assertEquals(givenTodayUserNoCount, result);
            }
        }
    }

    @Nested
    @DisplayName("findThreeDay")
    class Describe_findThreeDay {

        @Nested
        @DisplayName("3일 연속 참여 여부 요청")
        class Context_user_three_count {

            private int givenTotalUserNoCount = 2;
            private int givenUserNo = 9999999;

            @BeforeEach
            void prepareData() {
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(2));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(1));
            }

            @Test
            @DisplayName("총 참여수를 반환한다")
            void it_return_count() {
                long result = getRewardSearcher().findThreeDay(givenUserNo);
                assertEquals(givenTotalUserNoCount, result);
            }
        }
    }

    @Nested
    @DisplayName("findFiveDay")
    class Describe_findFiveDay {

        @Nested
        @DisplayName("5일 연속 참여 여부 요청")
        class Context_user_five_count {

            private int givenTotalUserNoCount = 4;
            private int givenUserNo = 9999999;

            @BeforeEach
            void prepareData() {
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(4));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(3));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(2));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(1));
            }

            @Test
            @DisplayName("총 참여수를 반환한다")
            void it_return_count() {
                long result = getRewardSearcher().findFiveDay(givenUserNo);
                assertEquals(givenTotalUserNoCount, result);
            }
        }
    }

    @Nested
    @DisplayName("findTenDay")
    class Describe_findTenDay {

        @Nested
        @DisplayName("10일 연속 참여 여부 요청")
        class Context_user_today_maximum {

            private int givenTotalUserNoCount = 9;
            private int givenUserNo = 9999999;

            @BeforeEach
            void prepareData() {
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(9));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(8));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(7));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(6));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(5));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(4));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(3));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(2));
                getRewardCreatedSave(givenUserNo, givenDefaultReward, LocalDateTime.now().minusDays(1));

            }

            @Test
            @DisplayName("총 참여수를 반환한다")
            void it_return_count() {
                long result = getRewardSearcher().findTenDay(givenUserNo);
                assertEquals(givenTotalUserNoCount, result);
            }
        }
    }
}