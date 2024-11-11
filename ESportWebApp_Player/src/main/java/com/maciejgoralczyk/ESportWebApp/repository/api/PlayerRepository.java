package com.maciejgoralczyk.ESportWebApp.repository.api;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID>
{
    Player findPlayerById(UUID id);
    List<Player> findPlayersByOrganization(Organization organization);
    Player findPlayerByName(String name);
    List<Player> findPlayersByAge(int age);

}
