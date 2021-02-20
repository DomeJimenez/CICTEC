import { Component, OnInit, ViewChild, Input } from '@angular/core';
import 'rxjs/add/observable/fromEvent';
import { ActivatedRoute } from '@angular/router';
import { RegistroPreguntaComponent } from './registro-pregunta.component';
import { FormularioService } from '../../services/formulario.service';

@Component({
  selector: 'app-pregunta',
  templateUrl: './pregunta.component.html'
})
export class PreguntaComponent implements OnInit {

  @ViewChild('registro') childOne: RegistroPreguntaComponent;

  itemList: any[] = [];

  public formulario: Object = {
    codigo: ''
  };

  param: {
    formulario: string
  };

  private sub: any;

  p = 1;
  public largeModal;


  constructor(private services: FormularioService, private route: ActivatedRoute) {
    console.log('Ingreso de la peticion de la lista de Simuladores');
    let request: any;
    request = {
      codigo: ''
    };
      this.services.getPregunta(request)
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
        this.formulario = {
          codigo: params['formulario']
        };
     });
     this.param = {
      formulario: this.route.snapshot.params.formulario
    };
    if ( this.param.formulario !== '0' ) {
      console.log('Paramentro diferente de 0');
      let request: any;
      request = {
        formulario:  {
          codigo: this.param.formulario
        }
      };
      this.services.getPreguntaById(request)
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
    let formulario: any;
    formulario = {
      codigo: id
    };
        this.services.getPreguntaById(formulario)
          .subscribe( data => {
            this.itemList = data;
          });
    }
    eliminar(id: string) {
       let res: any;
      let formulario: any;
      formulario = {
        codigo: id
      };
          this.services.deletePregunta(formulario)
            .subscribe( data => {
                res = data;
            });
    }

}
