import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
@Injectable({
  providedIn: 'root'
})

export class ApiService {
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
  getItems(post: string, objeto: any) {
    return this.getQuery(post, objeto)
                  .pipe( map(data => data));
  }

  getCatalogo(tabla: any) {
    return this.getQuery('utils/getCatalogo', tabla)
                  .pipe( map(data => data['catalogo']));
  }

  getObjService(item: any, post: string, elemento: string) {
    return this.getQuery(post, item)
                  .pipe( map(data => data[elemento]));
  }
}
