import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class IlusionService {
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

  getIlusion(ilusion: any) {
    return this.getQuery('ilusion/getIlusion', ilusion)
                  .pipe( map(data => data['ilusion']));
  }
  getIlusionById(ilusion: any) {
    return this.getQuery('ilusion/getIlusionById', ilusion)
                  .pipe( map(data => data['ilusion']));
  }
  getIlusionByName(ilusion: any) {
    return this.getQuery('ilusion/getIlusionByName', ilusion)
                  .pipe( map(data => data['ilusion']));
  }
  addIlusion(ilusion: any) {
    return this.getQuery( 'ilusion/addIlusion', ilusion )
                  .pipe( map(data => data));
  }
  updateIlusion(ilusion: any) {
    return this.getQuery( 'ilusion/updateIlusion', ilusion )
                  .pipe( map(data => data));
  }
  deleteIlusion(ilusion: any) {
    return this.getQuery('ilusion/deleteIlusion', ilusion)
                  .pipe( map(data => data));
  }
}
