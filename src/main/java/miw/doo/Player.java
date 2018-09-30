package miw.doo;

public interface Player {

    Color[] getNewGuess();

    void sendFeedback(ColorFeedback[] feedback);
}
