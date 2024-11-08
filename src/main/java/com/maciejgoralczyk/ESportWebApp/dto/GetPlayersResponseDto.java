package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class GetPlayersResponseDto {
    @Singular
    private List<GetPlayerResponseDto> players;
}
