package vc.rux.codingtest.hangman.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordsRepository {
    public String getRandomWord() {
        return words[(int)(Math.random()*10000) % words.length];
    }

    private static final String[] words = new String[] {
            "angels",
            "announcement",
            "bells",
            "bethlehem",
            "blitzen",
            "candles",
            "candy",
            "candy canes",
            "cards",
            "advent",
            "cedar",
            "celebrate",
            "ceremonies",
            "chimney",
            "cookies",
            "tree",
            "cold",
            "comet",
            "sauce",
            "crowds",
            "cupid",
            "dancer",
            "dasher",
            "december",
            "decorations",
            "dolls",
            "donner",
            "dressing",
            "eggnog",
            "elves",
            "reunion",
            "festival",
            "fir",
            "frosty",
            "fruitcake",
            "boxes",
            "gifts",
            "goodwill",
            "greetings",
            "ham",
            "happy",
            "holiday",
            "holly",
            "holy",
            "icicles",
            "jolly",
            "lights",
            "lists",
            "merry",
            "miracle",
            "mistletoe",
            "new year",
            "noel",
            "pole",
            "pageant",
            "parades",
            "party",
            "pie",
            "pine",
            "pudding",
            "poinsettia",
            "prancer",
            "presents",
            "pumpkin",
            "punch",
            "reindeer",
            "ribbon",
            "rudolph",
            "sacred",
            "sales",
            "sauce",
            "scrooge",
            "season",
            "sled",
            "bells",
            "snowflakes",
            "spirit",
            "stand",
            "star",
            "stickers",
            "stuffers",
            "potato",
            "tidings",
            "tinsel",
            "togetherness",
            "toys",
            "tradition",
            "traffic",
            "trips",
            "turkey",
            "vacation",
            "vixen",
            "winter",
            "worship",
            "paper",
            "wreath",
            "yule",
            "yuletide"
    };
}
