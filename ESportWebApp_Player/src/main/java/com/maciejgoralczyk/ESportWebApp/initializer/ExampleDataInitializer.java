package com.maciejgoralczyk.ESportWebApp.initializer;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.service.impl.OrganizationDefaultService;
import com.maciejgoralczyk.ESportWebApp.service.impl.PlayerDefaultService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(1)
public class ExampleDataInitializer
{
    private final PlayerDefaultService playerService;
    private final OrganizationDefaultService organizationService;

    @Autowired
    public ExampleDataInitializer(PlayerDefaultService playerService, OrganizationDefaultService organizationService) {
        this.playerService = playerService;
        this.organizationService = organizationService;
    }

    @PostConstruct
    public void init()
    {
        List<Player> players = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();


        Organization vp = Organization.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                .build();
        organizations.add(vp);

        Organization fnatic = Organization.builder()
                .id(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                .build();
        organizations.add(fnatic);


        Player pashaBiceps = Player.builder()
                .id(UUID.fromString("10000000-0000-0000-0000-000000000000"))
                .name("pashaBiceps")
                .age(35)
                .organization(vp)
                .build();
        players.add(pashaBiceps);

        Player byali = Player.builder()
                .id(UUID.fromString("20000000-0000-0000-0000-000000000000"))
                .name("byali")
                .age(28)
                .organization(vp)
                .build();
        players.add(byali);

        Player olofmeister = Player.builder()
                .id(UUID.fromString("30000000-0000-0000-0000-000000000000"))
                .name("olofmeister")
                .age(37)
                .organization(fnatic)
                .build();
        players.add(olofmeister);

        Player jw = Player.builder()
                .id(UUID.fromString("40000000-0000-0000-0000-000000000000"))
                .name("jw")
                .age(30)
                .organization(fnatic)
                .build();
        players.add(jw);


        for (Organization organization : organizations)
        {
            organizationService.create(organization);
        }
        for(Player player : players)
        {
            playerService.create(player);
        }
    }

}
