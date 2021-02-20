import { Component, OnInit, Input } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { HuellaService } from '../../services/huella.service';

@Component({
  selector: 'app-selecionar-huella',
  templateUrl: './selecionar-huella.component.html',
  styleUrls: ['./selecionar-huella.component.scss']
})
export class SelecionarHuellaComponent implements OnInit {
  @Input() itemRes: any;
  @Input() isEdit: boolean;

  private _success = new Subject<string>();
  private id_mil = '';
  private _flag = false;

  successMessage: string;

  public alert: any = {
    msg: '',
    type: ''
  };

  public huella: any = {
    id: '',
    codigo: '',
    mano: '',
    dedo: ''
  };

  public fingerPrintsToShow: Object = {
    left: {
      little: false,
      ring: false,
      middle: false,
      index: false,
      thumb: false
    },
    right: {
      little: false,
      ring: false,
      middle: false,
      index: false,
      thumb: false
    }
  };
  public hands: Object = {
    left: {
      little: true,
      ring: true,
      middle: true,
      index: true,
      thumb: true
    },
    right: {
      little: true,
      ring: true,
      middle: true,
      index: true,
      thumb: true
    }
  };

  selectFingerToCapture(hand: string, finger: string) {
    console.log('Mano: ' + hand + '    Dedo: ' + finger);
    let request: any;
    request = {
      id: this.id_mil,
      mano: hand,
      dedo: finger
    };
    if (this._flag) {
      alert('Ingrese el dedo en el lector');
      this.capture(request);
    } else {
      alert('El piloto ya dispone de registro biométrico');
    }
  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - Message successfully changed.`);
  }
  ngOnInit() {
    this.resetFinger();
    this._success.subscribe(message => (this.successMessage = message));
    this._success.pipe(debounceTime(3000)).subscribe(() => {
      (this.successMessage = null), this.router.navigate(['./dashboard']);
    });
  }

  constructor(private services: HuellaService, private router: Router) {}

  onNew() {
    this.huella = {
      id: '',
      codigo: '',
      mano: '',
      dedo: ''
    };
  }

  onEdit() {}

  onSubmit(forma: NgForm) {
    console.log('Datos de form: ' + forma.value);
    this.changeSuccessMessage();
    console.log('Formulario posteado');
    if (this.isEdit) {
      console.log('es editado ===>' + JSON.stringify(this.huella));
      this.onEdit();
    } else {
      console.log('es uno nuevo ===>' + JSON.stringify(this.huella));
      this.onEdit();
    }
  }

  fingerStatus(item: any) {
    console.log('huella: a editar' + JSON.stringify(item.id_mil));
    this.id_mil = item.id_mil;
    let request: any;
    request = {
      id: item.id_mil
    };
    this.services.getFingerStatus(request).subscribe(data => {
      console.log('getFingerStatus:  ' + JSON.stringify(data));
      if (data['success']) {
        this.alert = {
          msg: 'El piloto ya cuenta con un registro biométrico',
          type: 'danger'
        };
        this.validateFinger(data['mano'], data['dedo']);
        this._flag = false;
      } else {
        this._flag = true;
        this.resetFinger();
      }
    });
  }

  validate(item: any) {
    console.log('huella: a editar' + JSON.stringify(item));
    this.services.validate(item).subscribe(data => {
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
    });
  }

  capture(item: any) {
    console.log('huella nuevo: ' + JSON.stringify(item));
    this.services.capture(item).subscribe(data => {
      if (data['success']) {
        this.alert = {
          msg: 'Operación Correcta',
          type: 'success'
        };
        this.changeSuccessMessage();
      } else {
        const message = data['message'];
        this.alert = {
          msg: message['message'],
          type: 'danger'
        };
        this.changeSuccessMessage();
      }
    });
  }

  validateFinger(hand: string, finger: string) {

    console.log('Mano: ' + hand + ' Dedo:  ' + finger );
    if ('left' === hand) {
      switch (finger) {
        case 'little':
          this.fingerPrintsToShow = {
            left: {
              little: true,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            },
            right: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'ring':
          this.fingerPrintsToShow = {
            left: {
              little: false,
              ring: true,
              middle: false,
              index: false,
              thumb: false
            },
            right: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'middle':
          this.fingerPrintsToShow = {
            left: {
              little: false,
              ring: false,
              middle: true,
              index: false,
              thumb: false
            },
            right: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'index':
          this.fingerPrintsToShow = {
            left: {
              little: false,
              ring: false,
              middle: false,
              index: true,
              thumb: false
            },
            right: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'thumb':
          this.fingerPrintsToShow = {
            left: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: true
            },
            right: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        default:
      }
    } else if ('right' === hand) {
      switch (finger) {
        case 'little':
          this.fingerPrintsToShow = {
            right: {
              little: true,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            },
            left: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'ring':
          this.fingerPrintsToShow = {
            right: {
              little: false,
              ring: true,
              middle: false,
              index: false,
              thumb: false
            },
            left: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'middle':
          this.fingerPrintsToShow = {
            right: {
              little: false,
              ring: false,
              middle: true,
              index: false,
              thumb: false
            },
            left: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'index':
          this.fingerPrintsToShow = {
            right: {
              little: false,
              ring: false,
              middle: false,
              index: true,
              thumb: false
            },
            left: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        case 'thumb':
          this.fingerPrintsToShow = {
            right: {
            little: false,
            ring: false,
            middle: false,
            index: false,
            thumb: true
            },
            left: {
              little: false,
              ring: false,
              middle: false,
              index: false,
              thumb: false
            }
          };
          break;
        default:
      }
    }
  }

  resetFinger() {
    this.fingerPrintsToShow = {
      left: {
        little: false,
        ring: false,
        middle: false,
        index: false,
        thumb: false
      },
      right: {
        little: false,
        ring: false,
        middle: false,
        index: false,
        thumb: false
      }
    };
  }
}
