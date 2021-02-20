import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class PlanService {
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

  getPlan(plan: any) {
    return this.getQuery('plan/getPlan', plan)
                  .pipe( map(data => data['plan']));
  }
  getPlanById(plan: any) {
    return this.getQuery('plan/getPlanById', plan)
                  .pipe( map(data => data['plan']));
  }
  getPlanByName(plan: any) {
    return this.getQuery('plan/getPlanByName', plan)
                  .pipe( map(data => data['plan']));
  }
  addPlan(plan: any) {
    return this.getQuery( 'plan/addPlan', plan )
                  .pipe( map(data => data));
  }
  updatePlan(plan: any) {
    return this.getQuery( 'plan/updatePlan', plan )
                  .pipe( map(data => data));
  }
  deletePlan(plan: any) {
    return this.getQuery('plan/deletePlan', plan)
                  .pipe( map(data => data));
  }
}
