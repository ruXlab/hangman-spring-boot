package vc.rux.codingtest.hangman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.repository.GameRepository;
import vc.rux.codingtest.hangman.repository.WordsRepository;

@Service
public class GameService {
    private GameRepository gameRepo;
    private WordsRepository wordsRepo;

    @Autowired
    public GameService(GameRepository gameRepo, WordsRepository wordsRepository) {
        this.gameRepo = gameRepo;
        this.wordsRepo = wordsRepository;
    }

    public Game getOrCreateGame(String sessionId) {
        Game game = gameRepo.findFirstBySessionIdOrderByStartedAtDesc(sessionId);
        if (game == null) {
            game = startNewGame(sessionId);
        }
        return game;
    }


    public Game startNewGame(String sessionId) {
        Game game = new Game(sessionId, wordsRepo.getRandomWord());
        return gameRepo.save(game);
    }


    public Game onChar(String sessionId, Character ch) {
        Game game = getOrCreateGame(sessionId);
        game.addChar(ch);
        return gameRepo.save(game);
    }

}
