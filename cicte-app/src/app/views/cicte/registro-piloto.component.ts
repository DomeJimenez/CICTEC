import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import { Router } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { PilotoService } from '../../services/piloto.service';

@Component({
  selector: 'app-registro-piloto',
  templateUrl: './registro-piloto.component.html'
})
export class RegistroPilotoComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  selectedFile: File;

  public catalogo: any[] = [];

  public piloto: Object = {
    codigo: '',
    cedula: '',
    id_mil: '',
    nombre: '',
    apellido: '',
    grado: ''
  };

  public base64Files: string[] = [];
  private files: any[] = [];
  private fileReader = new FileReader();

  constructor(private services: PilotoService,
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
    this.piloto = {
      codigo: '',
      cedula: '',
      id_mil: '',
      nombre: '',
      apellido: '',
      grado: ''
    };
  }

  onEdit() {
    this.getCatalogo();
    this.piloto = {
      codigo: this.itemRes.codigo,
      cedula: this.itemRes.cedula,
      id_mil: this.itemRes.id_mil,
      nombre: this.itemRes.nombre,
      apellido: this.itemRes.apellido,
      grado: this.itemRes.grado
    };
  }

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.piloto));
      this.update(this.piloto);
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.piloto));
      this.add(this.piloto);
    }
  }

  update(item: any) {
    console.log('Piloto: a editar' + JSON.stringify(item));
    this.services.updatePiloto(item)
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
    console.log('Piloto nuevo: ' + JSON.stringify(item));
    this.services.addPiloto(item)
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

  onFileChanged(event: Event) {
    this.files = event.target['files'];
    if (event.target['files']) {
      console.log(event.target['files']);
      this.readFiles(event.target['files'], 0);
    }
    console.log('Imagen  ---> ' + JSON.stringify(this.base64Files) );
  }

  private readFiles(files: any[], index: number) {
    const file = files[index];
    this.fileReader.onload = () => {
      this.base64Files.push(this.fileReader.result.toString());
      if (files[index + 1]) {
        this.readFiles(files, index + 1);
      } else {
        console.log('loaded all files');
      }
    };
    this.fileReader.readAsDataURL(file);
  }
}
