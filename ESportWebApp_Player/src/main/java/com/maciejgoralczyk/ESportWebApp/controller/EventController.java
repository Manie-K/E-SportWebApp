package com.maciejgoralczyk.ESportWebApp.controller;

import com.maciejgoralczyk.ESportWebApp.event.OrganizationEvent;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/events/organization")
public class EventController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<String> createdEvent(@RequestBody OrganizationEvent event) {
        organizationService.create(Organization.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .roster(new ArrayList<>())
                        .build());

        return ResponseEntity.ok("[" + event.getName() + ", " + event.getId() + "] created");
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletedEvent(@RequestBody OrganizationEvent event) {
        Organization organization = organizationService.find(event.getId());
        if(organization == null) {
            return ResponseEntity.notFound().build();
        }

        organizationService.delete(event.getId());
        return ResponseEntity.noContent().build();
    }
}
