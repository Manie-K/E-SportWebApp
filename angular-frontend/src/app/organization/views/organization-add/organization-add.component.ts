import { Component } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {NgIf} from '@angular/common';
import {OrganizationService} from '../../services/organization.service';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-organization-add',
  imports: [
    ReactiveFormsModule,
    NgIf,
    RouterLink
  ],
  templateUrl: './organization-add.component.html',
  styleUrl: './organization-add.component.css'
})
export class OrganizationAddComponent {
  organizationForm: FormGroup;

  constructor(private fb: FormBuilder, private organizationService: OrganizationService) {
    this.organizationForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      foundationYear: [null, [Validators.required,
        Validators.min(1900), Validators.max(new Date().getFullYear())]]
    });
  }

  onSubmit(): void {
    if (this.organizationForm.valid) {
      console.log('Organization form sent', this.organizationForm.value);
      this.organizationService.addOrganization(this.organizationForm.value);
    } else {
      console.log('Form is invalid');
    }
  }
}
