package com.maciejgoralczyk.ESportWebApp.controller;

import com.maciejgoralczyk.ESportWebApp.dto.*;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private RestTemplate restTemplate;

    private GetOrganizationResponseDto organizationToGetOrganizationResponseDto(Organization organization) {
        if(organization == null) {
            throw new EntityNotFoundException("Organization not found");
        }

        var dto = GetOrganizationResponseDto.builder()
                .id(organization.getId())
                .name(organization.getName())
                .foundationYear(organization.getFoundationYear())
                .roster(new ArrayList<>())
                .build();


        String url = "http://localhost:8081/api/players/organization/" + organization.getId();
        GetPlayersResponseDto playersDto = restTemplate.getForObject(url, GetPlayersResponseDto.class);

        var roster = playersDto.getPlayers().stream()
                .map(player -> PlayerSimpleDto.builder().name(player.getName()).build())
                .toList();

        dto.setRoster(roster);
        return dto;
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
