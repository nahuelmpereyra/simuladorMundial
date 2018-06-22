class CargarEquipoController {

  constructor($state, cargarEquipoService, cargarResultadoService, growl) {
    this.state = $state
    this.cargarEquipoService = cargarEquipoService
    this.cargarResultadoService = cargarResultadoService
    this.equipoACargar = new Equipo()
    this.equipoAEliminar = new Equipo()
    this.equipoAEditar = null
    this.zonasValidas = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.growl = growl
    this.todosLosEquipos()
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

  eliminarEquipo() {
    const mensaje = "¿Está seguro que desea eliminar a <b>'" + this.equipoAEliminar.nombre + "'</b>?"
    bootbox.confirm({
      message: mensaje,
      buttons: {
        confirm: {
          label: 'Sí, eliminar',
          className: 'btn-success'
        },
        cancel: {
          label: 'Cancelar',
          className: 'btn-danger'
        }
      },
      callback: (confirma) => {
        if (confirma) {
          this.cargarEquipoService.eliminarEquipo(this.equipoAEliminar)
            .then((response) => {
              this.notificarMensaje("Eliminaste a " + this.equipoAEliminar.nombre + " exitosamente")
            }, this.errorHandler)
        }
      }
    })
  }

  editarEquipo(){
    this.validarZona(this.equipoAEditar.zona)
    this.cargarEquipoService.editarEquipo(this.equipoAEditar, this.equipoEditado)
    .then((response) => {
      this.notificarMensaje("Editaste a " + this.equipoAEditar.nombre + " exitosamente")
    }, this.errorHandler)
  }

  upperCase(zona) {
    if (zona !== undefined) {
      this.equipoACargar.zona = zona.toLocaleUpperCase()
      this.equipoAEditar.zona = zona.toLocaleUpperCase()
    }
  }

  validarZona(zona) {
    if (!this.zonasValidas.includes(zona)) {
      this.notificarError("Zona incorrecta")
      throw 'Zona incorrecta' // Necesario para que corte la ejecución.
    }
  }

  todosLosEquipos() {
    this.cargarResultadoService.listarEquipos()
      .then((response) => {
        this.equipos = response.data
      }, this.errorHandler)
  }

}