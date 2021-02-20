export const navCicte = [
    {
      name: 'Dashboard',
      url: '/dashboard',
      icon: 'icon-speedometer',
      badge: {
        variant: 'info'
      }
    },
    {
      title: true,
      name: 'Personal'
    },
    {
      name: 'Cuentas',
      url: '',
      icon: 'icon-people',
      children: [
        {
          name: 'Pilotos',
          url: '/cicte/se-pilotos',
          icon: 'icon-user-follow',
          badge: {
            variant: 'success',
            text: 'NEW'
          }
        }
      ]
    },
    {
      title: true,
      name: 'Biometría'
    },
    {
      name: 'Huella Dactilar',
      url: '',
      icon: 'fa fa-handshake-o',
      children: [
        {
          name: 'Vincular',
          url: '/admin/vincular-huella',
          icon: 'fa fa-hand-pointer-o',
        }
      ]
    },
    {
      title: true,
      name: 'Evaluaciones'
    },
    {
      name: 'Valoración',
      url: '',
      icon: 'fa fa-institution',
      children: [
        {
          name: 'Formularios',
          url: '/cicte/formulario',
          icon: 'fa fa-map',
        },
        {
          name: 'Preguntas',
          url: '/cicte/pregunta/0',
          icon: 'fa fa-question-circle',
        },
        {
          name: 'Respuestas',
          url: '/cicte/respuesta/0/0',
          icon: 'fa fa-font',
        },
        {
          name: 'Evaluación',
          url: '/cicte/evaluacion',
          icon: 'fa fa-mortar-board'
        },
        {
          name: 'Reportes',
          url: '/cicte/resultado',
          icon: 'fa fa-line-chart'
        }
      ]
    }
  ];
