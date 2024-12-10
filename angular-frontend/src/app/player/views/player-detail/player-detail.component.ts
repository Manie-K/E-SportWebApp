import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {GetPlayerDto} from '../../models/get-player-dto.model';
import {PlayerService} from '../../services/player.service';
import {OrganizationService} from '../../../organization/services/organization.service';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-player-detail',
  imports: [
    RouterLink
  ],
  templateUrl: './player-detail.component.html',
  styleUrl: './player-detail.component.css'
})
export class PlayerDetailComponent implements OnInit {
  organizationId: string | undefined;
  playerId: string | undefined;
  player: GetPlayerDto | undefined;
  organizationName: string | undefined;

  constructor(private playerService: PlayerService, private organizationService: OrganizationService,
              private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.organizationId = params['orgId'];
      this.playerId = params['playerId'];

      this.organizationService.getOrganization(this.organizationId!).subscribe({
        next: (organization) => {
          this.organizationName = organization.name;
        },
        error: (err) => {
          console.error('Error fetching organization', err);
        },
      });

      this.playerService.getPlayer(this.playerId!).subscribe({
        next: (response) => {
          this.player = response;
        },
        error: (err) => {
          console.error('Error getting player', err);
        }
      });
    });
  }
}
