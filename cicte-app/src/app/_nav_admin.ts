export const navAdmin = [
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
    name: 'Cuentas'
  },
  {
    name: 'Personal',
    url: '',
    icon: 'fa fa-user-circle',
    children: [
      {
        name: 'Pilotos',
        url: '/cicte/se-pilotos',
        icon: 'fa fa-vcard-o',
      },
      {
        name: 'Instructor',
        url: '/admin/instructor',
        icon: 'fa fa-grav'
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
      },
      {
        name: 'Actualizar',
        url: '/admin/actualizar-huella',
        icon: 'fa fa-hand-paper-o'
      }
    ]
  },
  {
    title: true,
    name: 'Configuración'
  },
  {
    name: 'Componentes',
    url: '',
    icon: 'fa fa-industry',
    children: [
      {
        name: 'Simulador',
        url: '/admin/simulador',
        icon: 'fa fa-television',
      },
      {
        name: 'Ilusión',
        url: '/admin/ilusion/0',
        icon: 'fa fa-plane'
      },
      {
        name: 'Plan de Vuelo',
        url: '/admin/plan/0/0',
        icon: 'fa fa-user-secret'
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
        name: 'Reportes',
        url: '/cicte/resultado',
        icon: 'fa fa-line-chart'
      }
    ]
  }
];
