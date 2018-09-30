package miw.doo;

public class ColorFeedbackView {


        private static final char[] COLORS = { 'N', 'B', '-' };

        private ColorFeedback color;

        private IO io;

        ColorFeedbackView(ColorFeedback color) {
            this.color = color;
            io = new IO();
        }

        void write(String title) {
            io.print(title + this.toChar());
        }

        void writeln(String title) {
            this.write(title);
            io.println("");
        }



        private char toChar() {
            return COLORS[color.ordinal()];
        }


}
