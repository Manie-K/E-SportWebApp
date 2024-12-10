import {Component, OnInit} from '@angular/core';
import {PutOrganizationDtoModel} from '../../models/put-organization-dto.model';
import {OrganizationService} from '../../services/organization.service';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-organization-edit',
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './organization-edit.component.html',
  styleUrl: './organization-edit.component.css'
})
export class OrganizationEditComponent implements OnInit {
  uuid: string | undefined;
  org: PutOrganizationDtoModel | undefined;

  constructor(private organizationService: OrganizationService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.uuid = params['id'];

      this.organizationService.getOrganization(this.uuid!).subscribe({
        next: (response) => {
          this.org = response;
        },
        error: (err) => {
          console.error('Error getting organization', err);
        }
      });
    });
  }

  onSubmit() {
    this.organizationService.updateOrganization(this.uuid!, this.org!).subscribe({
      next: (response) => {
        console.log('Organization updated', response);
      },
      error: (err) => {
        console.error('Error updating organization', err);
      }
    });
  }
}
