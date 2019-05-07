package lab8;

public class GameOverException extends Exception {
    GameOverException(){
        super("New levels on the way :)\n" +
                "P.S. money will speed up development...");
    }

}
