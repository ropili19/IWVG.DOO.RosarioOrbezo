package miw.doo;

import java.util.Random;

public class Board {
    private static final int SECRET_SIZE = 4;
    private String colors = "ARVZ";
    private int numMaxIntentos;
    private int turno;
    private String secret;

    private Boolean isWinner = false;
    private Random random;

    public Board(int numMaxIntentos, Random random) {
        this.numMaxIntentos = numMaxIntentos;
        this.turno = 0;
        this.random = random;
        this.secret = initSecret();

    }
    
    public String initSecret() {
        StringBuilder secret = new StringBuilder();
        for (int i = 0; i < SECRET_SIZE; i++) {
            secret.append(colors.charAt(random.nextInt(colors.length())));
        }
        return secret.toString();
    }

    public ColorFeedback[] makeGuess(String guess) {
        assert guess != null;
        IO io=new IO();
        ColorFeedback[] feedback = null;
        guess = guess.toUpperCase();
        if (verifyGuess(guess)) {
            feedback = gradeGuess(guess);
            isWinner=isWinner(feedback);
            turno++;
        } else {
            io.println("No se ha insertado el movimiento");
        }
        return feedback;
    }

    private Boolean isWinner(ColorFeedback[] feedback) {
        for (ColorFeedback aFeedback : feedback) {
            if (aFeedback != ColorFeedback.DEAD) {
                return false;
            }
        }
        return true;

    }


    private ColorFeedback[] gradeGuess(String guess) {
        ColorFeedback[] feedbacks=new ColorFeedback[SECRET_SIZE];
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == secret.charAt(i)) {
                feedbacks[i] = ColorFeedback.DEAD;
            } else if (secret.indexOf(guess.charAt(i)) >= 0) {

                feedbacks[i] = ColorFeedback.HIT;
            } else {
                feedbacks[i] = ColorFeedback.NONE;
            }
        }
        return feedbacks;
    }

    private boolean verifyGuess(String guess) {
        IO io=new IO();
        if (guess.length() != SECRET_SIZE) {
            io.println("No ha introducido el número de fichas correcto.");

            return false;
        }
        for (int i = 0; i < SECRET_SIZE; i++) {
            if (colors.indexOf(guess.charAt(i)) < 0) {
                io.println(String.format("Ha marcado un color no válido <%s> en la posición %d.", guess.charAt(i), i + 1));
                return false;
            }
        }
        return true;
    }

    public Boolean isFinished() {
        return (this.turno >= numMaxIntentos || isWinner());
    }

    public Boolean isWinner() {
        return this.isWinner;
    }


}
