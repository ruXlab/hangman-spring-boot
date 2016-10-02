package vc.rux.codingtest.hangman.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vc.rux.codingtest.hangman.HangmanApplication;
import vc.rux.codingtest.hangman.entity.Game;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepository;

    @Test
    public void findOneBySessionIdOrderByStartedAt() throws Exception {
        assertEquals(0, gameRepository.count());
        Game g = gameRepository.save(new Game("sess1", "apple"));
        assertNotNull(g);
//        assertNotNull(g.getId());
        gameRepository.save(new Game("sess1", "strawberry"));
        assertEquals(2, gameRepository.count());

        g = gameRepository.findFirstBySessionIdOrderByStartedAtDesc("sess1");
        assertEquals("strawberry", g.getWord());
    }



}