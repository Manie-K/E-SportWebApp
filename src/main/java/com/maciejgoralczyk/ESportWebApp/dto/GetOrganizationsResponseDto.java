package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
public class GetOrganizationsResponseDto {
    @Singular
    List<GetOrganizationResponseDto> organizations;
}
