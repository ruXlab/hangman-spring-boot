package vc.rux.codingtest.hangman.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    public String index() {
        return "index";
    }


    @RequestMapping("/logout")
    public String newSession(HttpSession session, HttpResponse response) {
        session.invalidate();
        return "redirect:/";
    }



}
