import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class LoginService {

  API_URL = environment.API_URL;
  constructor(public http: HttpClient, public router: Router) {
  }
  getQuery(query: string, request: any) {
    const url = `${this.API_URL}${query}`;
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    const body = JSON.stringify(request);
    console.log('Paramentros enviados: ' + body);
    return this.http.post(url, body, { headers: headers });
  }

  login(item: any) {
    return this.getQuery('security/login', item).pipe(map(data => data));
  }

  logout() {
    this.removeStorage();
    this.router.navigate(['./login']);
  }

  removeStorage() {
    localStorage.removeItem('ROL');
    localStorage.removeItem('USER');
    localStorage.removeItem('INSTRUCTOR');
    localStorage.removeItem('ACCESS');
    localStorage.removeItem('DATE');
    localStorage.clear();
  }

}
