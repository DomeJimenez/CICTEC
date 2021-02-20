import { Component, OnInit, ViewChild, OnChanges, EventEmitter, Output } from '@angular/core';
import { ValidaHuellaComponent } from './valida-huella.component';
import { WizardPilotoComponent } from './wizard-piloto.component';
import { WizardFormularioComponent } from './wizard-formulario.component';
import { EvaluacionComponent } from './evaluacion.component';
import { Observable, of, Subject } from 'rxjs';
import { Piloto } from '../../models/piloto';
import { PilotoService } from '../../services/piloto.service';
import { HuellaService } from '../../services/huella.service';
import { FormularioService } from '../../services/formulario.service';
import { PlanService } from '../../services/plan.service';
import { Resultados } from '../../models/resultados';
import { ApiService } from '../../services/api.service';
import { formatDate } from '@angular/common';
import {debounceTime} from 'rxjs/operators';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { EvaluacionService } from '../../services/evaluacion.service';
import { ResultadoService } from '../../services/resultado.service';
import { Router } from '@angular/router';
import { formattedError } from '@angular/compiler';

@Component({
  selector: 'app-form-wizard',
  templateUrl: './form-wizard.component.html'
})
export class FormWizardComponent implements OnInit {

  public piloto: any[];
  public plan: any[];
  public formulario: any[];

  public base64Files: string[] = [];
  private files: any[] = [];
  private fileReader = new FileReader();

  itemList: any[] = [];

  selectedFile: File;

  public preguntas:  any[] = [];
  public respuestas:  any[] = [];

  public isValidate: boolean;
  public today = new Date();
  public fecha = '';

  public pilotoId: string;
  public planId: number;

  resFormulario: any[] = [];

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public evaluacion: Object = {
    observacion: '',
    piloto: '',
    instructor: '',
    plan: '',
    funcion: '',
    aeronave: '',
    horas_vuelo: '',
    grafico: ''
  };

  public catalogoFuncion: any[] = [];
  public catalogoAeronave: any[] = [];

  @ViewChild('registro') childOne: ValidaHuellaComponent;

  formulario_consulta: any[];
  pilotos_Consulta: Piloto[] = [];
  pilotoRequest: Piloto;
  plan_consulta: any[];
  matriz_formulario: any[] = [];

  p = 1;
  p1 = 1;
  p2 = 1;
  public largeModal;
  public fingerV;

  constructor( private servicePiloto: PilotoService, private huella: HuellaService,
    private serviceFormulario: FormularioService, private servicePlan: PlanService,
    private utils: ApiService, private serviceEvaluacion: EvaluacionService,
    private serviceResultado: ResultadoService, private router: Router) {
    this.fecha = formatDate(this.today, 'yyyy-MM-dd hh:mm:ss', 'en-US', '+0530');
    console.log('Ingreso de la peticion de la lista de pilotos');
    let formulario: any;
    formulario = {
      codigo: ''
    };
    let plan: any;
    plan = {
      codigo: ''
    };
    this.isValidate = false;
      this.servicePiloto.getPilotos(this.pilotoRequest)
        .subscribe( data => {
          this.pilotos_Consulta = data;
        });
      this.serviceFormulario.getFormulario(this.formulario)
        .subscribe( data => {
          this.formulario_consulta = data;
        });
      this.servicePlan.getPlan(this.plan)
        .subscribe( data => {
          this.plan_consulta = data;
        });
    this.serviceFormulario.getFormularioMatriz(formulario)
        .subscribe( data =>  {
          this.matriz_formulario = JSON.parse(JSON.stringify(data));
        });

      this.resFormulario = new Array<Resultados>();
  }

  isCollapsed = false;
  iconCollapse = 'icon-arrow-up';

  collapsed(event: any): void {
    // console.log(event);
  }

  expanded(event: any): void {
    // console.log(event);
  }

  toggleCollapse(): void {
    this.isCollapsed = !this.isCollapsed;
    this.iconCollapse = this.isCollapsed ? 'icon-arrow-down' : 'icon-arrow-up';
  }

