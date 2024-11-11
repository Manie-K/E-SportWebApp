package com.maciejgoralczyk.ESportWebApp.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrganizationEvent {
    private UUID id;
    private String name;
}
