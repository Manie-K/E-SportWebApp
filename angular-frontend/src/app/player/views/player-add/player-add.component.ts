import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {OrganizationService} from '../../../organization/services/organization.service';
import {PlayerService} from '../../services/player.service';
import {NgIf} from '@angular/common';
import {ActivatedRoute, RouterLink} from '@angular/router';

@Component({
  selector: 'app-player-add',
  imports: [
    NgIf,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './player-add.component.html',
  styleUrl: './player-add.component.css'
})
export class PlayerAddComponent {
  playerForm: FormGroup | undefined;
  orgId: string | undefined;
  constructor(private fb: FormBuilder, private playerService: PlayerService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => {
      this.orgId = params['orgId'];
      this.playerForm = this.fb.group({
          name: ['', [Validators.required, Validators.minLength(2)]],
          age: [null, [Validators.required, Validators.min(10), Validators.max(99)]],
          organizationId: [this.orgId, [Validators.required]]
      });
    });
  }

  onSubmit(): void {
    if (this.playerForm!.valid) {
      this.playerService.addPlayer(this.playerForm!.value);
    } else {
      console.log('Form is invalid');
    }
  }
}
