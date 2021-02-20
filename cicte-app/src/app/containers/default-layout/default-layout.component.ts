import { Component, Input, OnInit } from '@angular/core';
import { navItems } from './../../_nav';
import { navCicte } from './../../_nav_cicte';
import { navAdmin } from './../../_nav_admin';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit {
  public navItems = navItems;
  public navCicte = navCicte;
  public navAdmin = navAdmin;
  public rol: string;

  profile: any;

  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement = document.body;
  constructor(private auth: LoginService) {
    this.changes = new MutationObserver((mutations) => {
      this.sidebarMinimized = document.body.classList.contains('sidebar-minimized');
    });
    this.changes.observe(<Element>this.element, {
      attributes: true
    });
    this.rol = localStorage.getItem('ROL');
    if (this.rol === 'ADMIN') {
      this.navItems = navAdmin;
      console.log('menu Admin');
    } else {
      this.navItems = navCicte;
      console.log('menu Instructor');
    }
  }
  ngOnInit() {
  }
  logout() {
    this.auth.logout();
  }
}
