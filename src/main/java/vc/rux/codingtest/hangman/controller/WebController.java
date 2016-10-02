package vc.rux.codingtest.hangman.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import vc.rux.codingtest.hangman.service.GameService;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    protected GameService gameService;

    public String index() {
        return "index";
    }


    @RequestMapping("/logout")
    public String newSession(HttpSession session, HttpResponse response) {
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/newgame")
    public String newGame(HttpSession session, HttpResponse response) {
        gameService.startNewGame(session.getId());
        return "redirect:/";
    }



}
