import { Component, OnInit, ViewChild, Input } from '@angular/core';
import 'rxjs/add/observable/fromEvent';
import { SimuladorService } from '../../services/simulador.service';
import { RegistroIlusionComponent } from './registro-ilusion.component';
import { IlusionService } from '../../services/ilusion.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-ilusion',
  templateUrl: './ilusion.component.html'
})
export class IlusionComponent implements OnInit {

  @ViewChild('registro') childOne: RegistroIlusionComponent;

  itemList: any[] = [];

  public simulador: Object = {
    codigo: ''
  };

  param: {
    simulador: string
  };

  private sub: any;

  p = 1;
  public largeModal;


  constructor(private services: IlusionService, private route: ActivatedRoute) {
    console.log('Ingreso de la peticion de la lista de Simuladores');
    let request: any;
    request = {
      codigo: ''
    };
      this.services.getIlusion(request)
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
      this.sub = this.route.params.subscribe(params => {
        this.simulador = {
          codigo: params['simulador']
        };
     });
     this.param = {
      simulador: this.route.snapshot.params.simulador
    };
    if ( this.param.simulador !== '0' ) {
      console.log('Paramentro diferente de 0');
      let request: any;
      request = {
        simulador:  {
          codigo: this.param.simulador
        }
      };
      this.services.getIlusionById(request)
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
        this.services.getIlusionByName(simulador)
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
          this.services.deleteIlusion(simulador)
            .subscribe( data => {
                res = data;
            });
    }

}
