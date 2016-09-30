package vc.rux.codingtest.hangman.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordsRepository {
    public String getRandomWord() {
        return words[(int)(Math.random()*10000) % words.length];
    }

    private static final String[] words = new String[] {
            "boo",
            "bar",
            "foo"

    };
}
