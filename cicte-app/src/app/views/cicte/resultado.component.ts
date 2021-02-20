import { Component, OnInit, ViewChild, Input } from '@angular/core';
import 'rxjs/add/observable/fromEvent';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { EvaluacionService } from '../../services/evaluacion.service';

@Component({
  selector: 'app-resultado',
  templateUrl: './resultado.component.html'
})
export class ResultadoComponent implements OnInit {


  itemList: any[] = [];

  p = 1;
  public largeModal;


  constructor(private services: EvaluacionService, private router: Router) {
    console.log('Ingreso de la peticion de la lista de Resultados');
    let evaluacion: any;
    evaluacion = {
      codigo: ''
    };
      this.services.getEvaluacion(evaluacion)
        .subscribe( data => {
          this.itemList = JSON.parse(JSON.stringify(data));
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
  }

  buscar(id: string) {
    let evaluacion: any;
    evaluacion = {
      codigo: id
    };
        this.services.getEvaluacionById(evaluacion)
          .subscribe( data => {
            this.itemList = data;
          });
    }
    eliminar(id: string) {
      let res: any;
      let evaluacion: any;
      evaluacion = {
        codigo: id
      };
          this.services.deleteEvaluacion(evaluacion)
            .subscribe( data => {
                res = data;
            });
    }
    goTo( id: any ) {
      this.router.navigate(['/charts']);
    }

}

