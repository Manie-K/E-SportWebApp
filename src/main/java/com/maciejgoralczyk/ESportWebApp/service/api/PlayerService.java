package com.maciejgoralczyk.ESportWebApp.service.api;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerService {
    Player find(UUID id);
    Player find(String name);
    List<Player> findAll();
    List<Player> findAll(int age);
    List<Player> findAll(Organization organization);
    void create(Player player);
    void update(Player player);
    void delete(UUID id);
    void delete(String name);
    void delete(Player player);
}
