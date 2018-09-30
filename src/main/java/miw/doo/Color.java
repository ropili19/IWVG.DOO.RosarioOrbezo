package miw.doo;

public enum Color {
        RED,
        GREEN,
        BLUE,
        YELLOW;

    public static Color getColor(char character){
        switch (character){
            case 'R': return RED;
            case 'Z': return BLUE;
            case 'A' : return YELLOW;
            case 'V' : return GREEN;
        }
        return null;
    }

}
