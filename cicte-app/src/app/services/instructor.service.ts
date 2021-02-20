import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class InstructorService {
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

  getInstructor(instructor: any) {
    return this.getQuery('instructor/getInstructor', instructor)
                  .pipe( map(data => data['instructor']));
  }
  getInstructorById(instructor: any) {
    return this.getQuery('instructor/getInstructorById', instructor)
                  .pipe( map(data => data['instructor']));
  }
  addInstructor(instructor: any) {
    return this.getQuery( 'instructor/addInstructor', instructor )
                  .pipe( map(data => data));
  }
  updateInstructor(instructor: any) {
    return this.getQuery( 'instructor/updateInstructor', instructor )
                  .pipe( map(data => data));
  }
  deleteInstructor(instructor: any) {
    return this.getQuery('instructor/deleteInstructor', instructor)
                  .pipe( map(data => data));
  }
}
