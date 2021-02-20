import { Piloto } from './../../models/piloto';
import { PilotoService } from './../../services/piloto.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { AlertsService } from 'angular-alert-module';
import { RegistroPilotoComponent } from './registro-piloto.component';

@Component( {
  selector: 'app-piloto',
  templateUrl: './piloto.component.html'
})

export class PilotoComponent implements OnInit {

  @ViewChild('registro') childOne: RegistroPilotoComponent;

  itemList: any[] = [];

  p = 1;
  public largeModal;

  constructor( private services: PilotoService,
    private alerts: AlertsService) {
    console.log('Ingreso de la peticion de la lista de Instructores');

    let itemRequest: any;
    itemRequest = {
      codigo: ''
    };
      this.services.getPilotos(itemRequest)
        .subscribe( data => {
          this.itemList = data;
          console.log('Datos :' + this.itemList);
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
    this.alerts.setDefaults('timeout', 0);
    this.alerts.setConfig('warn', 'icon', 'warning');
      this.childOne.itemRes = this.itemList[0];
      this.childOne.isEdit = false;
  }

  ver(item: any): void {
    console.log('Datos a editar' + JSON.stringify(item));
    this.alerts.setMessage('All the fields are required', 'error');
    this.childOne.itemRes = item;
    this.childOne.isEdit = true;
    this.childOne.onEdit();
  }

  nuevo() {
    this.childOne.isEdit = false;
    this.childOne.onNew();
  }

  buscar(id: string) {
    let itemRequest: any;
    itemRequest = {
      id_mil: id
    };
        this.services.getPilotosById(itemRequest)
          .subscribe( data => {
            this.itemList = data;
          });
    }
    eliminar(id: string) {
       let res: any;
      let itemRequest: any;
      itemRequest = {
        codigo: id
      };
          this.services.deletePiloto(itemRequest)
            .subscribe( data => {
                res = data;
            });
    }
}
