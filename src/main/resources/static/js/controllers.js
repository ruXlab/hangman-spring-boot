'use strict';

var app = angular.module('app', []);




function GameController($scope, $http) {
    $scope.alphabet = {}
    $scope.word = ""
    Array.apply(null, {length: 26})
        .map((x,i) => String.fromCharCode(97 + i) )
        .forEach(ch => $scope.alphabet[ch] = true)

    $scope.onGuess = (char) => {
        sendGuess(char)
    }


    function attachState(state) {
        console.log(state)
        $scope.word = state.word;
        state.guesses.forEach((ch) => {
            $scope.alphabet[ch] = false;
        })

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
        $http
            .post("/data/guess", {character: char} )
            .then( (resp) => resp.data, (err) => err )
            .then( attachState)

    }

    updateGameState()

}