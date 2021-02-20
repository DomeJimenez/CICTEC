import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { FormularioService } from '../../services/formulario.service';

@Component({
  selector: 'app-registro-pregunta',
  templateUrl: './registro-pregunta.component.html'
})
export class RegistroPreguntaComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public pregunta: Object = {
    codigo: '',
    codigoFormulario: '',
    criterio: ''
  };

  param: {
    formulario: string
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
      formulario: this.route.snapshot.params.formulario
    };
    this.pregunta = {
      codigo: '',
      formulario: this.param.formulario,
      nombre: '',
      tipo: ''
    };
  }

  onEdit() {
    this.pregunta = {
      codigo: this.itemRes.codigo,
      simulador: this.itemRes.simulador,
      nombre: this.itemRes.nombre,
      tipo: this.itemRes.tipo
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.pregunta));
      this.update(this.pregunta);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.pregunta));
      this.add(this.pregunta);
    }
  }

  update(item: any) {
    console.log('simulador: a editar' + JSON.stringify(item));
    this.services.updatePregunta(item)
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
    this.services.addPregunta(item)
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
