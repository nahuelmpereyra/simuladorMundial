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

  $urlRouterProvider.otherwise("/login");

}