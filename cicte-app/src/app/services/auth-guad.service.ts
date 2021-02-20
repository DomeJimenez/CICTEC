// src/app/auth/auth.service.ts

import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot,
         RouterStateSnapshot, CanActivate  } from '@angular/router';
import { AuthService } from './auth.service';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { LoginService } from './login.service';
@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private auth: LoginService) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log(next);
    if (localStorage.getItem('ACCESS') === 'true') {
      console.log('Aceptado por el Guard');
      return true;
    } else {
      console.log('Bloqueado por el Guard');
      return false;
    }
  }

}
