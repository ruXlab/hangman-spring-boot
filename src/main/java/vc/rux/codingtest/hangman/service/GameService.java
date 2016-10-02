package vc.rux.codingtest.hangman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.repository.GameRepository;
import vc.rux.codingtest.hangman.repository.WordsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    private GameRepository gameRepository;
    private WordsRepository wordsRepository;

    @Autowired
    public GameService(GameRepository gameRepository, WordsRepository wordsRepository) {
        this.gameRepository = gameRepository;
        this.wordsRepository = wordsRepository;
    }

    public Game getOrCreateGame(String sessionId) {
        Game game = gameRepository.findFirstBySessionIdOrderByStartedAtDesc(sessionId);
        if (game == null) {
            game = startNewGame(sessionId);
        }
        return game;
    }


    public Game startNewGame(String sessionId) {
        Game game = new Game(sessionId, wordsRepository.getRandomWord());
        return gameRepository.save(game);
    }


    public Game onChar(String sessionId, Character ch) {
        Game game = getOrCreateGame(sessionId);
        game.addChar(ch);
        return gameRepository.save(game);
    }

    public Iterable<Game> allGames() {
        return gameRepository.findAll();
    }
}
