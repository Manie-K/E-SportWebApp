package com.maciejgoralczyk.ESportWebApp.controller;

import com.maciejgoralczyk.ESportWebApp.dto.GetOrganizationResponseDto;
import com.maciejgoralczyk.ESportWebApp.dto.GetOrganizationsResponseDto;
import com.maciejgoralczyk.ESportWebApp.dto.PutOrganizationRequestDto;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    private GetOrganizationResponseDto organizationToGetOrganizationResponseDto(Organization organization)
    {
        if(organization == null) {
            throw new EntityNotFoundException("Organization not found");
        }
        return GetOrganizationResponseDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .foundationYear(organization.getFoundationYear())
                .roster(organization.getRoster().stream()
                        .map(Player::getName)
                        .toList())
                .build();
    }

    @PostMapping
    public ResponseEntity<GetOrganizationResponseDto> createOrganization(@RequestBody PutOrganizationRequestDto dto) {
        Organization organization = organizationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationToGetOrganizationResponseDto(organization));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizationResponseDto> getOrganization(@PathVariable UUID id) {
        Organization organization = organizationService.find(id);
        return ResponseEntity.ok(organizationToGetOrganizationResponseDto(organization));
    }

    @GetMapping
    public ResponseEntity<GetOrganizationsResponseDto> getOrganizations()
    {
        List<Organization> organizations = organizationService.findAll();
        return ResponseEntity.ok(GetOrganizationsResponseDto.builder()
                .organizations(organizations.stream()
                        .map(this::organizationToGetOrganizationResponseDto)
                        .toList())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetOrganizationResponseDto> updateOrganization(@PathVariable UUID id, @RequestBody PutOrganizationRequestDto dto) {
        Organization organization = organizationService.update(id, dto);
        return ResponseEntity.ok(organizationToGetOrganizationResponseDto(organization));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable UUID id) {
        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
