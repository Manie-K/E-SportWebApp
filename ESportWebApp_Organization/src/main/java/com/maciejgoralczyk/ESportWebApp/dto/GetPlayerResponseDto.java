package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetPlayerResponseDto {
    private UUID id;
    private String name;
    private int age;
    private UUID organizationId;
}
