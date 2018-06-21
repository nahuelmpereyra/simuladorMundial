class CargarResultadosController {

  constructor(cargarResultadoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.cargarResultadoService = cargarResultadoService
    this.growl = growl
    this.todosLosEquipos()
    this.todosLosPartidos()
  }

  /*
  mostrarEquipos(){
    const promise = this.cargarResultadoService.listarTodos()
        promise
            .then((response) => response.data)
            .then((data) => this.equipos = data)           
            .catch(this.errorHandler)
  }
*/
  todosLosEquipos() {
    this.cargarResultadoService.listarEquipos()
      .then((response) => {
        this.equipos = response.data
      }, this.errorHandler)
  }

  todosLosPartidos() {
    this.cargarResultadoService.listarPartidos()
      .then((response) => {
        this.partidos = response.data
      }, this.errorHandler)
  }

  esEquipoDeGrupo(equipo, grupo) {
    return equipo.zona == grupo
  }

  actualizarResultado(partido) {
    if (partido.resultado.golesLocal != undefined && partido.resultado.golesVisitantes != undefined) {
      this.cargarResultadoService.actualizarResultado(partido)
        .then((response) => {
          this.todosLosEquipos()
        }, this.errorHandler)
    }
  }




}