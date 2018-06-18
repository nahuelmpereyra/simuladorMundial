class CargarResultadosController {

  constructor(cargarResultadoService, growl) {
    this.equipos = []
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
  mostrarEquipos(){

    this.cargarResultadoService.listarTodos()
    .then((response) => {
      this.equipos = response.data
      console.log(response.data)
    }, this.errorHandler)
  }



}