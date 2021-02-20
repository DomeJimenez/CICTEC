import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { ModalModule } from 'ngx-bootstrap';
import { AlertsModule } from 'angular-alert-module';

import { AdminRoutingModule } from './admin-routing.module';
import { PlanComponent } from './plan.component';
import { SimuladorComponent } from './simulador.component';
import { RegistroSimuladorComponent } from './registro-simulador.component';
import { IlusionComponent } from './ilusion.component';
import { InstructorComponent } from './instructor.component';
import { RegistroInstructorComponent } from './registro-instructor.component';


import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { RegistroPlanComponent } from './registro-plan.component';
import { RegistroIlusionComponent } from './registro-ilusion.component';
import { RegistroHuellaComponent } from './registro-huella.component';
import { ActualizaHuellaComponent } from './actualiza-huella.component';
import { SelecionarHuellaComponent } from './selecionar-huella.component';
import { ActualizaHuellaPilotoComponent } from './actualiza-huella-piloto.component';


@NgModule({
  imports: [ AdminRoutingModule, CommonModule,
    FormsModule, NgxPaginationModule,
    NgbModule,
    ModalModule.forRoot(), AlertsModule.forRoot() ],
  declarations: [
    PlanComponent,
    SimuladorComponent,
    RegistroSimuladorComponent,
    IlusionComponent,
    InstructorComponent,
    RegistroInstructorComponent,
    RegistroPlanComponent,
    RegistroIlusionComponent,
    RegistroHuellaComponent,
    ActualizaHuellaComponent,
    SelecionarHuellaComponent,
    ActualizaHuellaPilotoComponent
  ],
  exports: [
    ModalModule
  ]
})
export class AdminModule { }
