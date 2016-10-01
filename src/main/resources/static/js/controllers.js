'use strict';

var app = angular.module('app', []);




function GameController($scope, $http) {
    $scope.alphabet = Array.apply(null, {length: 26})
        .map((x,i) => String.fromCharCode(97 + i) )
        .map(char => { return {char, enabled: true}});


    function attachState(state) {

    }

    function updateGameState() {
        $http
            .get("/data/game")
            .then( (resp) => resp.data, (err) => err )
            .then( (state) => {
                console.log(state)
                attachState(state)
            })
        // return {
        //     gameState: () =>
        // }

    }

    function sendGuess(char) {
        action: (char) => $http
            .post(`${prefix}/guess`, {character: char} )
            .then( (resp) => resp.data, (err) => err )
            .then( attachState)

    }

    updateGameState()

}