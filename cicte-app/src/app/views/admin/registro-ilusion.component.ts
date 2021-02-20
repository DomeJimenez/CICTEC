import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { IlusionService } from '../../services/ilusion.service';

@Component({
  selector: 'app-registro-ilusion',
  templateUrl: './registro-ilusion.component.html'
})
export class RegistroIlusionComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public ilusion: Object = {
    codigo: '',
    simulador: '',
    nombre: '',
    tipo: ''
  };

  param: {
    simulador: string
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

  constructor(private services: IlusionService, private router: Router, private route: ActivatedRoute) { }

  onNew() {
    this.param = {
      simulador: this.route.snapshot.params.simulador
    };
    this.ilusion = {
      codigo: '',
      simulador: this.param.simulador,
      nombre: '',
      tipo: ''
    };
  }

  onEdit() {
    this.ilusion = {
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
      console.log('es editado ===>' + JSON.stringify(this.ilusion));
      this.update(this.ilusion);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.ilusion));
      this.add(this.ilusion);
    }
  }

  update(item: any) {
    console.log('simulador: a editar' + JSON.stringify(item));
    this.services.updateIlusion(item)
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
    this.services.addIlusion(item)
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
