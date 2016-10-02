package vc.rux.codingtest.hangman.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rux on 02/10/16.
 */
public class Utils {
    public static final List<Character> stringToList(String s) {
        char[] chars = s.toCharArray();
        ArrayList<Character> characters = new ArrayList<Character>(chars.length);
        for (char aChar : chars) {
            characters.add(aChar);
        }
        return characters;
    }
}
