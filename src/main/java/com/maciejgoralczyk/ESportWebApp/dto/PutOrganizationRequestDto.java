package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Data;

@Data
public class PutOrganizationRequestDto {
    private String name;
    private int foundationYear;
}
