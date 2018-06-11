class RegisterController {

  constructor($state, usuarioService, growl) {
    this.state = $state
    this.usuarioService = usuarioService
    this.growl = growl
    this.usuarioARegistrar = new Usuario()
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


  agregarUsuario() {
    this.usuarioService.register(this.usuarioARegistrar)
      .then((response) => {
        this.notificarMensaje("Te registraste como: " + this.usuarioARegistrar.username)
        this.state.go("login")
      }, this.errorHandler)


  }
}