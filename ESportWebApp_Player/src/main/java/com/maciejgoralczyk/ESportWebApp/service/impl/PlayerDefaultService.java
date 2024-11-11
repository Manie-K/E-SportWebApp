package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.dto.PutPlayerRequestDto;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.repository.api.OrganizationRepository;
import com.maciejgoralczyk.ESportWebApp.repository.api.PlayerRepository;
import com.maciejgoralczyk.ESportWebApp.service.api.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerDefaultService implements PlayerService {

    private final PlayerRepository repository;
    private final OrganizationRepository organizationRepository;

    @Autowired
    public PlayerDefaultService(PlayerRepository repository, OrganizationRepository organizationRepository) {
        this.repository = repository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Player find(UUID id) {
        return repository.findPlayerById(id);
    }

    @Override
    public Player find(String name) {
        return repository.findPlayerByName(name);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Player> findAll(int age) {
        return repository.findPlayersByAge(age);
    }

    @Override
    public List<Player> findAll(Organization organization) {
        return repository.findPlayersByOrganization(organization);
    }

    @Override
    public Player create(PutPlayerRequestDto dto) {
        if(dto.getOrganizationId() == null) {
            throw new IllegalArgumentException("Dto.OrganizationID cannot be null");
        }

        Organization organization = organizationRepository.findOrganizationById(dto.getOrganizationId());
        if(organization == null) {
            throw new EntityNotFoundException("Organization not found");
        }

        Player player = Player.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .organization(organization)
                .build();

        return repository.save(player);
    }
    @Override
    public Player create(Player player) {
        return repository.save(player);
    }

    @Override
    public Player update(UUID id, PutPlayerRequestDto dto)
    {
        Player player = repository.findPlayerById(id);
        if (player == null) {
            throw new EntityNotFoundException("Player not found");
        }

        if(dto.getOrganizationId() == null) {
            throw new IllegalArgumentException("Dto.OrganizationID cannot be null");
        }

        Organization organization = organizationRepository.findOrganizationById(dto.getOrganizationId());
        if(organization == null) {
            throw new EntityNotFoundException("Organization not found");
        }

        player.setName(dto.getName());
        player.setAge(dto.getAge());
        player.setOrganization(organization);

        return repository.save(player);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(String name) {
        Player player = repository.findPlayerByName(name);
        if (player == null) {
            throw new EntityNotFoundException("Player not found");
        }
        repository.delete(player);
    }

    @Override
    public void delete(Player player) {
        repository.delete(player);
    }
}
