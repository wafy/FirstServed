package com.event.reward.web.v1.admin;

import com.event.reward.core.Reward;
import com.event.reward.core.command.RewardCreator;
import com.event.reward.core.command.RewardSaveRepository;
import com.event.reward.test.util.RestDocCommonConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RestDocCommonConfig
@AutoConfigureMockMvc
@DisplayName("RewardSearchController: 리워드 조회 요청")
public class RewardSearchControllerRestDoc {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RewardCreator rewardCreator;

    @Autowired
    RewardSaveRepository rewardSaveRepository;

    long savedRewardId;

    @BeforeEach
    void prepareData() {
        rewardSaveRepository.deleteAll();
        long mockUserNo = 999L;
        LocalDate mockCreatedD = LocalDate.now();
        Reward reward = rewardCreator.create(mockCreatedD, mockUserNo);
        savedRewardId = reward.getId();
    }
    @AfterEach
    void after() {
        rewardSaveRepository.deleteAll();
    }

    @Test
    @DisplayName("200. 정상적으로 요청이 주어지면, 리워드 조회 결과를 반환한다.")
    public void findById() throws Exception {
        final ResultActions resultActions =
                mockMvc.perform(get("/v1/admin/reward/{rewardId}",savedRewardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

        resultActions.andDo(document("v1/admin/reward/query/search",
                pathParameters(
                        parameterWithName("rewardId").description("리워드 아이디")
                ),
                responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER)
                                .description("리워드 아이디"),
                        fieldWithPath("userNo").type(JsonFieldType.NUMBER)
                                .description("사용자번호"),
                        fieldWithPath("reward").type(JsonFieldType.NUMBER)
                                .description("받은 리워드"),
                        fieldWithPath("created").type(JsonFieldType.STRING)
                                .description("등록일")
                )));
    }
}
