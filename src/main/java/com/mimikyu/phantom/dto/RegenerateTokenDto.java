package com.mimikyu.phantom.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegenerateTokenDto {
    private String refresh_token;
}
