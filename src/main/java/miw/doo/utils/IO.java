package miw.doo.utils;
import java.util.Scanner;
public class IO {
    Scanner input;

    public IO() {

        this.input = new Scanner(System.in);
    }

    public void println(String s) {
        System.out.println(s);
    }
    public void print(String s) {
        System.out.print(s);
    }
    public int getInt() {
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Valor no válido, por favor introduzca un número:");
        }
        int output = input.nextInt();
        input.nextLine();
        return output;
    }

    public String getString() {
        return input.nextLine();
    }
    public String getString(String regexp){
        boolean accept;
        String s="";
        do {
          s = getString();
           accept = s.toUpperCase().matches(regexp.toUpperCase());

          if (!accept) {
              System.out.println("El formato introducido es erróneo");

          }
      }while(!accept);

       return s;
    }

    public static void main(String[] aa){
        IO o=new IO();
        o.getString("[AZRV]{4}");
    }
}
