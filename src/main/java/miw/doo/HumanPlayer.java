package miw.doo;

public class HumanPlayer implements Player {

    @Override
    public String getNewGuess() {
        IO io=new IO();
        io.println("Intento? [cuatro letras de entre A-amarillo, R-rojo, V-verde, Z-azul]");
        return io.getString();
    }

    @Override
    public void sendFeedback(ColorFeedback[] feedback) {
       int dead=0;
        int hit=0;
        for (ColorFeedback aFeedback : feedback) {
            if (aFeedback == ColorFeedback.HIT) {
                hit++;
            } else if (aFeedback == ColorFeedback.DEAD) {
                dead++;
            }
        }
        IO io=new IO();
        io.print("Pista: ");
        viewFeedback(feedback);
        io.println(" ("+dead+" muertos y "+hit+" heridos )");
    }
    private void viewFeedback(ColorFeedback[] feedback) {

        for (ColorFeedback aFeedback : feedback) {
            ColorFeedbackView color = new ColorFeedbackView(aFeedback);
            color.write("");
        }
    }
}
