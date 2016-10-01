'use strict';

var app = angular.module('app', [
    'ngRoute',
]);


app.config(function AppConfig($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: "../parts/game.html",
        controller: GameController
    });
    $routeProvider.otherwise({
        redirectTo: "/"
    });
});




// app.run(function ($rootScope, $routeParams) {
// });


