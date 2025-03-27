import {Component, OnInit} from '@angular/core';
import {PutOrganizationDto} from '../../../organization/models/put-organization.dto';
import {OrganizationService} from '../../../organization/services/organization.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {PutPlayerDto} from '../../models/put-player-dto.model';
import {PlayerService} from '../../services/player.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-player-edit',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './player-edit.component.html',
  styleUrl: './player-edit.component.css'
})
export class PlayerEditComponent implements OnInit {
  playerId: string | undefined;
  organizationId: string | undefined;
  player: PutPlayerDto | undefined;

  constructor(private playerService: PlayerService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.organizationId = params['orgId'];
      this.playerId = params['playerId'];

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

  onSubmit() {
    this.playerService.updatePlayer(this.playerId!, this.player!).subscribe({
      next: (response) => {
        console.log('Player updated', response);
        this.router.navigate(['/organizations/{organizationId}']);
      },
      error: (err) => {
        console.error('Error updating player', err);
      }
    });
  }
}
