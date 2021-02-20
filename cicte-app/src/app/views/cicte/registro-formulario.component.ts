import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import { Router } from '@angular/router';
import { FormularioService } from '../../services/formulario.service';

@Component({
  selector: 'app-registro-formulario',
  templateUrl: './registro-formulario.component.html'
})
export class RegistroFormularioComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public formulario: Object = {
    codigo: '',
    id: '',
    nombre: '',
    fecha_crea: '',
    fecha_mod: '',
    detalle: '',
    estado: ''
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

  constructor(private services: FormularioService, private router: Router, ) { }

  onNew() {
    this.formulario = {
      codigo:     '',
      id:         '',
      nombre:     '',
      fecha_crea: '',
      fecha_mod:  '',
      detalle:    '',
      estado:     ''
    };
  }

  onEdit() {
    this.formulario = {
      codigo:      this.itemRes.codigo,
      id:          this.itemRes.id,
      nombre:      this.itemRes.nombre,
      fecha_crea:  this.itemRes.fecha_crea,
      fecha_mod:   this.itemRes.fecha_mod,
      detalle:     this.itemRes.detalle,
      estado:      this.itemRes.estdo
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.formulario));
      this.update(this.formulario);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.formulario));
      this.add(this.formulario);
    }
  }

  update(item: any) {
    console.log('simulador: a editar' + JSON.stringify(item));
    this.services.updateFormulario(item)
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
    this.services.addFormulario(item)
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
