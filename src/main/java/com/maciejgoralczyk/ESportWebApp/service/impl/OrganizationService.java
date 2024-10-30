package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.repository.api.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationService
{
    private final OrganizationRepository repository;

    @Autowired
    public OrganizationService(OrganizationRepository repo)
    {
        repository = repo;
    }


    public Organization getOrganizationById(UUID id)
    {
        return repository.findOrganizationById(id);
    }
    public Organization getOrganizationByName(String name)
    {
        return repository.findOrganizationByName(name);
    }
    public List<Organization> getOrganizationsByFoundationYear(int foundationYear)
    {
        return repository.findOrganizationsByFoundationYear(foundationYear);
    }

    public List<Organization> getAllOrganizations()
    {
        return repository.findAll();
    }
    public void addOrganization(Organization organization)
    {
        repository.save(organization);
    }
}
