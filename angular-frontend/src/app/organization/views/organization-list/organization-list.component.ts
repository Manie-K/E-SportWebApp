import {Component, OnInit} from '@angular/core';
import {OrganizationService} from '../../services/organization.service';
import {Organizations} from '../../models/organizations.model';
import {Organization} from '../../models/organization.model';
import {NgForOf, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-organization-list',
  templateUrl: './organization-list.component.html',
  imports: [
    NgIf,
    NgForOf,
    RouterLink
  ],
  styleUrl: './organization-list.component.css'
})
export class OrganizationListComponent implements OnInit {
  orgs: Organizations = {organizations: []};
  constructor(private organizationService: OrganizationService) {
  }

  ngOnInit(): void {
    this.loadOrganizations();
  }

  loadOrganizations(): void {
    this.organizationService.getOrganizations().subscribe(orgs => this.orgs = orgs);
  }

  deleteOrganization(org: Organization){
    const id: number = org.id;
    this.organizationService.deleteOrganization(id).subscribe({
      next: () => {
        this.loadOrganizations();
      },
      error: (err) => console.error('Error while deleting organization:', err),
    });
  }
}
