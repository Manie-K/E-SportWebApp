import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {Organizations} from '../models/organizations.model';
import {Organization} from '../models/organization.model';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  constructor(private http: HttpClient) { }

  getOrganizations(): Observable<Organizations> {
    return this.http.get<Organizations>('/api/organizations');
  }

  deleteOrganization(id: number): void{
    const url = `/api/organizations/${id}`;
    this.http.delete(url).subscribe({
      next: (response) => {
        console.log('Organization deleted successfully', response);
      },
      error: (err) => {
        console.error('Error deleting organization', err);
      }
    });
  }
}
