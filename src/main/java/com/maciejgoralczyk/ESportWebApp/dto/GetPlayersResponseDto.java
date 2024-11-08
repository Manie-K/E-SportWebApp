package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
public class GetPlayersResponseDto {
    @Singular
    private List<GetPlayerResponseDto> players;
}
