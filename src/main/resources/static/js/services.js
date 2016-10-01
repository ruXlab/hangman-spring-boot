'use strict';
var app = angular.module('app', []);

app.factory('API', function ($http) {
    const prefix = '/data';
    return {
        gameState: () => $http
            .get(`${prefix}/game`)
            .then( (resp) => resp.data, (err) => err ),
        action: (char) => $http
            .post(`${prefix}/guess`, {character: char} )
            .then( (resp) => resp.data, (err) => err )
    }

})
