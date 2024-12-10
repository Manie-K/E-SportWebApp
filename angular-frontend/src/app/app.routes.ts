import {Routes} from '@angular/router';
import {OrganizationListComponent} from './organization/views/organization-list/organization-list.component';
import {OrganizationAddComponent} from './organization/views/organization-add/organization-add.component';
import {OrganizationEditComponent} from './organization/views/organization-edit/organization-edit.component';
import {OrganizationDetailComponent} from './organization/views/organization-detail/organization-detail.component';

export const routes: Routes = [
  {path: '', redirectTo: '/organizations', pathMatch: 'full' },
  {path: 'organizations', component: OrganizationListComponent},
  {path: 'organizations/add', component: OrganizationAddComponent},
  {path: 'organizations/:id/edit', component: OrganizationEditComponent},
  {path: 'organizations/:id', component: OrganizationDetailComponent}
]
