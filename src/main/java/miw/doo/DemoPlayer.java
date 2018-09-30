package miw.doo;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DemoPlayer  implements Player{

    private Random random;
    private String lastGuess;
    private Set<Character> candidates[];
    private char[] success;
    //TODO: Cosas del tablero... pasar como parametro
    private static final int SECRET_SIZE = 4;

    public DemoPlayer( Random random){
        this.random = random;

        candidates = new Set[SECRET_SIZE];
        success = new char[SECRET_SIZE];
        for (int i = 0; i < SECRET_SIZE; i++) {
            success[i] = '_';
            candidates[i] = new HashSet<Character>();
            String colors = "ARVZ";//TODO
            for (int j = 0; j < colors.length(); j++) {
                candidates[i].add(colors.charAt(j));
            }
        }
    }

    @Override
    public String getNewGuess() {
        IO io=new IO();
        io.println("Intento? [cuatro letras de entre A-amarillo, R-rojo, V-verde, Z-azul]");
        this.lastGuess = "";
        for (int i = 0; i < SECRET_SIZE; i++) {
            if (success[i] != '_') {
                this.lastGuess += success[i];
            } else {
                this.lastGuess += newCharacterGuess(i);
            }
        }
        io.println("La máquina insertará "+lastGuess);
        return lastGuess;
    }

    private char newCharacterGuess(int pos) {
        Character[] arraycandidates = this.candidates[pos].toArray(new Character[0]);
        return arraycandidates[random.nextInt(arraycandidates.length)];
    }

    @Override
    public void sendFeedback(ColorFeedback[] feedback) {
        IO io=new IO();
        for (int i = 0; i < feedback.length; i++) {
            ColorFeedback c = feedback[i];
            if(c == ColorFeedback.DEAD) { //muertos
                success[i]=lastGuess.charAt(i);
            } else if(c ==ColorFeedback.NONE) {//ninguno
                for (Set<Character> candidate : candidates) {
                    candidate.remove(lastGuess.charAt(i));
                }
            }else {
                candidates[i].remove(lastGuess.charAt(i));
            }
        }
        viewScore(feedback);
        io.println("Pulse ENTER para continuar");
        io.getString();

    }
    private void viewScore(ColorFeedback[] feedback){
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
