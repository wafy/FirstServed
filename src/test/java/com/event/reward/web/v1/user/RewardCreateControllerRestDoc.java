package com.event.reward.web.v1.user;

import com.event.reward.core.command.RewardCreator;
import com.event.reward.core.command.RewardSaveRepository;
import com.event.reward.test.util.ResourceMockUtil;
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

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RestDocCommonConfig
@AutoConfigureMockMvc
@DisplayName("RewardCreateController: 리워드 생성 요청")
public class RewardCreateControllerRestDoc {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    RewardCreator rewardCreator;

    @Autowired
    RewardSaveRepository rewardSaveRepository;

    @BeforeEach
    void prepareData() {
        rewardSaveRepository.deleteAll();
    }

    @AfterEach
    void after() {
        rewardSaveRepository.deleteAll();
    }

    @Test
    @DisplayName("200. 정상적으로 요청이 주어지면, 리워드 등록한 결과를 반환한다.")
    public void create() throws Exception {
        final ResultActions resultActions =
                mockMvc.perform(post("/v1/user/reward")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ResourceMockUtil.getString("v1/user/command/create-reward.json"))
                ).andExpect(status().isOk());

        resultActions.andDo(document("v1/admin/reward/command/create",
                requestFields(
                        fieldWithPath("userNo").description("사옹자번호")
                ),
                responseFields(
                        fieldWithPath("success").type(JsonFieldType.BOOLEAN)
                                .description("성공여부 성공이면 true 실패이면 false"),
                        fieldWithPath("reason").type(JsonFieldType.OBJECT)
                                .description("성공 실패 내역"),
                        fieldWithPath("reason.id").type(JsonFieldType.NUMBER)
                                .description("리워드 고유번호"),
                        fieldWithPath("reason.userNo").type(JsonFieldType.NUMBER)
                                .description("사용자 고유번호"),
                        fieldWithPath("reason.reward").type(JsonFieldType.NUMBER)
                                .description("받은 리워드"),
                        fieldWithPath("reason.created").type(JsonFieldType.STRING)
                                .description("등록일")
                ))
        );
    }

}
