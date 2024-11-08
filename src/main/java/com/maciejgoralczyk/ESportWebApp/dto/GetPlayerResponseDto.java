package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GetPlayerResponseDto {
    private UUID id;
    private String name;
    private int age;
    private String organizationName;
}
