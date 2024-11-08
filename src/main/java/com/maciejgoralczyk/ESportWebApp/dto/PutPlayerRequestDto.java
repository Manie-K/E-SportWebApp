package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PutPlayerRequestDto {
    private String name;
    private int age;
    private UUID organizationID; //Should it be here?
}
