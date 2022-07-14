package com.event.reward.core.query;

import com.event.reward.core.Reward;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("RewardPageSearcher")
class RewardPageSearcherTest extends RewardCrudSupplier {

    @BeforeEach
    void prepareData() {
        getRewardDeleteAll();
    }

    @Nested
    @DisplayName("find page 메소드")
    class Describe_find_page {

        @Nested
        @DisplayName("저장된 참여자수가 있다면")
        class Context_created_query {

            @BeforeEach
            void prepareData() {
                getRewardCreatedSave(1, givenDefaultReward, givenCreatedDateTime.minusDays(3));
                getRewardCreatedSave(2, givenDefaultReward, givenCreatedDateTime.minusDays(2));
                getRewardCreatedSave(3, givenDefaultReward, givenCreatedDateTime.minusDays(1));
                getRewardCreatedSave(4, givenDefaultReward, givenCreatedDateTime);
            }

            @Nested
            @DisplayName("조회 정렬 순서를 desc으로 요청한다면")
            class Context_order_desc {

                @Test
                @DisplayName("등록일 기준 내림차순으로 참여자 목록을 반환한다")
                void it_returns_reward_list_sort_desc() {
                    PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("created").descending());
                    Page<Reward> rewardList = getRewardPageSearcher().find(givenCreatedDate, givenCreatedDate, pageRequest);
                    assertEquals(givenCreatedDateTime, rewardList.getContent().get(0).getCreated());
                }
            }

            @Nested
            @DisplayName("조회 정렬 순서를 asc로 요청한다면")
            class Context_order_asc {

                @Test
                @DisplayName("등록일 기준 오름차순으로 참여자 목록을 반환한다")
                void it_returns_reward_list_sort_desc() {
                    Pageable pageable = PageRequest.of(0, 10, Sort.by("created").ascending());
                    Page<Reward> rewardList = getRewardPageSearcher().find(givenCreatedDate.minusDays(3), givenCreatedDate, pageable);
                    assertEquals(givenCreatedDateTime.minusDays(3), rewardList.getContent().get(0).getCreated());
                }
            }
        }
    }
}