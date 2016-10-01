package vc.rux.codingtest.hangman.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.repository.GameRepository;
import vc.rux.codingtest.hangman.service.GameService;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/data")
public class RestController {

    private GameRepository gameRepository;
    private GameService gameService;

    @Autowired
    public RestController(GameRepository gameRepository, GameService gameService) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
    }



    @RequestMapping(value = "/boo", method = RequestMethod.GET)
    public String gameStatea(HttpSession session) {
        Integer i = (Integer)session.getAttribute("i");
        if (i == null) i = 0;
        session.setAttribute("i", i+ 1);

        gameRepository.save(new Game(session.getId(), ""+ Math.random()));

        return "boooo "  + session.getId() + " i = " + i + " Count" + gameRepository.count();
    }


    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public GameState gameState(HttpSession session) {
        gameRepository.save(new Game(session.getId(), ""+ Math.random()));
        Game game = gameService.getOrCreateGame(session.getId());

        return new GameState(game);
    }


    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public GameState guess(HttpSession session, @RequestBody NewGuessRequest req) {
        Game game = gameService.onChar(session.getId(), req.character);

        return new GameState(game);
    }

    static final class NewGuessRequest {
        final char character;
        NewGuessRequest(char character) {
            this.character = character;
        }
    }

    /**
     * Game State DTO
     */
    static final class GameState {
        private Set<Character> wrong = new HashSet<>();
        private String word = "";
        private Long gameId;

        public GameState(Game game) {
            gameId = game.getId();
            Set<Character> correct = new HashSet<>();
            for (char ch : game.getChars().toCharArray()) {
                if (!game.getWord().contains(""+ch)) {
                    wrong.add(ch);
                } else {
                    correct.add(ch);
                }
            }

            word = game.getWord()
                    .chars()
                    .map(c -> correct.contains((char)c) ? (char)c : '_')
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        public Set<Character> getWrong() {
            return wrong;
        }

        public String getWord() {
            return word;
        }

        public Long getGameId() {
            return gameId;
        }
    }



}
