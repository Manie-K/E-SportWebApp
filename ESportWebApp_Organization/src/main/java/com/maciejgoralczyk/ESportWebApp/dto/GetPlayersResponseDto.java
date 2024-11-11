package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;
import java.util.UUID;

@Data
public class GetPlayersResponseDto {
    @Singular
    private List<GetPlayerResponseDto> players;
}
