package com.maciejgoralczyk.ESportWebApp.service.api;

import com.maciejgoralczyk.ESportWebApp.model.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    Organization find(UUID id);
    Organization find(String name);
    List<Organization> findAll();
    List<Organization> findAll(int foundationYear);
    void create(Organization organization);
    void update(Organization organization);
    void delete(UUID id);
    void delete(String name);
    void delete(Organization organization);
}
