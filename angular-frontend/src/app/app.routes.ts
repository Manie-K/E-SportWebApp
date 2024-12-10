import {Routes} from '@angular/router';
import {OrganizationListComponent} from './organization/views/organization-list/organization-list.component';
import {OrganizationAddComponent} from './organization/views/organization-add/organization-add.component';
import {OrganizationEditComponent} from './organization/views/organization-edit/organization-edit.component';
import {OrganizationDetailComponent} from './organization/views/organization-detail/organization-detail.component';
import {PlayerAddComponent} from './player/views/player-add/player-add.component';
import {PlayerEditComponent} from './player/views/player-edit/player-edit.component';

export const routes: Routes = [
  {path: '', redirectTo: '/organizations', pathMatch: 'full' },
  {path: 'organizations', component: OrganizationListComponent},
  {path: 'organizations/add', component: OrganizationAddComponent},
  {path: 'organizations/:orgId/edit', component: OrganizationEditComponent},
  {path: 'organizations/:orgId', component: OrganizationDetailComponent},
  {path: 'organizations/:orgId/players/add', component: PlayerAddComponent},
  {path: 'organizations/:orgId/players/:playerId/edit', component: PlayerEditComponent}
]