  ngOnInit() {
    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(3000)
    ).subscribe(() => {
      this.successMessage = null,
      this.router.navigate(['./dashboard']);
    });
  }

  verPiloto(item: any): void {
     this.childOne.fingerStatus(item);
     this.pilotoId = item.id_mil;
     this.piloto = item;
  }
  verFormulario(item: any): void {
    this.formulario = item;
    const matriz = JSON.parse(JSON.stringify(item));
    this.getMatriz(matriz.codigo);
    this.getCatalogoAereonave();
    this.getCatalogoFuncion();
  }

  verPlan(item: any): void {
    this.planId = item.codigo;
    this.plan = item;
  }

  getMatriz(codigoFormulario: string) {
    let formulario: any;
    formulario = {
      codigo: codigoFormulario
    };
    this.serviceFormulario.getFormularioMatriz(formulario)
        .subscribe( data =>  {
          this.matriz_formulario = JSON.parse(JSON.stringify(data));
          console.log('Matriz Formulario' + JSON.stringify(this.matriz_formulario[0].preguntaList));
          this.preguntas = JSON.parse(JSON.stringify(this.matriz_formulario[0].preguntaList));
          console.log('Matriz Preguntas' + JSON.stringify(this.preguntas));
        });
  }

  mostrarInfo(event) {
    console.log('Datos Event: ' + event.isValFinger);
    this.isValidate = event.isValFinger;
    console.log('Datos Padre: ' + JSON.stringify(this.piloto));
  }

  invalidPiloto() {
    console.log('Invalid piloto ');
    this.piloto =  [];
    this.isValidate = false;
  }

  invalidFormulario() {
    console.log('Invalid formulario');
    this.formulario =  [];
  }

  invalidPlan() {
    console.log('Invalid plan');
    this.plan =  [];
  }

  finishFunction(eva: any) {

    const evaluacion = {
      observacion: eva.observacion,
      piloto: this.pilotoId,
      instructor: localStorage.getItem('INSTRUCTOR') ,
      plan: this.planId,
      funcion: eva.funcion,
      aeronave: eva.aeronave,
      horas_vuelo: eva.horas_vuelo,
      // grafico: this.base64Files.toString()
    };


    this.addEvaluacion(evaluacion);

    console.log('Datos a enviar Resultados:  ' + JSON.stringify(this.resFormulario));

  }

  getCatalogoAereonave() {
    let tabla: any;
    tabla = {
      tabla: 'aeronave'
    };
    this.utils.getCatalogo(tabla)
      .subscribe(data => {
        this.catalogoAeronave = data;
      });
    console.log('Datos del catalogo' + JSON.stringify(this.catalogoAeronave));
  }


  getCatalogoFuncion() {
    let tabla: any;
    tabla = {
      tabla: 'funcion'
    };
    this.utils.getCatalogo(tabla)
      .subscribe(data => {
        this.catalogoFuncion = data;
      });
    console.log('Datos del catalogo' + JSON.stringify(this.catalogoFuncion));
  }

  public changeSuccessMessage() {
     this._success.next(`${new Date()} - Message successfully changed.`);
  }
  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
      this.finishFunction(this.evaluacion);
  }

  addEvaluacion(item: any) {
    console.log('Nueva Evaluacion: ' + JSON.stringify(item));
    this.serviceEvaluacion.addEvaluacion(item)
      .subscribe(data => {
        if (data['success']) {
          this.alert = {
            msg: 'Operación Correcta',
            type: 'success'
          };
          this.addRespuestas(this.formulario);
        } else {
          const message = data['message'];
          this.alert = {
            msg: message['message'],
            type: 'danger'
          };
        }
      }
      );
  }

  addRespuestas(item: any) {
    const formulario = JSON.parse(JSON.stringify(item));
    const matriz = JSON.parse(JSON.stringify(this.resFormulario));
    for ( const index of matriz) {
      const respuestas = {
        formulario: formulario.id,
        pregunta: index[0],
        respuesta: index[2]
      };
      console.log('Matriz de respuestas:  ' + JSON.stringify(respuestas) );
      this.serviceResultado.addResultado(respuestas)
      .subscribe(data => {
        if (data['success']) {
          this.alert = {
            msg: 'Evaluación Registrada',
            type: 'success'
          };
        } else {
          const message = data['message'];
          this.alert = {
            msg: message['message'],
            type: 'danger'
          };
        }
      }
      );
    }
  }

  // Imagenes
  onFileChanged(event: Event) {
    this.files = event.target['files'];
    if (event.target['files']) {
      console.log(event.target['files']);
      this.readFiles(event.target['files'], 0);
    }
  }

  private readFiles(files: any[], index: number) {
    const file = files[index];
    this.fileReader.onload = () => {
      this.base64Files.push(this.fileReader.result.toString());
      if (files[index + 1]) {
        this.readFiles(files, index + 1);
      } else {
        console.log('loaded all files');
      }
      console.log('Imagen  ---> ' + JSON.stringify(this.base64Files.toString()) );
    };
    this.fileReader.readAsDataURL(file);
  }
}
