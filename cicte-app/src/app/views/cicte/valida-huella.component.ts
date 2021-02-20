import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms/src/directives/ng_form';
import { Subject } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { Router } from '@angular/router';
import { HuellaService } from '../../services/huella.service';

@Component({
  selector: 'app-valida-huella',
  templateUrl: './valida-huella.component.html',
  styleUrls: ['./selecionar-huella.component.scss']
})
export class ValidaHuellaComponent implements OnInit {

  @Input() itemRes: any;
  @Input() isValidate: boolean;

  @Output() outValidate = new EventEmitter();

  private _success = new Subject<string>();
  private id_mil = '';
  private _flag = false;
  public _isValidate = false;


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

  selectFingerToCapture(hand: string, finger: string, event) {
    console.log('Mano: ' + hand + '    Dedo: ' + finger);
    let request: any;
    request = {
      id: this.id_mil,
      mano: hand,
      dedo: finger
    };
    if (this._flag) {
      alert('Ingrese el dedo en el lector');
      this.validate(request, event);
    } else {
      alert('El piloto ya dispone de registro biométrico');
    }
  }

  public changeSuccessMessage() {
    this._success.next(`${new Date()} - Message successfully changed.`);
  }
  ngOnInit() {
    this._isValidate = false;
    this.resetFinger();
    this._success.subscribe(message => (this.successMessage = message));
    this._success.pipe(
      debounceTime(4000)
    ).subscribe(() => {
      this.successMessage = null;
    });
  }

  pasarInfo(event) {
    console.log('Desde el hijo');
    this.outValidate.emit({isValFinger: this._isValidate});
  }
  constructor(private services: HuellaService, private router: Router) {
    this._isValidate = false;
  }

  onNew() {
    this.huella = {
      id: '',
      codigo: '',
      mano: '',
      dedo: ''
    };
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
        this.validateHands(data['mano'], data['dedo']);
        this._flag = true;
      } else {
        this._flag = false;
        this.resetFinger();
      }
    });
  }

  validate(item: any, event) {
    console.log('huella: a editar en validate: ' + JSON.stringify(item));
    this.services.validate(item).subscribe(data => {
      console.log('Validate JSON: ' + JSON.stringify(data));
      if (data['success']) {
        this._isValidate = true;
        this.pasarInfo(event);
        this.alert = {
          msg: 'Operación Correcta',
          type: 'success'
        };
        this.changeSuccessMessage();
      } else {
        this._isValidate = false;
        const message = data['message'];
        this.alert = {
          msg: message['message'],
          type: 'danger'
        };
        this.changeSuccessMessage();
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

  validateHands(hand: string, finger: string) {

    console.log('Mano: ' + hand + ' Dedo:  ' + finger );
    if ('left' === hand) {
      switch (finger) {
        case 'little':
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
          this.hands = {
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
}
