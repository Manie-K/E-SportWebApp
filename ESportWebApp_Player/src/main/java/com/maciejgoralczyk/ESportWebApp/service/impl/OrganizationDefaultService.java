package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import com.maciejgoralczyk.ESportWebApp.repository.api.OrganizationRepository;
import com.maciejgoralczyk.ESportWebApp.service.api.OrganizationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    @Override
    public void delete(UUID id) {
        Organization organization = repository.findOrganizationById(id);
        if (organization == null) {
            throw new EntityNotFoundException("Organization not found");
        }
        repository.deleteById(id);
    }

}
