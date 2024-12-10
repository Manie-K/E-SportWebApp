import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PutPlayerDto} from '../models/put-player-dto.model';
import {GetPlayerDto} from '../models/get-player-dto.model';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private http: HttpClient) { }


  getPlayer(id: string): Observable<GetPlayerDto> {
    const url = `/api/players/${id}`;
    console.log('Getting player', url);
    return this.http.get<GetPlayerDto>(url);
  }
  deletePlayer(id: String): Observable<void> {
    const url = `/api/players/${id}`;
    console.log('Deleting player', url);
    return this.http.delete<void>(url);
  }

  addPlayer(playerDto: PutPlayerDto): void {
    console.log('Adding player', playerDto);
    this.http.post('/api/players', playerDto).subscribe({
      next: (response) => {
        console.log('Player added successfully', response);
      },
      error: (err) => {
        console.error('Error adding player', err);
      }
    });
  }

  updatePlayer(playerId: string, playerDto: PutPlayerDto):Observable<void>{
    const url = `/api/players/${playerId}`;
    return this.http.put<void>(url, playerDto);
  }
}
