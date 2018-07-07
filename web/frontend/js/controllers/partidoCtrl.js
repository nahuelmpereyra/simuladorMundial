class PartidoController {

    constructor($state, equipoService, partidoService, growl) {
        this.state = $state
        this.equipoService = equipoService
        this.partidoService = partidoService
        this.calendarioAbierto = false
        this.partido = new Partido()
        this.growl = growl
        this.errorHandler = (response) => {
            if (response.data) {
                this.notificarError(response.data.error)
            } else {
                this.notificarError("Error de conexión, intente nuevamente luego.")
            }
        }
        this.resetPartidos()
    }


    // NOTIFICACIONES & ERRORES
    notificarMensaje(mensaje) {
        this.growl.info(mensaje)
    }

    notificarError(mensaje) {
        this.growl.error(mensaje)
    }

    verCalendario($event) {
        $event.preventDefault()
        $event.stopPropagation()
        this.calendarioAbierto = true
    }

    // LISTAR
    resetPartidos() {
        this.partidoService.listarPartidos()
            .then((response) => {
                this.partidos = response.data
            }, this.errorHandler)
    }

    // CARGAR
    cargarPartido() {
        this.partido.fecha = moment(this.partido.fecha).format("DD/MM/YYYY HH:mm")
        if (this.partido.equipoLocal != this.partido.equipoVisitante) {
            this.partidoService.cargarPartido(this.partido)
                .then((response) => {
                    this.notificarMensaje("Partido " + response.data.equipoLocal.nombre + "-" + response.data.equipoVisitante.nombre + " agregado exitosamente")
                    this.resetPartidos()
                }, this.errorHandler)
        } else {
            this.notificarError("Los equipos no pueden ser iguales")
        }
    }

    // ELIMINAR
    eliminarPartido(partido) {
        const mensaje = "¿Está seguro que desea eliminar el partido <b>'" + partido.equipoLocal.nombre + "-" + partido.equipoVisitante.nombre + "'</b>?<br><br><b><u>Nota:</b></u> Una vez eliminado no se podrá recuperar"
    
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
              this.partidoService.eliminarPartido(partido)
                .then((response) => {
                  this.notificarMensaje("Eliminaste el partido " + partido.equipoLocal.nombre + "-" + partido.equipoVisitante.nombre + " exitosamente")
                  this.resetPartidos()
                }, this.errorHandler)
            }
          }
        })
      }
    



}