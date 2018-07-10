class ResultadosController {

  constructor(resultadoService, equipoService, partidoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.resultadoService = resultadoService
    this.equipoService = equipoService
    this.partidoService = partidoService
    this.growl = growl
    this.primeros = []
    this.segundos = []
    this.llaves = []
    this.llavesCuartos = []
    this.llaveSemi = []
    this.hayLlavesArmadas()
    this.todosLosEquipos()
    this.todosLosPartidos()
    this.todosLosPrimeros()
    this.todosLosSegundos()
    this.listarLlavesOctavos()
    this.listarLlavesCuartos()
    this.listarLlavesSemi()
    this.listarLlavesFinal()
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

  todosLosEquipos() {
    this.equipoService.listarEquipos()
      .then((response) => {
        this.equipos = response.data
      }, this.errorHandler)
  }


  todosLosPartidos() {
    this.partidoService.listarPartidos()
      .then((response) => {
        this.partidos = response.data
      }, this.errorHandler)
  }

  esEquipoDeGrupo(equipo, grupo) {
    return equipo.zona == grupo
  }

  actualizarResultado(partido) {
    if (partido.resultado.golesLocal != undefined && partido.resultado.golesVisitantes != undefined) {
      this.resultadoService.actualizarResultado(partido)
        .then((response) => {
          this.todosLosEquipos()
          this.listarLlavesOctavos()
          this.listarLlavesCuartos()
          this.listarLlavesSemi()
          this.listarLlavesFinal()
        }, this.errorHandler)
    }
  }

  todosLosPrimeros() {
    for (let grupo of this.grupos) {
      this.equipoService.listarEquiposPorGrupo(grupo)
        .then((response) => {
          this.primeros.push(response.data[0])
        }, this.errorHandler)
    }
  }

  todosLosSegundos() {
    for (let grupo of this.grupos) {
      this.equipoService.listarEquiposPorGrupo(grupo)
        .then((response) => {
          this.segundos.push(response.data[1])
        }, this.errorHandler)
    }
  }

  elegirGanador(llave, equipo) {
    this.resultadoService.elegirGanador(llave, equipo)
      .then((response) => {
        this.listarLlavesOctavos()
        this.listarLlavesCuartos()
        this.listarLlavesSemi()
        this.listarLlavesFinal()
      }, this.errorHandler)
  }

  armarLlaves() {
    this.resultadoService.armarLlaves()
      .then((response) => {
        this.listarLlavesOctavos()
        this.listarLlavesCuartos()
        this.listarLlavesSemi()
        this.listarLlavesFinal()
        this.hayLlavesArmadas()
      }, this.errorHandler)
  }

  llave(numero) {
    return this.llaves[numero]
  }

  llaveCuartos(numero) {
    return this.llavesCuartos[numero]
  }

  llaveSemis(numero) {
    return this.llaveSemi[numero]
  }

  llaveFinal() {
    return this.llavesFinal
  }

  listarLlavesOctavos() {
    this.resultadoService.listarLlavesOctavos()
      .then((response) => {
        this.llaves = response.data
      }, this.errorHandler)
  }

  listarLlavesCuartos() {
    this.resultadoService.listarLlavesCuartos()
      .then((response) => {
        this.llavesCuartos = response.data
      }, this.errorHandler)
  }

  listarLlavesSemi() {
    this.resultadoService.listarLlavesSemi()
      .then((response) => {
        this.llaveSemi = response.data
      }, this.errorHandler)
  }

  listarLlavesFinal() {
    this.resultadoService.listarLlavesFinal()
      .then((response) => {
        this.llavesFinal = response.data
      }, this.errorHandler)
  }


  hayLlavesArmadas() {
    this.resultadoService.hayLlaves()
      .then((response) => {
        this.llavesArmadas = response.data
      }, this.errorHandler)
  }

}