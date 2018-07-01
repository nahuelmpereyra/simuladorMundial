class ResultadosController {

  constructor(resultadoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.resultadoService = resultadoService
    this.growl = growl
    this.primeros = []
    this.segundos = []
    this.todosLosEquipos()
    this.todosLosPartidos()
    this.todosLosPrimeros()
    this.todosLosSegundos()
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


}