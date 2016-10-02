package vc.rux.codingtest.hangman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import vc.rux.codingtest.hangman.HangmanApplication;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.repository.GameRepository;

import static org.junit.Assert.*;

/**
 * Created by rux on 29/09/16.
 */

@RunWith(SpringRunner.class)
//@SpringApplicationConfiguration(HangmanApplication.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = HangmanApplication.class)
//@WebIntegrationTest
//@WebMvcTest
@SpringApplicationConfiguration(HangmanApplication.class)
//@DataJpaTest(showSql = true)
//@WebIntegrationTest
//@ContextConfiguration(classes=HangmanApplication.class, loader=SpringApplicationContextLoader.class)
//@SpringBootTest(classes = HangmanApplication.class)

@TransactionConfiguration(defaultRollback=false)

//@Rollback(false)
//@Transactional



public class GameServiceTest {
    @Autowired
    GameService gameService;

    @Autowired
    public GameRepository gameRepository;

    @Test public void as() {
        assertEquals(0, gameRepository.count());
        Game g = gameRepository.save(new Game("q", "baz"));
        assertEquals(1, gameRepository.count());
        g = gameRepository.save(new Game("q", "baz"));
        assertEquals(1, gameRepository.count());
        g = gameRepository.save(new Game("s2", "baz"));
        assertEquals(2, gameRepository.count());
        g = gameService.getOrCreateGame("a");
    }

    @Test
    public void getOrCreateGame() throws Exception {
        final String session1 = "session1", session2 = "sessionTwo";

        Game g1 = gameService.getOrCreateGame(session1);
        assertNotNull(g1);
        Game g2 = gameService.getOrCreateGame(session1);
        assertNotNull(g2);
        assertEquals(g1, g2);

        Game g3 = gameService.getOrCreateGame(session2);
        assertNotEquals(g2, g3);

    }

    @Test
    public void testOnNewGuess() {
        final String session1 = "session1";
        Game g = gameService.getOrCreateGame(session1);
        assertEquals(0, g.getChars().length());
        assertNotNull(g.getId());
        g = gameService.onChar(session1, 'a');
        assertEquals(1, g.getChars().length());
        g = gameService.onChar(session1, 'b');
        assertEquals(2, g.getChars().length());
        g = gameService.onChar(session1, 'b');
        assertEquals(2, g.getChars().length());
    }


    @Test
    public void startNewGame() {
        final String session1 = "session1";
        Game g1 = gameService.startNewGame(session1);
        Game g2 = gameService.startNewGame(session1);
        assertNotEquals(g1, g2);
    }

}