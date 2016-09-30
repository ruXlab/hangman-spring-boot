package vc.rux.codingtest.hangman.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import vc.rux.codingtest.hangman.entity.Game;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    @Test
    public void findOneBySessionIdOrderByStartedAt() throws Exception {
        assertEquals(0, gameRepository.count());
        gameRepository.save(new Game("sess1", "apple"));
        gameRepository.save(new Game("sess1", "strawberry"));
        assertEquals(2, gameRepository.count());

        Game g = gameRepository.findFirstBySessionIdOrderByStartedAtDesc("sess1");
        assertEquals("strawberry", g.getWord());


    }

}