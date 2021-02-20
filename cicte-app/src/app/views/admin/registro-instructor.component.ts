import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import { Router } from '@angular/router';
import { InstructorService } from '../../services/instructor.service';
import { ApiService } from '../../services/api.service';


@Component({
  selector: 'app-registro-instructor',
  templateUrl: './registro-instructor.component.html'
})
export class RegistroInstructorComponent implements OnInit {
  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  public catalogo: any[] = [];

  public instructor: Object = {
    codigo: '',
    cedula: '',
    id_mil: '',
    nombre: '',
    apellido: '',
    grado: '',
    email: ''
  };

  constructor(private services: InstructorService,
    private utils: ApiService, private router: Router) {
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

  onNew() {
    this.getCatalogo();
    this.instructor = {
      codigo: '',
      cedula: '',
      id_mil: '',
      nombre: '',
      apellido: '',
      grado: '',
      email: ''
    };
  }

  onEdit() {
    this.getCatalogo();
    this.instructor = {
      codigo: this.itemRes.codigo,
      cedula: this.itemRes.cedula,
      id_mil: this.itemRes.id_mil,
      nombre: this.itemRes.nombre,
      apellido: this.itemRes.apellido,
      grado: this.itemRes.grado,
      email: this.itemRes.email
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.instructor));
      this.update(this.instructor);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.instructor));
      this.add(this.instructor);
    }
  }

  update(item: any) {
    console.log('Instructor: a editar' + JSON.stringify(item));
    this.services.updateInstructor(item)
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
    console.log('Instructor nuevo: ' + JSON.stringify(item));
    this.services.addInstructor(item)
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

  getCatalogo() {
    let tabla: any;
    tabla = {
      tabla: 'cic_piloto'
    };
    this.utils.getCatalogo(tabla)
      .subscribe(data => {
        this.catalogo = data;
      });
    console.log('Datos del catalogo' + JSON.stringify(this.catalogo));
  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - Message successfully changed.`);
  }
}
