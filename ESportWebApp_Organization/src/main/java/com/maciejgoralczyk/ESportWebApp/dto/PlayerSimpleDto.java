package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PlayerSimpleDto {
    UUID id;
    String name;
}
