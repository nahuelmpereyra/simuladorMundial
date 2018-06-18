class CargarResultadosController {

  constructor(cargarResultadoService, growl) {
    this.grupos = ["A", "B", "C", "D", "E", "F", "G", "H"]
    this.cargarResultadoService = cargarResultadoService
    this.growl = growl
    this.mostrarEquipos()
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
  mostrarEquipos() {
    for (const grupo of this.grupos) {
      this.cargarResultadoService.listarPorGrupo(grupo)
        .then((response) => {
          if (grupo == "A") {
            this.A = response.data
          }
          if (grupo == "B") {
            this.B = response.data
          }
          if (grupo == "C") {
            this.C = response.data
          }
          if (grupo == "D") {
            this.D = response.data
          }
          if (grupo == "E") {
            this.E = response.data
          }
          if (grupo == "F") {
            this.F = response.data
          }
          if (grupo == "G") {
            this.G = response.data
          }
          if (grupo == "H") {
            this.H = response.data
          }
        }, this.errorHandler)
    }


    // this.cargarResultadoService.listarPorGrupo("A")
    // .then((response) => {
    //   this.equiposA = response.data
    // }, this.errorHandler)
    // this.cargarResultadoService.listarPorGrupo("D")
    // .then((response) => {
    //   this.equiposD = response.data
    // }, this.errorHandler)
  }

  getGrupo(grupo) {
    if (grupo == "A") {
      return this.A
    }
    if (grupo == "B") {
      return this.B
    }
    if (grupo == "C") {
      return this.C
    }
    if (grupo == "D") {
      return this.D
    }
    if (grupo == "E") {
      return this.E
    }
    if (grupo == "F") {
      return this.F
    }
    if (grupo == "G") {
      return this.G
    }
    if (grupo == "H") {
      return this.H
    }
  }




}