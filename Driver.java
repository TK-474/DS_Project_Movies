import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        BST Netflix = new BST();

        Scanner Sc = new Scanner(System.in);
        String in = "";
        String b;
        double a;
        String[] sg;
        while (!in.equalsIgnoreCase("E")) {
            System.out.println("\nEnter F to search for a movie; Enter G to browse through a genre; Enter S to find the highest scored movies" +
                    "\nEnter I to add a movie to your watch list; Enter L to display your watch list; Enter C to clear your watch list;" +
                    "\nEnter W to watch a movie; Enter H to display your watch history; Enter E to exit Netflix");
            in = Sc.nextLine();
            if (in.equalsIgnoreCase("F")) {
                System.out.println("Enter a movie Title to search for it");
                b = Sc.nextLine();
                Netflix.search(b);
            } else if (in.equalsIgnoreCase("G")) {
                System.out.println("Enter the Genre you want to browse through");
                b = Sc.nextLine();
                Netflix.traverseGenre(b);
            } else if (in.equalsIgnoreCase("S")) {
                System.out.println("Enter the lowest Score acceptable (0 to 10) - (optional: Enter a Genre for a more specific output)");
                while (true) {
                    b = Sc.nextLine();
                    sg = b.split(" ");
                    if (sg.length == 1) {
                        try {
                            a = Double.parseDouble(sg[0]);
                            Netflix.topScore(a);
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter valid Decimal value");
                        }
                    } else if (sg.length == 2) {
                        try {
                            a = Double.parseDouble(sg[0]);
                            Netflix.topScore(a, sg[1]);
                            break;
                        } catch (Exception e) {
                            System.out.println("Enter valid Decimal value");
                        }
                    } else System.out.println("Enter valid value");
                }
            } else if (in.equalsIgnoreCase("I")) {
                System.out.println("Enter a movie Title to insert it to your watch list");
                b = Sc.nextLine();
                Netflix.myListInsert(b);
            } else if (in.equalsIgnoreCase("L")) {
                Netflix.displayMyList();
            } else if (in.equalsIgnoreCase("C")) {
                Netflix.clearMyList();
            } else if (in.equalsIgnoreCase("W")) {
                System.out.println("Enter the Title of the movie you have watched");
                b = Sc.nextLine();
                Netflix.watch(b);
            } else if (in.equalsIgnoreCase("H")) {
                Netflix.displayHistory();
            } else if (in.equalsIgnoreCase("E")) {
                System.out.println("Exiting");
            } else System.out.println("Invalid Input");
        }
        Sc.close();
    }
}
