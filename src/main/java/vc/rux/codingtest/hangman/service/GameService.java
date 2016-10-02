package vc.rux.codingtest.hangman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vc.rux.codingtest.hangman.HangmanApplication;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.misc.Utils;
import vc.rux.codingtest.hangman.repository.GameRepository;
import vc.rux.codingtest.hangman.repository.WordsRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


        List<Character> word = Utils.stringToList(game.getWord());
        List<Character> guesses = Utils.stringToList(game.getChars());
        List<Character> wrong = guesses
                .stream()
                .filter(c -> !word.contains(c))
                .collect(Collectors.toList());

        boolean won = word
                .stream()
                .filter(c -> !guesses.contains(c))
                .count() == 0;
        boolean exceedAttempts = wrong.size() >= HangmanApplication.MAX_ATTEMPTS;

        if (won || exceedAttempts) {
            game.setFinishedAt(new Date());
            game.setWon(won);
        }

        return gameRepository.save(game);
    }

    public Iterable<Game> allGames() {
        return gameRepository.findAll();
    }
}
