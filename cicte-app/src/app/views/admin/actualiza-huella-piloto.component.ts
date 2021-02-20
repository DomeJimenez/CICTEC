import { Piloto } from './../../models/piloto';
import { PilotoService } from './../../services/piloto.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { AlertsService } from 'angular-alert-module';
import 'rxjs/add/observable/fromEvent';
import { Router } from '@angular/router';
import { HuellaService } from '../../services/huella.service';
import { ActualizaHuellaComponent } from './actualiza-huella.component';

@Component({
  selector: 'app-actualiza-huella-piloto',
  templateUrl: './actualiza-huella-piloto.component.html'
})
export class ActualizaHuellaPilotoComponent implements OnInit {

  @ViewChild('registro') childOne: ActualizaHuellaComponent;

  pilotos_Consulta: Piloto[] = [];
  pilotoRequest: Piloto;
  p = 1;
  public largeModal;

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
      this.childOne.isEdit = false;
  }

  ver(item: any): void {
    console.log('Datos a editar' + JSON.stringify(item));
    this.childOne.itemRes = item;
    this.childOne.isEdit = true;
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
