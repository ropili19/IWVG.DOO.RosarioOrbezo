package miw.doo;
import miw.doo.model.DemoPlayer;
import miw.doo.model.GameMasterMind;
import miw.doo.model.HumanPlayer;
import miw.doo.utils.IO;

import java.util.Random;
public class MainMenu {

    private static Random random;
    private static String DIALOG_MENU = "Master Mind (demo)\n\n" +
            "1.-Partida\n"
            + "2.-Demo\n" +
            "Opción?: (Pulse 0 para salir)";

    public static void main(String[] args) {
        random = new Random(0);
        GameMasterMind game = new GameMasterMind(random);
        int option = -1;
        do {
            option = getMenuOption();
            switch (option) {
                case 1:
                game.play(new HumanPlayer());
                break;
                case 2:
                game.play(new DemoPlayer(random));
                break;
                default:
            }
        } while (option != 0);
    }

    private static int getMenuOption() {
        IO io=new IO();
        io.println(DIALOG_MENU);
        return io.getInt();
    }


}
