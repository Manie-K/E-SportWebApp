package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class GetOrganizationsResponseDto {
    @Singular
    List<GetOrganizationResponseDto> organizations;
}
