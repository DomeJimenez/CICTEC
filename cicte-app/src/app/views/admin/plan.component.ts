import { Component, OnInit, ViewChild, Input } from '@angular/core';
import 'rxjs/add/observable/fromEvent';
import { SimuladorService } from '../../services/simulador.service';
import { RegistroPlanComponent } from './registro-plan.component';
import { PlanService } from '../../services/plan.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html'
})
export class PlanComponent implements OnInit {

  @ViewChild('registro') childOne: RegistroPlanComponent;

  itemList: any[] = [];
  private sub: any;

  param: {
    simulador: string,
    ilusion: string
  };
  ilusion: string;

  p = 1;
  public largeModal;


  constructor(private services: PlanService, private route: ActivatedRoute) {
    console.log('Ingreso de la peticion de la lista de Simuladores');
    let request: any;
    request = {
      codigo: ''
    };
      this.services.getPlan(request)
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
      this.param = {
        simulador: this.route.snapshot.params.simulador,
        ilusion: this.route.snapshot.params.ilusion
      };
      if ( this.param.ilusion !== '0' ) {
        console.log('Paramentro diferente de 0');
        let request: any;
        request = {
          ilusion:  {
            codigo: this.param.ilusion
          }
        };
        this.services.getPlanById(request)
          .subscribe( data => {
            this.itemList = data;
          });
      }
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
        this.services.getPlanByName(simulador)
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
          this.services.deletePlan(simulador)
            .subscribe( data => {
                res = data;
            });
    }

}
