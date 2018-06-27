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

    cargarPartido() {

        this.partido.fecha = moment(this.partido.fecha).format("DD/MM/YYYY HH:mm")
        if (this.partido.equipoLocal != this.partido.equipoVisitante) {
            this.partidoService.cargarPartido(this.partido)
                .then((response) => {
                    this.notificarMensaje("Partido " + response.data.equipoLocal.nombre + "-" + response.data.equipoVisitante.nombre + " agregado exitosamente")
                }, this.errorHandler)
        } else {
            this.notificarError("Los equipos no pueden ser iguales")
        }
    }



}