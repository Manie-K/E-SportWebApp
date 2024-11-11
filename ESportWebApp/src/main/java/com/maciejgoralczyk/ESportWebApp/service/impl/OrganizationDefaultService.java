package com.maciejgoralczyk.ESportWebApp.service.impl;

import com.maciejgoralczyk.ESportWebApp.dto.PutOrganizationRequestDto;
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
    public Organization create(PutOrganizationRequestDto dto) {
        Organization organization = Organization.builder()
                .name(dto.getName())
                .foundationYear(dto.getFoundationYear())
                .roster(new ArrayList<>())
                .build();
        return repository.save(organization);
    }
    @Override
    public Organization create(Organization organization) {
        return repository.save(organization);
    }

    @Override
    public Organization update(UUID id, PutOrganizationRequestDto dto)
    {
        Organization organization = repository.findOrganizationById(id);

        if(organization == null){
            throw new EntityNotFoundException("Organization not found");
        }

        organization.setName(dto.getName());
        organization.setFoundationYear(dto.getFoundationYear());
        return repository.save(organization);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(String name) {
        Organization org = repository.findOrganizationByName(name);
        if (org == null)
        {
            throw new EntityNotFoundException("Organization not found");
        }
        repository.delete(org);
    }

    @Override
    public void delete(Organization organization) {
        repository.delete(organization);
    }
}
