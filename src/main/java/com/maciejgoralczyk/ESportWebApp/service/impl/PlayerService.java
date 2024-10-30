package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.repository.api.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService
{
    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repo)
    {
        repository = repo;
    }

    public Player getPlayerById(UUID id)
    {
        return repository.findPlayerById(id);
    }
    public Player getPlayerByName(String name)
    {
        return repository.findPlayerByName(name);
    }
    public List<Player> getPlayerByAge(int age)
    {
        return repository.findPlayersByAge(age);
    }

    public List<Player> getPlayersByOrganization(Organization organization)
    {
        return repository.findPlayersByOrganization(organization);
    }

    public List<Player> getAllPlayers()
    {
        return repository.findAll();
    }

    public void addPlayer(Player player)
    {
        repository.save(player);
    }

    public void deletePlayer(UUID id)
    {
        repository.deleteById(id);
    }
}
