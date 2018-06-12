const cargarEquipoService = ($http) => {
    const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
    return {
      cargarEquipo: (equipo) => {
        return $http({
          method: "POST",
          url: baseurl + "agregarequipo",
          data: equipo
        })
      }
    }
  }