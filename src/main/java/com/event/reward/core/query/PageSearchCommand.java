package com.event.reward.core.query;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PageSearchCommand {
    public PageSearchCommand() {}

    private LocalDateTime created;
}
