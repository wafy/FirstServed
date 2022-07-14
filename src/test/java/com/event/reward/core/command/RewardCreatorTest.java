package com.event.reward.core.command;

import com.event.reward.core.Reward;
import com.event.reward.core.query.RewardCrudSupplier;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("RewardCreator")
class RewardCreatorTest extends RewardCrudSupplier {

    @BeforeEach
    void prepareData() {
        getRewardDeleteAll();
    }

    @Nested
    @DisplayName("create 메소드")
    class Describe_create {

        @Nested
        @DisplayName("리워드 생성 요청이 주어지면")
        class Context_normal_create_request {

            @Test
            @DisplayName("리워드를 생성해 리턴한다")
            void it_returns_created_reward() {
                final Reward savedReward = getRewardSave(givenUserNo, givenDefaultReward);
                assertNotNull(savedReward);
                assertNotNull(savedReward.getId(), "새로 생성된 리워드 아이디를 갖고 있어야 합니다.");
            }
        }

        @Nested
        @DisplayName("하루 최대 참여인원 초과 요청이 주어지면")
        class Context_over_create_request {

            @BeforeEach
            void prepareData() {
                for (int i = 1; i < 11; i++) {
                    getRewardSave(i, givenDefaultReward);
                }
            }

            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> getRewardCreator().create(givenCreatedDate, givenUserNo));
            }
        }

        @Nested
        @DisplayName("하루 참여수 제한 초과 요청이 주어지면")
        class Context_over_create_user_request {

            @BeforeEach
            void prepareData() {
                getRewardSave(givenUserNo, givenDefaultReward);
            }

            @Test
            @DisplayName("예외를 던진다")
            void it_throws_exception() {
                Assertions.assertThrows(IllegalArgumentException.class,
                        () -> getRewardCreator().create(givenCreatedDate, givenUserNo));
            }
        }
    }
}