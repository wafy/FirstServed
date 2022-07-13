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
import static org.springframework.restdocs.request.RequestDocumentation.*;
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
                mockMvc.perform(get("/v1/admin/reward/{rewardId}", savedRewardId)
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
                ))
        );
    }

    @Test
    @DisplayName("200. 정상적으로 요청이 주어지면, 리워드 조회 결과를 paging 하여 반환한다.")
    public void findPaging() throws Exception {
        final ResultActions resultActions =
                mockMvc.perform(get("/v1/admin/reward")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "desc")
                        .param("startAt", "2022-07-01")
                        .param("endAt", "2022-07-30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

        resultActions.andDo(document("v1/admin/reward/query/search-page",
                requestParameters(
                        parameterWithName("page").description("페이지 번호"),
                        parameterWithName("size").description("페이지 사이즈"),
                        parameterWithName("sort").description("정렬조건 내림차순이면 desc, 오름차순이면 asc"),
                        parameterWithName("startAt").description("YYYY-MM-DD"),
                        parameterWithName("endAt").description("YYYY-MM-DD")
                ),
                responseFields(
                        fieldWithPath("content").type(JsonFieldType.ARRAY)
                                .description(""),
                        fieldWithPath("content[0].id").type(JsonFieldType.NUMBER)
                                .description("리워드 아이디"),
                        fieldWithPath("content[0].userNo").type(JsonFieldType.NUMBER)
                                .description("사용자번호"),
                        fieldWithPath("content[0].reward").type(JsonFieldType.NUMBER)
                                .description("받은 리워드"),
                        fieldWithPath("content[0].created").type(JsonFieldType.STRING)
                                .description("등록일"),
                        fieldWithPath("pageable").type(JsonFieldType.OBJECT)
                                .description(""),
                        fieldWithPath("pageable.sort").type(JsonFieldType.OBJECT)
                                .description(""),
                        fieldWithPath("pageable.sort.sorted").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("pageable.sort.unsorted").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("pageable.sort.empty").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("pageable.pageNumber").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("pageable.pageSize").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("pageable.offset").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("pageable.paged").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("pageable.unpaged").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("totalPages").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("totalElements").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("last").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("numberOfElements").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("number").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("size").type(JsonFieldType.NUMBER)
                                .description(""),
                        fieldWithPath("sort").type(JsonFieldType.OBJECT)
                                .description(""),
                        fieldWithPath("sort.sorted").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("sort.unsorted").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("sort.empty").type(JsonFieldType.BOOLEAN)
                                .description(""),
                        fieldWithPath("first").type(JsonFieldType.BOOLEAN).description(""),
                        fieldWithPath("empty").type(JsonFieldType.BOOLEAN).description("")
                )

        ));
    }
}
