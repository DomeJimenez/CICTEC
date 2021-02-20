import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { Observable } from 'rxjs/Observable';
import { map, retry } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})

export class FormularioService {
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

  getFormulario(formulario: any) {
    return this.getQuery('formulario/getFormularios', formulario)
                  .pipe( map(data => data['formulario']));
  }
  getFormularioMatriz(formulario: any): Observable<any> {
    return this.getQuery('formulario/getFormularioMatriz', formulario)
                  .pipe( map(data => data['formulario']));
  }
  getFormularioById(formulario: any) {
    return this.getQuery('formulario/getFormularioById', formulario)
                  .pipe( map(data => data['formulario']));
  }

  getPregunta(formulario: any) {
    return this.getQuery('pregunta/getPregunta', formulario)
                  .pipe( map(data => data['pregunta']));
  }
  getPreguntaById(formulario: any) {
    return this.getQuery('pregunta/getPreguntaById', formulario)
                  .pipe( map(data => data['pregunta']));
  }
  getRespuesta(formulario: any) {
    return this.getQuery('respuesta/getRespuesta', formulario)
                  .pipe( map(data => data['respuesta']));
  }
  getRespuestaById(formulario: any) {
    return this.getQuery('respuesta/getRespuestaById', formulario)
                  .pipe( map(data => data['respuesta']));
  }
  getRespuestaByName(formulario: any) {
    return this.getQuery('respuesta/getRespuestaByName', formulario)
                  .pipe( map(data => data['respuesta']));
  }
  addFormulario(formulario: any) {
    return this.getQuery( 'formulario/addFormulario', formulario )
                  .pipe( map(data => data));
  }
  addPregunta(formulario: any) {
    return this.getQuery( 'formulario/addPregunta', formulario )
                  .pipe( map(data => data));
  }
  addRespuesta(formulario: any) {
    return this.getQuery( 'formulario/addRespuesta', formulario )
                  .pipe( map(data => data));
  }
  updateFormulario(formulario: any) {
    return this.getQuery( 'formulario/updateFormulario', formulario )
                  .pipe( map(data => data));
  }
  updatePregunta(formulario: any) {
    return this.getQuery( 'formulario/updatePregunta', formulario )
                  .pipe( map(data => data));
  }
  updateRespuesta(formulario: any) {
    return this.getQuery( 'formulario/updateRespuestas', formulario )
                  .pipe( map(data => data));
  }
  deletePregunta(formulario: any) {
    return this.getQuery('formulario/deletePregunta', formulario)
                  .pipe( map(data => data));
  }
  deleteRespuesta(formulario: any) {
    return this.getQuery('formulario/deleteRespuesta', formulario)
                  .pipe( map(data => data));
  }
  deleteFormulario(formulario: any) {
    return this.getQuery('formulario/deleteFormulario', formulario)
                  .pipe( map(data => data));
  }
}
