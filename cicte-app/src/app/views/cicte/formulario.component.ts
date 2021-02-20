import { Component, OnInit, ViewChild, Input } from '@angular/core';
import 'rxjs/add/observable/fromEvent';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { RegistroFormularioComponent } from './registro-formulario.component';
import { FormularioService } from '../../services/formulario.service';
@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html'
})
export class FormularioComponent implements OnInit {

  @ViewChild('registro') childOne: RegistroFormularioComponent;

  itemList: any[] = [];

  p = 1;
  public largeModal;


  constructor(private services: FormularioService, private router: Router) {
    console.log('Ingreso de la peticion de la lista de Formularios');
    let formulario: any;
    formulario = {
      codigo: ''
    };
      this.services.getFormulario(formulario)
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
    let formulario: any;
    formulario = {
      codigo: id
    };
        this.services.getFormularioById(formulario)
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
          this.services.deleteFormulario(formulario)
            .subscribe( data => {
                res = data;
            });
    }
    goTo( id: any ) {
      this.router.navigate(['/pregunta', id]);
    }

}
