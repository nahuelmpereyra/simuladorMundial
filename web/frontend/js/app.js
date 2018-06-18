angular.module('simuladorApp', ['ui.router', 'ui.bootstrap', 'angular-growl'])
    .config(['growlProvider', function (growlProvider) {
        growlProvider.globalTimeToLive(2000);
    }])
    .factory("usuarioService", usuarioService)
    .factory("cargarEquipoService", cargarEquipoService)
    .factory("cargarResultadoService", cargarResultadoService)
    .controller('LoginController', LoginController)
    .controller('RegisterController', RegisterController)
    .controller('UsuarioController', UsuarioController)
    .controller('CargarEquipoController', CargarEquipoController)
    .controller('CargarResultadosController', CargarResultadosController)
    .config(routes)