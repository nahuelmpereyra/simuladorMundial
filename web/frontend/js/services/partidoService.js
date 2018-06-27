const partidoService = ($http) => {
    const baseurl = "http://localhost:8080/simuladorMundial_Servidor_war_exploded/"
    return {
        cargarPartido: (partido) => {
            return $http({
                method: "POST",
                url: baseurl + "agregarpartido",
                data: partido
            })
        }
    }
}