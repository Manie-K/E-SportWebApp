package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.repository.api.PlayerRepository;
import com.maciejgoralczyk.ESportWebApp.service.api.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerDefaultService implements PlayerService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerDefaultService(PlayerRepository repository) {
        this.repository = repository;
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
    public void create(Player player) {
        repository.save(player);
    }

    @Override
    public void update(Player player) {
        repository.save(player);
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
