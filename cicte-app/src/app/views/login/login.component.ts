import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html'
})
export class LoginComponent implements OnInit {

  public user: Object = {
    username: '',
    email: '',
    password: '',
    terminal: '',
    intento: '',
    instructor: '',
  };
  // Alerts
  private _success = new Subject<string>();
  successMessage: string;
  public alert: any = {
    msg: '',
    type: ''
  };

  constructor(private auth: LoginService, private router: Router) {
  }
  ngOnInit() {
    localStorage.clear();
    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => {
      this.successMessage = null,
      this.router.navigate(['./dashboard']);
    });
  }

  onSubmit(forma: NgForm) {
    this.changeSuccessMessage();
    console.log('Datos de form: ' + forma.value);
    console.log('es editado ===> ' + JSON.stringify(this.user));
    this.login(this.user);
  }
  login(user: any) {
    this.removeStorage();
    this.auth.login(user)
      .subscribe(data => {
        const login = data['login'];
        console.log('Datos Login' + JSON.stringify(login));
        if (data['success']) {
          localStorage.setItem('ROL', login['rol']);
          localStorage.setItem('USER', login['usuario']);
          localStorage.setItem('INSTRUCTOR', login['instructor']);
          localStorage.setItem('ACCESS', data['access']);
          localStorage.setItem('DATE', login['ultimoIngreso']);
          this.alert = {
            msg: 'Operación Correcta',
            type: 'success'
          };
          this.router.navigate(['./dashboard']);
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

  removeStorage() {
    localStorage.removeItem('ROL');
    localStorage.removeItem('USER');
    localStorage.removeItem('INSTRUCTOR');
    localStorage.removeItem('ACCESS');
    localStorage.removeItem('DATE');
  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - Message successfully changed.`);
  }
}

