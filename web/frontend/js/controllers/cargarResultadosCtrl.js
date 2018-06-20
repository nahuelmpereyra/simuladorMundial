class CargarResultadosController {

  constructor(cargarResultadoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.cargarResultadoService = cargarResultadoService
    this.growl = growl
    this.todosLosEquipos()
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
    this.cargarResultadoService.listarTodos()
      .then((response) => {
        this.equipos = response.data
      }, this.errorHandler)
  }

  esDelGrupo(equipo, grupo) {
    return equipo.zona == grupo
  }


}