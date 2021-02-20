import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HuellaService {
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

  capture(finger: any) {
    return this.getQuery('finger/capture', finger)
                  .pipe( map(data => data));
  }
  validate(finger: any) {
    return this.getQuery('finger/validate', finger)
                  .pipe( map(data => data));
  }
  getFingerStatus(finger: any) {
    return this.getQuery( 'finger/getFingerStatus', finger )
                  .pipe( map(data => data));
  }
  update(finger: any) {
    return this.getQuery( 'finger/update', finger )
                  .pipe( map(data => data));
  }
}
