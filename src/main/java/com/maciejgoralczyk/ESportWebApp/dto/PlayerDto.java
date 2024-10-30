package com.maciejgoralczyk.ESportWebApp.dto;

import lombok.*;

import java.util.UUID;

@Data
public class PlayerDto{
    private UUID id;

    private String name;

    private int age;

    private String organizationName;


    @Override
    public String toString(){
        String str = "[Player]: " + name + ", age " + age + ", " + organizationName + "\n";
        return str;
    }

}
