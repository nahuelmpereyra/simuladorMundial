  class LoginController {

    constructor($state, usuarioService, growl) {
      this.state = $state
      this.usuarioService = usuarioService
      this.growl = growl
      this.usuarioALoguear = null
      this.errorHandler = (response) => {
        if (response.data) {
          this.notificarError(response.data.error)
        } else {
          this.notificarError("Error de conexión, intente nuevamente luego.")
        }
      }
    }


    // NOTIFICACIONES & ERRORES
    notificarMensaje(mensaje) {
      this.growl.info(mensaje)
    }

    notificarError(mensaje) {
      this.growl.error(mensaje)
    }


    loginUsuario() {
      this.usuarioService.login(this.usuarioALoguear)
        .then((response) => {
          this.notificarMensaje("Ingresaste como: " +response.data.username) //this.usuarioALoguear.username)
          this.barraSuperiorService.usuarioLogueado = response.data//this.usuarioALoguear
          //this.state.go("buscarViajes")
        }, this.errorHandler)
    }

  }