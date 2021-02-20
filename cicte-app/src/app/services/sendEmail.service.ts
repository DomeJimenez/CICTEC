import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class SendEmailService {
  type: any;
  constructor( private http: HttpClient) { }
  getResponseEmail(_body: any): Observable<any> {
    // this.http.post('/send.php',_body).subscribe(data =>{
    //   console.log(data);
    //   this.type = JSON.stringify(data);
    // });
    return this.http.post('/send', _body);
  }
}
