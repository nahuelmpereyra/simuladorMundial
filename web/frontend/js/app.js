angular.module('simuladorApp', ['ui.router', 'ui.bootstrap', 'angular-growl'])
    .config(['growlProvider', function (growlProvider) {
        growlProvider.globalTimeToLive(2000);
    }])
    .factory("usuarioService", usuarioService)
    .controller('LoginController', LoginController)
    .controller('RegisterController', RegisterController)
    .controller('UsuarioController', UsuarioController)
    .config(routes)


    