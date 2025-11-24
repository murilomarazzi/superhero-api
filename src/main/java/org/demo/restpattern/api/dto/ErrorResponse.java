package org.demo.restpattern.api.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String message,
        String error,
        Integer status,
        LocalDateTime timestamp
) {}
