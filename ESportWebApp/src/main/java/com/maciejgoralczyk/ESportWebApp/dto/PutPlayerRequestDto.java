package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PutPlayerRequestDto {
    private String name;
    private int age;
    private UUID organizationID; //Should it be here?
}
