package com.maciejgoralczyk.ESportWebApp.controller;

import com.maciejgoralczyk.ESportWebApp.dto.*;
import com.maciejgoralczyk.ESportWebApp.event.OrganizationEvent;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    @Value("${PLAYER_SERVICE_APPNAME}")
    private String playerServiceAppName;
    private final OrganizationService organizationService;
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    public OrganizationController(OrganizationService organizationService, RestTemplate restTemplate,
                                  DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient) {
        this.organizationService = organizationService;
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

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

        String url = getPlayerServiceUrl() + "/api/players/organization/" + organization.getId();
        GetPlayersResponseDto playersDto = restTemplate.getForObject(url, GetPlayersResponseDto.class);

        if(playersDto == null || playersDto.getPlayers() == null) {
            return dto;
        }

        var roster = playersDto.getPlayers().stream()
                .map(player -> PlayerSimpleDto.builder()
                        .name(player.getName())
                        .id(player.getId())
                        .build())
                .toList();

        dto.setRoster(roster);
        return dto;
    }

    private String getPlayerServiceUrl() {
        String playerServiceUrl = loadBalancerClient.choose(playerServiceAppName).getUri().toString();
        return playerServiceUrl;
    }

    @PostMapping
    public ResponseEntity<GetOrganizationResponseDto> createOrganization(@RequestBody PutOrganizationRequestDto dto) {
        Organization organization = organizationService.create(dto);
        OrganizationEvent event = new OrganizationEvent(organization.getId(), organization.getName());

        restTemplate.postForEntity(getPlayerServiceUrl() + "/api/events/organization/create", event, null);


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
        Organization organization = organizationService.find(id);
        if(organization == null)
        {
            throw new EntityNotFoundException("Organization not found");
        }
        OrganizationEvent event = new OrganizationEvent(organization.getId(), null);

        restTemplate.postForEntity(getPlayerServiceUrl() + "/api/events/organization/delete", event,
                null);

        organizationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
