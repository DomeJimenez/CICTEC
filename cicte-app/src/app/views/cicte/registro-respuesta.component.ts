import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { FormularioService } from '../../services/formulario.service';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Component({
  selector: 'app-registro-respuesta',
  templateUrl: './registro-respuesta.component.html'
})
export class RegistroRespuestaComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public respuesta: Object = {
      codigo: '',
      preguntaCodigo: '',
      tipo: '',
      tipoRespuesta: '',
      estado: ''
  };

  param: {
    formulario: string,
    pregunta: string
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

  constructor(private services: FormularioService, private router: Router, private route: ActivatedRoute) { }

  onNew() {
    this.param = {
      formulario: this.route.snapshot.params.formulario,
      pregunta: this.route.snapshot.params.pregunta
    };
    this.respuesta = {
      codigo: '',
      preguntaCodigo: this.param.pregunta,
      tipo: '',
      tipoRespuesta: '',
      estado: ''
    };
  }

  onEdit() {
    this.respuesta = {
      codigo: this.itemRes.codigo,
      preguntaCodigo: this.itemRes.pregunta,
      tipo: this.itemRes.tipo,
      tipoRespuesta: this.itemRes.tipoRespuesta,
      estado: this.itemRes.estado
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.respuesta));
      this.update(this.respuesta);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.respuesta));
      this.add(this.respuesta);
    }
  }

  update(item: any) {
    console.log('simulador: a editar' + JSON.stringify(item));
    this.services.updateRespuesta(item)
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
    this.services.addRespuesta(item)
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
