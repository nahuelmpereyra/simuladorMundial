const resultadoService = ($http, $state) => {
  this.state = $state
  const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
  return {
    actualizarResultado: (partido) => {
      return $http({
        method: "POST",
        url: baseurl + "partidos/" + partido.id,
        data: partido.resultado
      })
    },
    elegirGanador: (llave, equipo) => {
      return $http({
        method: "PUT",
        url: baseurl + "llaves/" + llave.id,
        data: equipo
      })
    },
    armarLlaves: () => {
      return $http({
        method: "POST",
        url: baseurl + "llaves"
      })
    },
    hayLlaves: () => {
      return $http({
        method: "GET",
        url: baseurl + "hayllaves"
      })
    },
    listarLlavesOctavos: () => {
      return $http({
        method: "GET",
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