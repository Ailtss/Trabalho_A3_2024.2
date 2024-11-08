package com.example.crud.domain.comments;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record RequestComments(
        String id,

        @NotBlank
        String product_name,
        @NotBlank
        String user_email,
        @NotBlank
        String comment_text,
        LocalDateTime created_at,
        @NotBlank
        String user_name) {
}
