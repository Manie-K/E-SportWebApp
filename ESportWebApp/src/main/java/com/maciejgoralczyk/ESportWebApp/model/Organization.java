package com.maciejgoralczyk.ESportWebApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organization{
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "foundation_year")
    private int foundationYear;

    @OneToMany(mappedBy = "organization", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Player> roster = new ArrayList<>();
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("[Organization " + id + "]: " + name + " founded in " + foundationYear + "\n");

        for (Player player : roster) {
            stringBuilder.append("----").append(player.getName()).append("\n");
        }
        return stringBuilder.toString();
    }

}
