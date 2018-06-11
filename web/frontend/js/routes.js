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

  $urlRouterProvider.otherwise("/login");

}