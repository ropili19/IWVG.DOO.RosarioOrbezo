package miw.doo;

public class ColorView {
    Color color;
    private int ordinal;

    public ColorView(Color c){
        this.color = c;
    }

    public void write(){
        IO io = new IO();
        io.print(""+getChar(this.color));
    }

    private char getChar(Color c) {

        if (c == Color.GREEN) {
            return 'V';
        } else if (c == Color.BLUE) {
            return 'Z';
        } else if (c == Color.RED) {
            return 'R';
        } else {
            return 'A';
        }
    }
}
