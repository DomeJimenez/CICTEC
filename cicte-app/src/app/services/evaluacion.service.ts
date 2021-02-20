import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class EvaluacionService {
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

  getEvaluacion(evaluacion: any) {
    return this.getQuery('evaluacion/getEvaluacion', evaluacion)
                  .pipe( map(data => data['evaluacion']));
  }
  getEvaluacionById(evaluacion: any) {
    return this.getQuery('evaluacion/getEvaluacionById', evaluacion)
                  .pipe( map(data => data['evaluacion']));
  }
  addEvaluacion(evaluacion: any) {
    return this.getQuery( 'evaluacion/addEvaluacion', evaluacion )
                  .pipe( map(data => data));
  }
  updateEvaluacion(evaluacion: any) {
    return this.getQuery( 'evaluacion/updateEvaluacion', evaluacion )
                  .pipe( map(data => data));
  }
  deleteEvaluacion(evaluacion: any) {
    return this.getQuery('evaluacion/deleteEvaluacion', evaluacion)
                  .pipe( map(data => data));
  }
}
