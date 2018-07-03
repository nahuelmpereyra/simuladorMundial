const resultadoService = ($http, $state) => {
  this.state = $state
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
    listarPartidos: (grupo) => {
      return $http({
        method: "GET",
        url: baseurl + "partidos"
      })
    },
    actualizarResultado: (partido) => {
      return $http({
        method: "POST",
        url: baseurl + "partidos/" + partido.id,
        data: partido.resultado
      })
    },
    buscar: (busqueda) => {
      return $http({
        method: "GET",
        url: baseurl + "equipos/nombre=" + busqueda
      })
    },
    elegirGanador: (llave, equipo) => {
      return $http({
        method: "PUT",
        url: baseurl + "llaves/" + llave.id,
        data: equipo
      })
    },
    listarLlaves: () => {
      return $http({
        method: "GET",
        url: baseurl + "llaves"
      })
    },
    armarLlaves: () => {
      return $http({
        method: "POST",
        url: baseurl + "llaves"
      })
    },
    listarLlavesCuartos: () => {
      return $http({
        method: "GET",
        url: baseurl + "llavescuartos"
      })
    },
    listarLlavesSemi: () => {
      return $http({
        method: "GET",
        url: baseurl + "llavessemi"
      })
    },
    listarLlavesFinal: () => {
      return $http({
        method: "GET",
        url: baseurl + "llavesfinal"
      })
    }
  }

}