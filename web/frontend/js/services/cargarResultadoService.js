const cargarResultadoService = ($http, $state) => {
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
      }
    }
  
  }