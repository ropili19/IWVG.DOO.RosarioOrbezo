package miw.doo.model;


public interface Player {

    Color[] getNewGuess();

    void sendFeedback(ColorFeedback[] feedback);
}
