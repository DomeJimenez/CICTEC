import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PilotoComponent } from './piloto.component';
import { RegistroPilotoComponent } from './registro-piloto.component';
import { EvaluacionComponent } from './evaluacion.component';
import { FormularioComponent } from './formulario.component';
import { ResultadoComponent } from './resultado.component';
import { PreguntaComponent } from './pregunta.component';
import { RespuestaComponent } from './respuesta.component';
import { FormWizardComponent } from './form-wizard.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'cicte'
    },
    children: [
      {
        path: 'se-pilotos',
        component: PilotoComponent,
        data: {
          title: 'Buscar Piloto'
        }
      },
      {
        path: 'formulario',
        component: FormularioComponent,
        data: {
          title: 'Formularios'
        }
      },
      {
        path: 'pregunta/:formulario',
        component: PreguntaComponent,
        data: {
          title: 'Preguntas'
        }
      },
      {
        path: 'respuesta/:pregunta/:formulario',
        component: RespuestaComponent,
        data: {
          title: 'Respuestas'
        }
      },
      {
        path: 'evaluacion',
        component: FormWizardComponent,
        data: {
          title: 'Evaluación'
        }
      },
      {
        path: 'resultado',
        component: ResultadoComponent,
        data: {
          title: 'Resultados'
        }
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CicteRoutingModule {}
