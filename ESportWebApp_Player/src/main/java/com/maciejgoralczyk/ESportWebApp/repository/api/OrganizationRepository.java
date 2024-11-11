package com.maciejgoralczyk.ESportWebApp.repository.api;

import com.maciejgoralczyk.ESportWebApp.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID>
{
    Organization findOrganizationById(UUID id);
}
