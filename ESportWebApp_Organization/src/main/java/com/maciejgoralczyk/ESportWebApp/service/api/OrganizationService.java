package com.maciejgoralczyk.ESportWebApp.service.api;

import com.maciejgoralczyk.ESportWebApp.dto.PutOrganizationRequestDto;
import com.maciejgoralczyk.ESportWebApp.model.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    Organization find(UUID id);
    Organization find(String name);
    List<Organization> findAll();
    List<Organization> findAll(int foundationYear);
    Organization create(PutOrganizationRequestDto dto);
    Organization create(Organization organization);
    Organization update(UUID id, PutOrganizationRequestDto dto);
    void delete(UUID id);
    void delete(String name);
    void delete(Organization organization);
}
