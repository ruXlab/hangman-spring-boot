package vc.rux.codingtest.hangman.entity;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(
    indexes = {
        @Index(name = "idxSessionId", columnList = "sessionId", unique = false)
    }
)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String chars = "";

    @Column(nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private Date startedAt = new Date();

    @Column(nullable = true)
    private Date finishedAt;

    @Column(nullable = true)
    private Boolean won;


    public Game() {}

    public Game(String sessionId, String word) {
        this.sessionId = sessionId;
        this.word = word;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    public void addChar(char ch) {
        HashSet<Character> charSet = new HashSet<Character>() {{
            for(char ch: chars.toCharArray()) add(ch);
            add(ch);
        }};
        setChars(charSet.stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Boolean getWon() {
        return won;
    }

    public void setWon(Boolean won) {
        this.won = won;
    }

    public boolean isFinished() {
        return getFinishedAt() != null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        if (word != null ? !word.equals(game.word) : game.word != null) return false;
        if (chars != null ? !chars.equals(game.chars) : game.chars != null) return false;
        if (sessionId != null ? !sessionId.equals(game.sessionId) : game.sessionId != null) return false;
        if (startedAt != null ? !startedAt.equals(game.startedAt) : game.startedAt != null) return false;
        return true;

    }

    @Override
    public int hashCode() {
        int result = 31 + (word != null ? word.hashCode() : 0);
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        result = 31 * result + (startedAt != null ? startedAt.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", chars='" + chars + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", startedAt=" + startedAt +
                '}';
    }
}