package com.maciejgoralczyk.ESportWebApp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "organizations")
public class Organization{
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "foundation_year")
    private int foundationYear;

    @Override
    public String toString(){
        return "[Organization " + id + "]: " + name + " founded in " + foundationYear + "\n";
    }

}
