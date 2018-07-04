const equipoService = ($http) => {
  const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
  return {
    listarEquipos: () => {
      return $http({
        method: "GET",
        url: baseurl + "equipos"
      })
    },
    listarEquiposPorGrupo: (grupo) => {
      return $http({
        method: "GET",
        url: baseurl + "equipos/" + grupo
      })
    },
    buscar: (busqueda) => {
      return $http({
        method: "GET",
        url: baseurl + "equipos/nombre=" + busqueda
      })
    },
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