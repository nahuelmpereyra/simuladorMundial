  const usuarioService = ($http) => {
    const baseurl = "http://localhost:9200/"
    return {
      login: (usuario) => {
        return $http({
          method: "POST",
          url: baseurl + "login",
          data: usuario
        })
      },
      register: (usuario) => {
        return $http({
          method: "POST",
          url: baseurl + "usuarios",
          data: usuario
        })
      },
      edit: (usuario) => {
        return $http({
          method: "PUT",
          url: baseurl + "usuarios/" +usuario.username,
          data: usuario
        })
      }
    }
  }
  