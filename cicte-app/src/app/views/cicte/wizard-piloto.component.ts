import { Piloto } from './../../models/piloto';
import { PilotoService } from './../../services/piloto.service';
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { AlertsService } from 'angular-alert-module';
import 'rxjs/add/observable/fromEvent';
import { Router } from '@angular/router';
import { HuellaService } from '../../services/huella.service';
import { ValidaHuellaComponent } from './valida-huella.component';

@Component({
  selector: 'app-wizard-piloto',
  templateUrl: './wizard-piloto.component.html',
})
export class WizardPilotoComponent implements OnInit {

  @ViewChild('registro') childOne: ValidaHuellaComponent;

  @Output() outValidate = new EventEmitter();


  pilotos_Consulta: Piloto[] = [];
  pilotoRequest: Piloto;
  p = 1;
  public largeModal;
  public fingerV;

  constructor( private services: PilotoService, private huella: HuellaService) {
    console.log('Ingreso de la peticion de la lista de pilotos');
      this.services.getPilotos(this.pilotoRequest)
        .subscribe( data => {
          this.pilotos_Consulta = data;
        });
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
       this.childOne.itemRes = this.pilotos_Consulta[0];
  }

  mostrarInfo(event) {
    console.log('Datos Padre: ' + event.isValFinger);
    this.fingerV = event.isValFinger;
  }

  pasarInfo(event) {
    console.log('Desde el hijo :' + event);
    this.outValidate.emit({isValFinger: this.fingerV});
  }

  ver(item: any): void {
     this.childOne.itemRes = item;
     this.childOne.fingerStatus(item);
  }

  buscar(id: string) {
  let piloto: any;
  piloto = {
    id_mil: id
  };
  console.log('ID del Piloto: ' + piloto.id_mil );
      this.services.getPilotosById(piloto)
        .subscribe( data => {
          this.pilotos_Consulta = data;
        });
  }

  eliminar(id: string) {
    let piloto: any;
    piloto = {
      id_mil: id
    };
    console.log('ID del Piloto: ' + piloto.id_mil );
        this.services.deletePiloto(piloto)
          .subscribe( data => data
          );
    }

}
