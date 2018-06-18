const cargarResultadoService = ($http, $state) => {
    this.state = $state
    const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
    return {
      listarTodos: () => {
        return $http({
          method: "GET",
          url: baseurl + "equipos"
        })
      }
    }
  
  }