import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import { Router } from '@angular/router';
import { SimuladorService } from '../../services/simulador.service';
@Component({
  selector: 'app-registro-simulador',
  templateUrl: './registro-simulador.component.html'
})
export class RegistroSimuladorComponent implements OnInit {
  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public simulador: Object = {
    codigo: '',
    nombre: '',
    ubicacion: ''
  };

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - Message successfully changed.`);
  }
  ngOnInit() {
    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(3000)
    ).subscribe(() => {
      this.successMessage = null,
      this.router.navigate(['./dashboard']);
    });
  }

  constructor(private services: SimuladorService, private router: Router, ) { }

  onNew() {
    this.simulador = {
      codigo: '',
      nombre: '',
      ubicacion: ''
    };
  }

  onEdit() {
    this.simulador = {
      codigo: this.itemRes.codigo,
      nombre: this.itemRes.nombre,
      ubicacion: this.itemRes.ubicacion
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.simulador));
      this.update(this.simulador);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.simulador));
      this.add(this.simulador);
    }
  }

  update(item: any) {
    console.log('simulador: a editar' + JSON.stringify(item));
    this.services.updateSimulador(item)
      .subscribe(data => {
        if (data['success']) {
          this.alert = {
            msg: 'Operación Correcta',
            type: 'success'
          };
        } else {
          const message = data['message'];
          this.alert = {
            msg: message['message'],
            type: 'danger'
          };
        }
      }
      );
  }

  add(item: any) {
    console.log('simulador nuevo: ' + JSON.stringify(item));
    this.services.addSimulador(item)
      .subscribe(data => {
        if (data['success']) {
          this.alert = {
            msg: 'Operación Correcta',
            type: 'success'
          };
        } else {
          const message = data['message'];
          this.alert = {
            msg: message['message'],
            type: 'danger'
          };
        }
      }
      );
  }

}
