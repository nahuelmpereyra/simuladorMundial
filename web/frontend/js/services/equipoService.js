const equipoService = ($http) => {
    const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
    return {
      cargarEquipo: (equipo) => {
        return $http({
          method: "POST",
          url: baseurl + "agregarequipo",
          data: equipo
        })
      },
      eliminarEquipo: (equipo) => {
        return $http({
          method: "POST",
          url: baseurl + "eliminarequipo",
          data: equipo
        })
      },
      editarEquipo: (equipoAEditar) => {
        return $http({
          method: "PUT",
          url: baseurl + "editarequipo",
          data: equipoAEditar
        })
      }
    }
  }