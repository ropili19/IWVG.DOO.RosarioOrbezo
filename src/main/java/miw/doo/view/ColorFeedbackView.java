package miw.doo.view;

import miw.doo.model.ColorFeedback;
import miw.doo.utils.IO;

public class ColorFeedbackView {


        private static final char[] COLORS = { 'N', 'B', '-' };

        private ColorFeedback color;

        private IO io;

        public ColorFeedbackView(ColorFeedback color) {
            this.color = color;
            io = new IO();
        }

        public void write(String title) {
            io.print(title + this.toChar());
        }

        public void writeln(String title) {
            this.write(title);
            io.println("");
        }



        private char toChar() {
            return COLORS[color.ordinal()];
        }


}
