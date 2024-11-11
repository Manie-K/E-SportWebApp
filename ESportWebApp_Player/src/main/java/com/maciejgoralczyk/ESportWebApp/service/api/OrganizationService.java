package com.maciejgoralczyk.ESportWebApp.service.api;

import com.maciejgoralczyk.ESportWebApp.model.Organization;

import java.util.List;
import java.util.UUID;

public interface OrganizationService {
    Organization find(UUID id);
    Organization create(Organization organization);
    void delete(UUID id);
}
