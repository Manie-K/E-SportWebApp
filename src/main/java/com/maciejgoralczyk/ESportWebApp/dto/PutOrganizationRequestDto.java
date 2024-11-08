package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PutOrganizationRequestDto {
    private String name;
    private int foundationYear;
}
