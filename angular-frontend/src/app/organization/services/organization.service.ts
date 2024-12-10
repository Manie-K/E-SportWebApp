import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {Organizations} from '../models/organizations.model';
import {Organization} from '../models/organization.model';
import {PutOrganizationDto} from '../models/put-organization.dto';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  constructor(private http: HttpClient) { }

  getOrganizations(): Observable<Organizations> {
    return this.http.get<Organizations>('/api/organizations');
  }

  getOrganization(id: string): Observable<Organization> {
    const url = `/api/organizations/${id}`;
    return this.http.get<Organization>(url);
  }

  addOrganization(organizationDto: PutOrganizationDto): void {
    console.log(organizationDto);
    this.http.post('/api/organizations', organizationDto).subscribe({
      next: (response) => {
        console.log('Organization added successfully', response);
      },
      error: (err) => {
        console.error('Error adding organization', err);
      }
    });
  }

  updateOrganization(id: string, organizationDto: PutOrganizationDto): Observable<void> {
    const url = `/api/organizations/${id}`;
    return this.http.put<void>(url, organizationDto);
  }

  deleteOrganization(id: number): Observable<void>{
    const url = `/api/organizations/${id}`;
    return this.http.delete<void>(url);
  }
}
