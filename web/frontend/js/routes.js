const routes = ($stateProvider, $urlRouterProvider) => {


  $stateProvider
    .state('login', {
      url: "/login",
      templateUrl: "partials/login.html"
    })
    .state('register', {
      url: "/register",
      templateUrl: "partials/register.html"
    })
    .state('cargarEquipo', {
      url: "/cargarEquipo",
      templateUrl: "partials/cargarEquipo.html"
    })
    .state('cargarResultados', {
      url: "/cargarResultados",
      templateUrl: "partials/cargarResultados.html"
    })
    .state('cargarPartidos', {
      url: "/cargarPartidos",
      templateUrl: "partials/cargarPartidos.html"
    })

  $urlRouterProvider.otherwise("/cargarResultados");

}