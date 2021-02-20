export const navItems = [
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
    name: 'Milicia',
    url: '/cicte',
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
      },
      {
        name: 'Instructor',
        url: '/admin/instructor',
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
    name: 'Valoración'
  },
  {
    name: 'Evaluación',
    url: '/cicte/',
    icon: 'icon-pencil',
    children: [
      {
        name: 'Buscar',
        url: '/cicte/evaluacion',
        icon: 'icon-user-follow'
      },
      {
        name: 'Resultados',
        url: '/cicte/resultado',
        icon: 'icon-user-follow'
      },
      {
        name: 'Formularios',
        url: '/cicte/formulario',
        icon: 'icon-drop'
      }
    ]
  },
  {
    title: true,
    name: 'Configuración'
  },
  {
    name: 'Simulador',
    url: '/cicte/simulador',
    icon: 'icon-drop'
  },
  {
    name: 'Ilusión',
    url: '/cicte/ilusion',
    icon: 'icon-drop'
  },
  {
    name: 'Plan de Vuelo',
    url: '/cicte/plan',
    icon: 'icon-pencil'
  }
];
