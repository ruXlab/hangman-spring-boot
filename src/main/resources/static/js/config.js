'use strict';

var app = angular.module('app', [
    'ngRoute',
]);


app.config(function AppConfig($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: "../parts/game.html",
        controller: GameController
    });
    $routeProvider.when('/statistics', {
        templateUrl: "../parts/statistics.html",
        controller: StatisticsController
    });
    $routeProvider.otherwise({
        redirectTo: "/"
    });
});




