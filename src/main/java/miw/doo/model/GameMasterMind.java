package miw.doo.model;

import miw.doo.utils.IO;

import java.util.Random;


public class GameMasterMind {
    private Random random;

    public GameMasterMind(Random random) {
        this.random=random;

    }
    public void play(Player player) {
        IO io=new IO();
        io.println("Secreto:****");
        Board board = new Board(4, random);
        while (!board.isFinished()) {
            Color[] guess = player.getNewGuess();
            ColorFeedback[] feedback = board.makeGuess(guess);
            if (!board.isWinner() && feedback!=null) {
                player.sendFeedback(feedback);
            }
        }
        if (board.isWinner()) {
            io.println("You Win!!");
        } else {
            io.println("You Lose.. ='( ");
        }
        io.println("Pulse ENTER para continuar: ");
        io.getString();
    }


}
