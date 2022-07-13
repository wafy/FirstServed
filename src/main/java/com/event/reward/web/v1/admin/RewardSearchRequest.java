package com.event.reward.web.v1.admin;

import com.event.reward.web.v1.user.command.RewardRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static com.event.reward.config.DateTimeConfig.DATE_FORMAT;

@Getter
public class RewardSearchRequest {
    @DateTimeFormat(pattern = DATE_FORMAT)
    private LocalDate startAt;

    @DateTimeFormat(pattern = DATE_FORMAT)
    private LocalDate endAt;

    private int page;
    private int size;
    private String sort;


    public RewardSearchRequest(LocalDate startAt, LocalDate endAt, int page, int size, String sort) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.page = page;
        this.size = size;
        this.sort = sort;
    }
}
