import gameSetup.Game;
import gameSetup.Language;

public class Main {
    public static void main(String[] args) {
        new Game(Language.GERMAN, 2, 0).start();
    }
}