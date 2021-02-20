
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class SimuladorService {
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

  getSimulador(simulador: any) {
    return this.getQuery('simulador/getSimulador', simulador)
                  .pipe( map(data => data['simulador']));
  }
  getSimuladorById(simulador: any) {
    return this.getQuery('simulador/getSimuladorById', simulador)
                  .pipe( map(data => data['simulador']));
  }
  addSimulador(simulador: any) {
    return this.getQuery( 'simulador/addSimulador', simulador )
                  .pipe( map(data => data));
  }
  updateSimulador(simulador: any) {
    return this.getQuery( 'simulador/updateSimulador', simulador )
                  .pipe( map(data => data));
  }
  deleteSimulador(simulador: any) {
    return this.getQuery('simulador/deleteSimulador', simulador)
                  .pipe( map(data => data));
  }
}
