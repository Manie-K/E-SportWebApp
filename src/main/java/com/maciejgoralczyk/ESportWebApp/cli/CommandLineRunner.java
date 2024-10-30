package com.maciejgoralczyk.ESportWebApp.cli;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.model.Player;
import com.maciejgoralczyk.ESportWebApp.service.impl.OrganizationService;
import com.maciejgoralczyk.ESportWebApp.service.impl.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner
{
    private final OrganizationService organizationService;
    private final PlayerService playerService;

    @Autowired
    public CommandLineRunner(OrganizationService organizationService, PlayerService playerService) {
        this.organizationService = organizationService;
        this.playerService = playerService;
    }

    @Override
    public void run(String... args) throws Exception {
        String command;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWelcome to ESportWebApp!");

        while(true)
        {
            System.out.println(" ");
            System.out.println("Available commands: ");
            System.out.println("1. list organizations");
            System.out.println("2. list players");
            System.out.println("3. add player");
            System.out.println("4. delete player");
            System.out.println("5. exit application");
            System.out.println("======================");
            System.out.println("Enter command: ");
            command = scanner.nextLine();

            switch(command.toLowerCase())
            {
                case "1":
                    listOrganizations();
                    break;
                case "2":
                    listPlayers();
                    break;
                case "3":
                    addPlayer();
                    break;
                case "4":
                    deletePlayer();
                    break;
                case "5":
                    System.out.println("Exiting application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input. Try again.");
                    break;
            }
        }
    }

    private void deletePlayer() {
        System.out.println("Enter player name to delete: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        Player toDelete = playerService.getPlayerByName(name);
        if(toDelete == null)
        {
            System.out.println("Player not found.");
            return;
        }

        UUID id = toDelete.getId();
        playerService.deletePlayer(id);
        System.out.println("Player with id = " + id + " deleted.");
    }

    private void addPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player name: ");
        String name = scanner.nextLine();
        System.out.println("Enter player age: ");
        int age = scanner.nextInt();
        System.out.println("Enter player organization: ");
        String organizationName = scanner.next();
        Organization organization = organizationService.getOrganizationByName(organizationName);

        if (organization == null)
        {
            System.out.println("Organization not found.");
            return;
        }

        Player player = Player.builder()
            .name(name)
            .age(age)
            .organization(organization)
            .build();

        playerService.addPlayer(player);

        System.out.println("Player added.");
    }

    private void listPlayers() {
        List<Player> players = playerService.getAllPlayers();
        System.out.println("Players: ");
        for(Player player : players)
        {
            System.out.println(player);
        }
    }

    private void listOrganizations() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        System.out.println("Organizations: ");
        for(Organization organization : organizations)
        {
            System.out.println(organization);
        }
    }

}
