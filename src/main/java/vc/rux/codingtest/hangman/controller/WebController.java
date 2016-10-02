package vc.rux.codingtest.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vc.rux.codingtest.hangman.service.GameService;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {
    private GameService gameService;

    @Autowired
    public WebController(GameService gameService) {
        this.gameService = gameService;
    }

    public String index() {
        return "index";
    }


    @RequestMapping("/logout")
    public String newSession(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/newgame")
    public String newGame(HttpSession session) {
        gameService.startNewGame(session.getId());
        return "redirect:/";
    }



}
