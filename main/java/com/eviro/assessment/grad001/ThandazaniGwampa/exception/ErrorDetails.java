package com.eviro.assessment.grad001.ThandazaniGwampa.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ErrorDetails {
    private String message;
    private LocalDateTime date;
}
