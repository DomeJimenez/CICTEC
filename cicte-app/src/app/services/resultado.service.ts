import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class ResultadoService {
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

  getResultado(resultado: any) {
    return this.getQuery('resultado/getResultado', resultado)
                  .pipe( map(data => data['resultado']));
  }
  getResultadoById(resultado: any) {
    return this.getQuery('resultado/getResultadoById', resultado)
                  .pipe( map(data => data['resultado']));
  }
  addResultado(resultado: any) {
    return this.getQuery( 'resultado/addResultado', resultado )
                  .pipe( map(data => data));
  }
  updateResultado(resultado: any) {
    return this.http.post( 'resultado/updateResultado', resultado )
                  .pipe( map(data => data));
  }
  deleteResultado(resultado: any) {
    return this.getQuery('resultado/deleteResultado', resultado)
                  .pipe( map(data => data));
  }
}
