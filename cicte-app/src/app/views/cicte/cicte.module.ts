import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { ModalModule } from 'ngx-bootstrap';
import { AlertsModule } from 'angular-alert-module';
import { ArchwizardModule } from 'ng2-archwizard';

import { PilotoComponent } from './piloto.component';
import { CicteRoutingModule } from './cicte-routing.module';
import { RegistroPilotoComponent } from './registro-piloto.component';
import { EvaluacionComponent } from './evaluacion.component';
import { FormularioComponent } from './formulario.component';
import { PreguntaComponent } from './pregunta.component';
import { RespuestaComponent } from './respuesta.component';
import { ResultadoComponent } from './resultado.component';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { RegistroFormularioComponent } from './registro-formulario.component';
import { RegistroPreguntaComponent } from './registro-pregunta.component';
import { RegistroRespuestaComponent } from './registro-respuesta.component';
import { RegistroEvaluacionComponent } from './registro-evaluacion.component';
import { ReportesComponent } from './reportes.component';
import { FormWizardComponent } from './form-wizard.component';
import { WizardFormularioComponent } from './wizard-formulario.component';
import { WizardPilotoComponent } from './wizard-piloto.component';
import { ValidaHuellaComponent } from './valida-huella.component';

@NgModule({
  imports: [ CicteRoutingModule, CommonModule, FormsModule,
    NgxPaginationModule, NgbModule, ModalModule.forRoot(), AlertsModule.forRoot(),
    ArchwizardModule ],
  declarations: [
    PilotoComponent,
    RegistroPilotoComponent,
    EvaluacionComponent,
    FormularioComponent,
    PreguntaComponent,
    RespuestaComponent,
    ResultadoComponent,
    RegistroFormularioComponent,
    RegistroPreguntaComponent,
    RegistroRespuestaComponent,
    RegistroEvaluacionComponent,
    ReportesComponent,
    FormWizardComponent,
    WizardFormularioComponent,
    WizardPilotoComponent,
    ValidaHuellaComponent
  ],
  exports: [
    ModalModule
  ]
})
export class CicteModule { }
