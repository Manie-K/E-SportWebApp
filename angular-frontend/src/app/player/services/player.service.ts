import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }

  deletePlayer(id: String): Observable<void> {
    const url = `/api/players/${id}`;
    console.log('Deleting player', url);
    return this.http.delete<void>(url);
  }

}
