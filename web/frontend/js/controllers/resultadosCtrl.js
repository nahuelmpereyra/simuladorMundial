class ResultadosController {

  constructor(resultadoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.resultadoService = resultadoService
    this.growl = growl
    this.todosLosEquipos()
    this.todosLosPartidos()
  }

  /*
  mostrarEquipos(){
    const promise = this.resultadoService.listarTodos()
        promise
            .then((response) => response.data)
            .then((data) => this.equipos = data)           
            .catch(this.errorHandler)
  }
*/
  todosLosEquipos() {
    this.resultadoService.listarEquipos()
      .then((response) => {
        this.equipos = response.data
      }, this.errorHandler)
  }

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




}