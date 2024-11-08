package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.repository.api.OrganizationRepository;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizationDefaultService implements OrganizationService {
    private final OrganizationRepository repository;

    @Autowired
    public OrganizationDefaultService(OrganizationRepository repository) {
        this.repository = repository;
    }


    @Override
    public Organization find(UUID id) {
        return repository.findOrganizationById(id);
    }

    @Override
    public Organization find(String name) {
        return repository.findOrganizationByName(name);
    }

    @Override
    public List<Organization> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Organization> findAll(int foundationYear) {
        return repository.findOrganizationsByFoundationYear(foundationYear);
    }

    @Override
    public void create(Organization organization) {
        repository.save(organization);
    }

    @Override
    public void update(Organization organization) {
        repository.save(organization);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(String name) {
        Organization org = repository.findOrganizationByName(name);
        if (org != null){
            repository.delete(org);
        }
    }

    @Override
    public void delete(Organization organization) {
        repository.delete(organization);
    }
}
