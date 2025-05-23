package com.maciejgoralczyk.ESportWebApp.controller;

import com.maciejgoralczyk.ESportWebApp.dto.GetPlayerResponseDto;
import com.maciejgoralczyk.ESportWebApp.dto.GetPlayersResponseDto;
import com.maciejgoralczyk.ESportWebApp.dto.PutPlayerRequestDto;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import com.maciejgoralczyk.ESportWebApp.service.api.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private static final org.apache.commons.logging.Log log = LogFactory.getLog(PlayerController.class);
    @Autowired
    private PlayerService playerService;
    @Autowired
    private OrganizationService organizationService;

    private GetPlayerResponseDto playerToGetPlayerResponseDto(Player player) {
        if (player == null) {
            throw new EntityNotFoundException("Player not found");
        }
        Organization org = player.getOrganization();
        UUID orgId = null;
        if(org != null) {
            orgId = org.getId();
        }

        return GetPlayerResponseDto.builder()
                .id(player.getId())
                .name(player.getName())
                .age(player.getAge())
                .organizationId(orgId)
                .build();
    }
    @PostMapping
    public ResponseEntity<GetPlayerResponseDto> createPlayer(@RequestBody PutPlayerRequestDto dto) {
        Player player = playerService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerToGetPlayerResponseDto(player));
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetPlayerResponseDto> getPlayer(@PathVariable UUID id) {
        Player player = playerService.find(id);
        return ResponseEntity.ok(playerToGetPlayerResponseDto(player));
    }

    @GetMapping
    public ResponseEntity<GetPlayersResponseDto> getPlayers() {
        List<Player> players = playerService.findAll();
        return ResponseEntity.ok(GetPlayersResponseDto.builder()
                .players(players.stream()
                        .map(this::playerToGetPlayerResponseDto)
                        .toList())
                .build());
    }
    @GetMapping("/organization/{organizationID}")
    public ResponseEntity<GetPlayersResponseDto> getPlayersFromOrganization(@PathVariable UUID organizationID) {
        log.debug("getPlayersFromOrganization");
        Organization organization = organizationService.find(organizationID);
        if(organization == null)
        {
            throw new EntityNotFoundException("Organization not found");
        }
        List<Player> players = playerService.findAll(organization);

        return ResponseEntity.ok(GetPlayersResponseDto.builder()
                .players(players.stream()
                        .map(this::playerToGetPlayerResponseDto)
                        .toList())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPlayerResponseDto> updatePlayer(@PathVariable UUID id, @RequestBody PutPlayerRequestDto dto) {
        Player player = playerService.update(id, dto);
        return ResponseEntity.ok(playerToGetPlayerResponseDto(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable UUID id) {
        if(playerService.find(id) == null)
        {
            throw new EntityNotFoundException("Player not found");
        }
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}