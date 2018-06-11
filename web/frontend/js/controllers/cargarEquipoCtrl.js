class CargarEquipoController {

  constructor($state, cargarEquipoService) {
    this.state = $state
    this.cargarEquipoService = cargarEquipoService
    this.equipoACargar = null
    // this.growl = growl
    // this.errorHandler = (response) => {
    //   if (response.data) {
    //     this.notificarError(response.data.error)
    //   } else {
    //     this.notificarError("Error de conexión, intente nuevamente luego.")
    //   }
    // }
  }


  // NOTIFICACIONES & ERRORES
  // notificarMensaje(mensaje) {
  //   this.growl.info(mensaje)
  // }

  // notificarError(mensaje) {
  //   this.growl.error(mensaje)
  // }

  cargarEquipo() {
    this.cargarEquipoService.cargarEquipo(this.equipoACargar)
      .then((response) => {
        //this.notificarMensaje("Cargaste: " + response.data.nombre)
        //this.state.go("equipos")
      }, this.errorHandler)
  }

}