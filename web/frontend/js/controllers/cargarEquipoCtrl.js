class CargarEquipoController {

  constructor($state, cargarEquipoService, cargarResultadoService, growl) {
    this.state = $state
    this.cargarEquipoService = cargarEquipoService
    this.cargarResultadoService = cargarResultadoService
    this.equipoACargar = null
    this.equipoParaModificar = null
    this.busqueda = ""
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

  // BUSCAR
  buscarEquipos() {
    const promise = (this.busqueda == "") ?
      this.listarTodos() :
      this.cargarResultadoService.buscar(this.busqueda)

    promise.then((response) => {
      this.equipos = response.data
    }, this.errorHandler)
  }

  // CARGAR
  cargarEquipo() {
    if (this.equipoACargar != null) {
      this.validarZona(this.equipoACargar.zona)
      this.cargarEquipoService.cargarEquipo(this.equipoACargar)
        .then((response) => {
          this.notificarMensaje("Agregaste a " + response.data.nombre + " exitosamente")
          this.todosLosEquipos()
          this.equipoACargar = null
        }, this.errorHandler)
    }
  }

  // ELIMINAR
  eliminarEquipo(equipo) {
    const mensaje = "¿Está seguro que desea eliminar a <b>'" + equipo.nombre + "'</b>?<br><br><b><u>Nota:</b></u> Se eliminarán todos los partidos en los cuales figure."

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
          this.cargarEquipoService.eliminarEquipo(equipo)
            .then((response) => {
              this.notificarMensaje("Eliminaste a " + equipo.nombre + " exitosamente")
              this.todosLosEquipos()
            }, this.errorHandler)
        }
      }
    })
  }

  // MODIFICAR
  modificarEquipo(equipo) {
    // Copiamos al equipo porque sino al cerrar el diálogo queda modificado en la lista
    this.equipoParaModificar = Object.assign({}, equipo);
    $("#modificarEquipoModal").modal({})
  }

  aplicarModificacion() {
    this.validarZona(this.equipoParaModificar.zona)
    this.cargarEquipoService.editarEquipo(this.equipoParaModificar).then(() => {
      this.notificarMensaje('Equipo modificado!')
      this.todosLosEquipos()
    }, this.errorHandler)

    this.equipoParaModificar = null
    $("#modificarEquipoModal").modal('toggle')
  }


  upperCase(zona) {
    if (zona !== undefined) {
      this.equipoACargar.zona = zona.toLocaleUpperCase()
      this.equipoParaModificar.zona = zona.toLocaleUpperCase()
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