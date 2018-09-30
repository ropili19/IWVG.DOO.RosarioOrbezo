package miw.doo;

public class HumanPlayer implements Player {


    public Color[] getNewGuess() {
        IO io=new IO();
        Color[] colors = null;
        while(colors==null) {
            io.println("Intento? [cuatro letras de entre A-amarillo, R-rojo, V-verde, Z-azul]");
            String input = io.getString().toUpperCase();
            colors = decodeString(input);
        }
        return colors;
    }


    private Color[] decodeString(String response) {
        IO io = new IO();
        if(response.length()!=Board.SECRET_SIZE){
            io.println("El número de caracteres no es correcto.");
            return null;
        }
        Color[] colors=new Color[Board.SECRET_SIZE];
        for (int i = 0; i < colors.length ; i++) {
            colors[i]=Color.getColor(response.charAt(i));
            if(colors[i]==null) {

                io.println(String.format("Ha marcado un color no válido <%s> en la posición %d.", response.charAt(i), i + 1));
                return null;
            }
        }
        return colors;
    }


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
