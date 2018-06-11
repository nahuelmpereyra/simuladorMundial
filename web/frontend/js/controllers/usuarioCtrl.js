class UsuarioController {


    constructor($state, usuarioService, BarraSuperiorService, growl) {
        this.state = $state
        this.usuarioService = usuarioService
        this.barraSuperiorService = BarraSuperiorService
        this.growl = growl
        this.usuarioAEditar = this.barraSuperiorService.usuarioLogueado
        this.hayAlgoEditado = false
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


    editarUsuario() {
      this.usuarioService.edit(this.usuarioAEditar)
        .then((response) => {
            this.notificarMensaje("Usuario editado con éxito")
        }, this.errorHandler)
    }

    cambio(){
      this.hayAlgoEditado = true
    }

  }