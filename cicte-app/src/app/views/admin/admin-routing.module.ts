import { SimuladorComponent } from './simulador.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlanComponent } from './plan.component';
import { InstructorComponent } from './instructor.component';
import { IlusionComponent } from './ilusion.component';
import { ActualizaHuellaComponent } from './actualiza-huella.component';
import { RegistroHuellaComponent } from './registro-huella.component';
import { SelecionarHuellaComponent } from './selecionar-huella.component';
import { ActualizaHuellaPilotoComponent } from './actualiza-huella-piloto.component';


const routes: Routes = [
  {
    path: '',
    data: {
      title: 'admin'
    },
    children: [
      {
        path: 'instructor',
        component: InstructorComponent,
        data: {
          title: 'Instructores'
        }
      },
      {
        path: 'simulador',
        component: SimuladorComponent,
        data: {
          title: 'Simulador'
        }
      },
      {
        path: 'ilusion/:simulador',
        component: IlusionComponent,
        data: {
          title: 'Ilusiones'
        }
      },
      {
        path: 'plan/:simulador/:ilusion',
        component: PlanComponent,
        data: {
          title: 'Plan de Vuelo'
        }
      },
      // huellas
      {
        path: 'vincular-huella',
        component: RegistroHuellaComponent,
        data: {
          title: 'Vincular Huella'
        }
      },
      {
        path: 'actualizar-huella',
        component: ActualizaHuellaPilotoComponent,
        data: {
          title: 'Actualizar Huella'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
