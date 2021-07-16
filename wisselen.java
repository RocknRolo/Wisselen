import java.util.Random;
import java.util.Scanner;

public class wisselen {

    private static String formatCash(String str) {
        if (str.length() == 1) return "0,0" + str;
        if (str.length() == 2) return "0," + str;
        if (str.length() == 3) {
            return str.substring(0, 1) + "," + str.substring(1);
        }
        if (str.length() >= 4) {
            return str.substring(0, str.length() - 2)+
                    "," + str.substring(str.length() - 2);
        } else {
            return "";
        }
    }

    private static String formatTime(String tmp) {
        if (tmp.length() >= 4) {
            return tmp.substring(0, tmp.length()-3) + "," +
                    tmp.substring(tmp.length()-3, tmp.length()-1);
        }
        if (tmp.length() == 3) {
            return "0," + tmp.substring(0, 2);
        }
        if (tmp.length() == 2) {
            return "0,0" + tmp.substring(0, 1);
        } else {
            return "0,00" + tmp;
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);

        int[] payArray = new int[] {500, 1000, 2000, 5000, 10000};
        int payKeuze;
        int betaaltInt;
        int kooptInt;
        int krijgtInt;
        int krijgtRondInt;
        int ki;
        int ronde = 0;

        String[] goedOfFout = new String[10];
        String[] tijd = new String[10];
        String betaalt;
        String koopt;
        String krijgt;
        String krijgtRond;
        String antwoord;

        long startTime;
        long tijdsDuur = 0;

        boolean correct;

        for (int i = 0; i < 10; i++) { // start of loop
            payKeuze = r.nextInt(5);
            betaaltInt = payArray[payKeuze];
            kooptInt = r.nextInt(betaaltInt);
            krijgtInt = betaaltInt - kooptInt;

            krijgtRondInt = krijgtInt + (5 - (krijgtInt % 5));
            if (krijgtInt % 5 <= 2) krijgtRondInt = krijgtInt - krijgtInt % 5;

            betaalt = formatCash(Integer.toString(betaaltInt));
            koopt = formatCash(Integer.toString(kooptInt));
            krijgt = formatCash(Integer.toString(krijgtInt));
            krijgtRond = formatCash(Integer.toString(krijgtRondInt));
            ronde = i+1;

            System.out.println("");
            if (i < 9) System.out.println("     ------ " + ronde + " ------");
            if (i == 9)System.out.println("    ------ " + "1 0" + " ------");
            System.out.println("");
            System.out.println("Klant geeft : € " + betaalt);
            System.out.println("Te betalen  : € " + koopt);
            System.out.print("Wisselgeld  : € ");

            startTime = System.currentTimeMillis();

            antwoord = scanner.nextLine();

            tijdsDuur = (System.currentTimeMillis() - startTime);

            ki = krijgt.indexOf(',');

            correct = antwoord.length() >= 4 &&
                    ((antwoord.equals(krijgt) || antwoord.charAt(ki) == '.' &&
                    antwoord.substring(ki+1).equals(krijgt.substring(ki+1)))) ||
                    (antwoord.equals(krijgtRond) || antwoord.charAt(ki) == '.' &&
                    antwoord.substring(ki+1).equals(krijgtRond.substring(ki+1)));

            if (correct) {
                System.out.println("");
                System.out.println("          Goed!");
                System.out.println("");
                tijd[i] = formatTime(Long.toString(tijdsDuur));
                goedOfFout[i] = "GOED";
            }
            if (!correct) {
                System.out.println("");
                System.out.println("          Fout!");
                System.out.println("");
                System.out.println("Het goede antwoord was : ");
                System.out.println("");
                System.out.print("  € " + krijgt);
                if (krijgtInt == krijgtRondInt) {
                    System.out.println("");
                    System.out.println("");
                }
                if (krijgtInt != krijgtRondInt) {
                    System.out.println(" (of € " + krijgtRond + ")");
                    System.out.println("");
                }
                tijd[i] = formatTime(Long.toString(tijdsDuur));
                goedOfFout[i] = "fout";
            }


            System.out.println("Dat duurde " +
                    formatTime(Long.toString(tijdsDuur)) + " seconden!");
            System.out.println("");
        } // end of loop

        // scorebord
        System.out.println("");
        System.out.println("  -----------------------");
        System.out.println("  |  #  | **** |  Tijd  |");
        System.out.println("  -----------------------");

        for (int j = 0; j < 10; j++) {
            System.out.print("  | ");
            if (j <  9) System.out.print(" " + (j+1) + " ");
            if (j == 9) System.out.print("1 0");

            System.out.print(" | ");
            System.out.print(goedOfFout[j] + " | ");

            if (tijd[j].length() == 4) System.out.print(" " + tijd[j] + "  |");
            if (tijd[j].length() == 5) System.out.print(tijd[j] + "  |");
            if (tijd[j].length() >= 6) System.out.print(tijd[j] + " |");
            System.out.println("");
        }
        System.out.println("  -----------------------");
        System.out.println("");
    }
}