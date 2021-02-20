import { Component, OnInit, ViewChild, Input } from '@angular/core';
import 'rxjs/add/observable/fromEvent';
import { Router } from '@angular/router';
import { SimuladorService } from '../../services/simulador.service';
import { RegistroSimuladorComponent } from './registro-simulador.component';



@Component( {
  selector: 'app-simulador',
  templateUrl: './simulador.component.html'
})
export class SimuladorComponent implements OnInit {
  @ViewChild('registro') childOne: RegistroSimuladorComponent;

  itemList: any[] = [];

  p = 1;
  public largeModal;


  constructor(private services: SimuladorService, private router: Router) {
    console.log('Ingreso de la peticion de la lista de Simuladores');
    let simulador: any;
    simulador = {
      codigo: ''
    };
      this.services.getSimulador(simulador)
        .subscribe( data => {
          this.itemList = data;
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
      this.childOne.itemRes = this.itemList[0];
      this.childOne.isEdit = false;
  }

  ver(item: any): void {
    console.log('Datos a editar' + JSON.stringify(item));
    this.childOne.itemRes = item;
    this.childOne.isEdit = true;
    this.childOne.onEdit();
  }

  nuevo() {
    this.childOne.isEdit = false;
    this.childOne.onNew();
  }


  buscar(id: string) {
    let simulador: any;
    simulador = {
      codigo: id
    };
        this.services.getSimuladorById(simulador)
          .subscribe( data => {
            this.itemList = data;
          });
    }
    eliminar(id: string) {
       let res: any;
      let simulador: any;
      simulador = {
        codigo: id
      };
          this.services.deleteSimulador(simulador)
            .subscribe( data => {
                res = data;
            });
    }
    goTo( id: any ) {
      this.router.navigate(['/ilusion', id]);
    }
}
