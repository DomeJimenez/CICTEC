import { Piloto } from './../models/piloto';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class PilotoService {
  API_URL = environment.API_URL;
  constructor(public http: HttpClient) {
  }
  getQuery( query: string, request: any) {
    const url = `${this.API_URL}${ query }`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const body = JSON.stringify(request);
    console.log('Paramentros enviados: ' + body );
    return this.http.post(url, body, { headers: headers });
  }

  getPilotos(piloto: Piloto) {
    return this.getQuery('pilotos/getPilotos', piloto)
                  .pipe( map(data => data['piloto']));
  }
  getPilotosById(piloto: Piloto) {
    return this.getQuery('pilotos/getPilotoById', piloto)
                  .pipe( map(data => data['piloto']));
  }
  addPiloto(piloto: Piloto) {
    return this.http.post( 'pilotos/addPilotos', piloto )
                  .pipe( map(data => data));
  }

  updatePiloto(piloto: Piloto) {
    return this.http.post( 'pilotos/updatePilotos', piloto )
                    .pipe( map(data => data));
  }

  deletePiloto(piloto: Piloto) {
    return this.getQuery('pilotos/deletePiloto', piloto)
                  .pipe( map(data => data));
  }
}
