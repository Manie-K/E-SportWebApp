package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GetOrganizationResponseDto {
    private UUID id;
    private String name;
    private int foundationYear;
    private List<String> roster;
}
