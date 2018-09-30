package miw.doo.model;

import miw.doo.utils.IO;
import miw.doo.view.ColorFeedbackView;
import miw.doo.view.ColorView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DemoPlayer  implements Player {

    private Random random;
    private Color[] lastGuess;
    private Set<Color> candidates[];
    private Color[] success;

    private static final int SECRET_SIZE = Board.SECRET_SIZE;

    public DemoPlayer( Random random){
        this.random = random;
        lastGuess = new Color[SECRET_SIZE];
        candidates = new Set[SECRET_SIZE];
        success = new Color[SECRET_SIZE];
        for (int i = 0; i < SECRET_SIZE; i++) {
           // success[i] = '_';
            candidates[i] = new HashSet<Color>();
            Color[] colors = Color.values();
            for (int j = 0; j < colors.length; j++) {
                candidates[i].add(colors[j]);
            }
        }
    }


    public Color[] getNewGuess() {
        IO io=new IO();
        io.println("Intento? [cuatro letras de entre A-amarillo, R-rojo, V-verde, Z-azul]");

        for (int i = 0; i < SECRET_SIZE; i++) {
            if (success[i] != null) {
                this.lastGuess[i] = success[i];
            } else {
                this.lastGuess[i] = newCharacterGuess(i);
            }
        }
        io.print("La máquina insertará ");
        viewColor(lastGuess);
        io.println(" ");
        return lastGuess;
    }

    private void viewColor(Color[] lastGuess) {
        for (Color c : lastGuess) {
            new ColorView(c).write();
        }
    }

    private Color newCharacterGuess(int pos) {
        Color[] arraycandidates = this.candidates[pos].toArray(new Color[0]);
        return arraycandidates[random.nextInt(arraycandidates.length)];
    }


    public void sendFeedback(ColorFeedback[] feedback) {
        IO io=new IO();
        for (int i = 0; i < feedback.length; i++) {
            ColorFeedback c = feedback[i];
            if(c == ColorFeedback.DEAD) { //muertos
                success[i]=lastGuess[i];
            } else if(c ==ColorFeedback.NONE) {//ninguno
                for (Set<Color> candidate : candidates) {
                    candidate.remove(lastGuess[i]);
                }
            }else {
                candidates[i].remove(lastGuess[i]);
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
