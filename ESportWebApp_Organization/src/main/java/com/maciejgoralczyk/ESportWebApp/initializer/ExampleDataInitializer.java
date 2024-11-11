package com.maciejgoralczyk.ESportWebApp.initializer;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.service.impl.OrganizationDefaultService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(1)
public class ExampleDataInitializer
{
    private final OrganizationDefaultService organizationService;

    @Autowired
    public ExampleDataInitializer(OrganizationDefaultService organizationService) {
        this.organizationService = organizationService;
    }

    @PostConstruct
    public void init()
    {
        List<Organization> organizations = new ArrayList<>();

        Organization vp = Organization.builder()
                .name("Virtus.pro")
                .foundationYear(2003)
                .build();
        organizations.add(vp);

        Organization fnatic = Organization.builder()
                .name("Fnatic")
                .foundationYear(2004)
                .build();
        organizations.add(fnatic);

        for (Organization organization : organizations)
        {
            organizationService.create(organization);
        }
    }

}
