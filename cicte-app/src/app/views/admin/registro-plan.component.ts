import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { PlanService } from '../../services/plan.service';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Component({
  selector: 'app-registro-plan',
  templateUrl: './registro-plan.component.html'
})
export class RegistroPlanComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public plan: Object = {
    simulador: '',
    ilusion: '',
    nombre: '',
    lugar: ''
  };

  param: {
    simulador: string,
    ilusion: string
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

  constructor(private services: PlanService, private router: Router, private route: ActivatedRoute) { }

  onNew() {
    this.param = {
      simulador: this.route.snapshot.params.simulador,
      ilusion: this.route.snapshot.params.ilusion
    };
    this.plan = {
      simulador: '',
      ilusion: this.param.ilusion,
      nombre: '',
      lugar: ''
    };
  }

  onEdit() {
    this.plan = {
      codigo: this.itemRes.codigo,
      simulador: this.itemRes.simulador,
      ilusion: this.itemRes.ilusion,
      nombre: this.itemRes.nombre,
      lugar: this.itemRes.lugar
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.plan));
      this.update(this.plan);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.plan));
      this.add(this.plan);
    }
  }

  update(item: any) {
    console.log('simulador: a editar' + JSON.stringify(item));
    this.services.updatePlan(item)
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
    this.services.addPlan(item)
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
