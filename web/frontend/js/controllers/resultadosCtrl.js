class ResultadosController {

  constructor(resultadoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.resultadoService = resultadoService
    this.growl = growl
    this.primeros = []
    this.segundos = []
    this.llaves = []
    this.llavesCuartos = []
    this.llaveSemi = []
    this.llavesFinal = null
    this.puedeMostrarCuadro = false
    this.todosLosEquipos()
    this.todosLosPartidos()
    this.todosLosPrimeros()
    this.todosLosSegundos()
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
    this.resultadoService.listarEquipos()
      .then((response) => {
        this.equipos = response.data
      }, this.errorHandler)
  }

  /*
  OTRA MANERA:

  todosLosEquipos(){
    const promise = this.resultadoService.listarTodos()
        promise
            .then((response) => response.data)
            .then((data) => this.equipos = data)           
            .catch(this.errorHandler)
  }
*/

  todosLosPartidos() {
    this.resultadoService.listarPartidos()
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
        }, this.errorHandler)
    }
  }

  todosLosPrimeros() {
    for (let grupo of this.grupos) {
      this.resultadoService.listarEquiposPorGrupo(grupo)
        .then((response) => {
          this.primeros.push(response.data[0])
        }, this.errorHandler)
    }
  }

  todosLosSegundos() {
    for (let grupo of this.grupos) {
      this.resultadoService.listarEquiposPorGrupo(grupo)
        .then((response) => {
          this.segundos.push(response.data[1])
        }, this.errorHandler)
    }
  }

  todasLasLlaves() {
    this.resultadoService.listarLlaves()
      .then((response) => {
        this.llaves = response.data
      }, this.errorHandler)
  }

  elegirGanador(llave, equipo) {
    this.resultadoService.elegirGanador(llave, equipo)
      .then((response) => {
        this.todasLasLlaves()
        this.listarLlavesCuartos()
        this.listarLlavesSemi()
        this.listarLlavesFinal()
      }, this.errorHandler)
  }

  armarLlaves() {
    this.puedeMostrarCuadro = true
    this.resultadoService.armarLlaves()
      .then((response) => {
        this.todasLasLlaves()
        this.listarLlavesCuartos()
        this.listarLlavesSemi()
        this.listarLlavesFinal()
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

}