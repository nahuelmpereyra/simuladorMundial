const partidoService = ($http) => {
    const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
    return {
        listarPartidos: (grupo) => {
            return $http({
                method: "GET",
                url: baseurl + "partidos"
            })
        },
        cargarPartido: (partido) => {
            return $http({
                method: "POST",
                url: baseurl + "agregarpartido",
                data: partido
            })
        },
        eliminarPartido: (partido) => {
            return $http({
                method: "DELETE",
                url: baseurl + "eliminarpartido/" + partido.id
            })
        }
    }
}