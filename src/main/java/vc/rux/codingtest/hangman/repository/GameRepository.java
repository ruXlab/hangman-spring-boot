package vc.rux.codingtest.hangman.repository;

import org.springframework.data.repository.CrudRepository;
import vc.rux.codingtest.hangman.entity.Game;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {
    public Game findFirstBySessionIdOrderByStartedAtDesc(String sessionId);
    public List<Game> findBySessionIdOrderByStartedAt(String sessionId);
}
