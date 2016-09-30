package vc.rux.codingtest.hangman.service;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import vc.rux.codingtest.hangman.HangmanApplication;
import vc.rux.codingtest.hangman.entity.Game;
import vc.rux.codingtest.hangman.repository.GameRepository;

import static org.junit.Assert.*;

/**
 * Created by rux on 29/09/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(HangmanApplication.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest

@IntegrationTest

public class GameServiceTest {
    @Autowired
    GameService gameService;


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
    public void startNewGame() {
        final String session1 = "session1";
        Game g1 = gameService.startNewGame(session1);
        Game g2 = gameService.startNewGame(session1);
        assertNotEquals(g1, g2);
    }

}