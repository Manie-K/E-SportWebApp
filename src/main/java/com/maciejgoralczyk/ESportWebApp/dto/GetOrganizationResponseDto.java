package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GetOrganizationResponseDto {
    private UUID id;
    private String name;
    private int foundationYear;
    private List<String> roster;
}
