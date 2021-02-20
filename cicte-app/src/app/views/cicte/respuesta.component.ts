import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { FormularioService } from '../../services/formulario.service';
import { RegistroRespuestaComponent } from './registro-respuesta.component';

@Component( {
  selector: 'app-respuesta',
  templateUrl: './respuesta.component.html'
})
export class RespuestaComponent implements OnInit {

  @ViewChild('registro') childOne: RegistroRespuestaComponent;

  itemList: any[] = [];
  private sub: any;

  param: {
    formulario: string,
    pregunta: string
  };
  pregunta: string;

  p = 1;
  public largeModal;


  constructor(private services: FormularioService, private route: ActivatedRoute) {
    console.log('Ingreso de la peticion de la lista de Simuladores');
    let request: any;
    request = {
      codigo: ''
    };
      this.services.getRespuesta(request)
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
        formulario: this.route.snapshot.params.formulario,
        pregunta: this.route.snapshot.params.pregunta
      };
      if ( this.param.pregunta !== '0' ) {
        console.log('Paramentro diferente de 0');
        let request: any;
        request = {
          pregunta:  {
            codigo: this.param.pregunta
          }
        };
        this.services.getRespuestaById(request)
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
        this.services.getRespuestaByName(formulario)
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
          this.services.deleteRespuesta(formulario)
            .subscribe( data => {
                res = data;
            });
    }

}
