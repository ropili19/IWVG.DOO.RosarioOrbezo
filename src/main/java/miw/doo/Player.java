package miw.doo;

public interface Player {

    String getNewGuess();

    void sendFeedback(ColorFeedback[] feedback);
}
