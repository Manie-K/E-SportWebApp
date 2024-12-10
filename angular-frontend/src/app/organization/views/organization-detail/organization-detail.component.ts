import {Component, OnInit} from '@angular/core';
import {PutOrganizationDto} from '../../models/put-organization.dto';
import {OrganizationService} from '../../services/organization.service';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {Organization} from '../../models/organization.model';
import {NgForOf} from '@angular/common';
import {PlayerService} from '../../../player/services/player.service';

@Component({
  selector: 'app-organization-detail',
  imports: [
    NgForOf,
    RouterLink
  ],
  templateUrl: './organization-detail.component.html',
  styleUrl: './organization-detail.component.css'
})
export class OrganizationDetailComponent implements OnInit{
  uuid: string | undefined;
  org: Organization | undefined;

  constructor(private organizationService: OrganizationService, private playerService: PlayerService,
              private route: ActivatedRoute) {
  }


  ngOnInit(): void {
    this.loadOrganization();
  }
  loadOrganization(): void {
    this.route.params.subscribe(params => {
      this.uuid = params['orgId'];

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

  deletePlayer(uuid: string)
  {
    this.playerService.deletePlayer(uuid).subscribe({
      next: (response) => {
        this.loadOrganization();
      },
      error: (err) => {
        console.error('Error deleting player', err);
      }
    });
  }
}
