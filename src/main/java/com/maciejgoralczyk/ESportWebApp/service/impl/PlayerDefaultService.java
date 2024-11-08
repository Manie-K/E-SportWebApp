package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.dto.PutOrganizationRequestDto;
import com.maciejgoralczyk.ESportWebApp.dto.PutPlayerRequestDto;
import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.repository.api.OrganizationRepository;
import com.maciejgoralczyk.ESportWebApp.repository.api.PlayerRepository;
import com.maciejgoralczyk.ESportWebApp.service.api.PlayerService;
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
        Organization organization = organizationRepository.findOrganizationById(dto.getOrganizationID());

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
        Organization organization = organizationRepository.findOrganizationById(dto.getOrganizationID());
        Player player = repository.findPlayerById(id);
        if (player != null) {
            player.setName(dto.getName());
            player.setAge(dto.getAge());
            player.setOrganization(organization);
            return repository.save(player);
        }
        return null;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(String name) {
        Player player = repository.findPlayerByName(name);
        if (player != null) {
            repository.delete(player);
        }
    }

    @Override
    public void delete(Player player) {
        repository.delete(player);
    }
}
