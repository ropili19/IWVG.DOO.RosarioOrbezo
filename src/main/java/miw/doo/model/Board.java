package miw.doo.model;

import miw.doo.utils.IO;

import java.util.Random;

public class Board {
    public static final int SECRET_SIZE = 4;
    private int numMaxIntentos;
    private int turno;
    private Color[] secret;

    private Boolean isWinner = false;
    private Random random;

    public Board(int numMaxIntentos, Random random) {
        this.numMaxIntentos = numMaxIntentos;
        this.turno = 0;
        this.random = random;
        this.secret = initSecret();

    }

    public Color[] initSecret() {
        Color[] secret = new Color[SECRET_SIZE];
        Color[] tokens = Color.values();
        for (int i = 0; i < SECRET_SIZE; i++) {
            secret[i]= tokens[random.nextInt(tokens.length)];
        }
        return secret;
    }

    public ColorFeedback[] makeGuess(Color[] guess) {
        assert guess != null;
        IO io = new IO();
        ColorFeedback[] feedback = null;

        feedback = gradeGuess(guess);
        isWinner = isWinner(feedback);
        turno++;

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


    private ColorFeedback[] gradeGuess(Color[] guess) {
        ColorFeedback[] feedbacks = new ColorFeedback[SECRET_SIZE];
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == secret[i]) {
                feedbacks[i] = ColorFeedback.DEAD;
            } else if (isHit(secret, guess[i])) {
                feedbacks[i] = ColorFeedback.HIT;
            } else {
                feedbacks[i] = ColorFeedback.NONE;
            }
        }
        return feedbacks;
    }

    private boolean isHit(Color[] secret, Color guess) {
        for (Color color : secret) {
            if(color==guess)
                return true;
        }
        return false;
    }


    public Boolean isFinished() {
        return (this.turno >= numMaxIntentos || isWinner());
    }

    public Boolean isWinner() {
        return this.isWinner;
    }


}
