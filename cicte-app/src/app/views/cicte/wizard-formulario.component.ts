import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormularioService } from '../../services/formulario.service';

@Component({
  selector: 'app-wizard-formulario',
  templateUrl: './wizard-formulario.component.html'
})
export class WizardFormularioComponent {

  itemList: any[] = [];

  formulario: any[] = [];

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


  ver(item: any): void {
    console.log('Datos a editar' + JSON.stringify(item));
    this.formulario = item;
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
