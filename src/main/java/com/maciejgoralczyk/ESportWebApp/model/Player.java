package com.maciejgoralczyk.ESportWebApp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "players")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player{
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne()
    @JoinColumn(name = "organization_id")
    @EqualsAndHashCode.Exclude
    private Organization organization;

    @Override
    public String toString(){
        String str = "[Player " + id + "]: " + name + ", age " + age + ", " + organization.getName() + "\n";
        return str;
    }

}
