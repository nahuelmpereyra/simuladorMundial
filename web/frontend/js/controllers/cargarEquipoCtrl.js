class CargarEquipoController {

  constructor($state, cargarEquipoService, growl) {
    this.state = $state
    this.cargarEquipoService = cargarEquipoService
    this.equipoACargar = new Equipo()
    this.zonasValidas = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.growl = growl
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

  cargarEquipo() {
    this.validarZona(this.equipoACargar.zona)
    this.cargarEquipoService.cargarEquipo(this.equipoACargar)
      .then((response) => {
        this.notificarMensaje("Registraste a " + response.data.nombre + " exitosamente")
        //this.state.go("equipos")
      }, this.errorHandler)
  }

  upperCase(zona) {
    if (zona !== undefined) {
      this.equipoACargar.zona = zona.toLocaleUpperCase()
    }
  }

  validarZona(zona){
    if (!this.zonasValidas.includes(zona)){
      this.notificarError("Zona incorrecta")
      throw 'Zona incorrecta' // Necesario para que corte la ejecución.
    }
  }

}