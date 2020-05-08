import java.util.*;

//for maze we need to move , jump pits, keep track of moves, and have alerts at set move levels ....
// win scenario (if we fix map) lose scenario, and quit option ...

public class MazeRunner {
    public static int moves = 0;
    public static Maze myMap = new Maze();
    public static String wall = "Sorry, you've hit a wall.";

    public static void main(String[] args) {
        welcome();
        intro();
        userMove();


    }

    public static void welcome() {
        System.out.println(" __   __  ___  _______ ___      ______   ______  ___      ___  _______      ___________ ______    \n" +
                "|\"  |/  \\|  \"|/\"     \"|\"  |    /\" _  \"\\ /    \" \\|\"  \\    /\"  |/\"     \"|    (\"     _   \")    \" \\   \n" +
                "|'  /    \\:  (: ______)|  |   (: ( \\___)/ ____  \\\\   \\  //   (: ______)     )__/  \\\\__// ____  \\  \n" +
                "|: /'        |\\/    | |:  |    \\/ \\   /  /    ) :)\\\\  \\/.    |\\/    |          \\\\_ / /  /    ) :) \n" +
                " \\//  /\\'    |// ___)_ \\  |___ //  \\ (: (____/ //: \\.        |// ___)_         |.  |(: (____/ //  \n" +
                " /   /  \\\\   (:      \"( \\_|:  (:   _) \\        /|.  \\    /:  (:      \"|        \\:  | \\        /   \n" +
                "|___/    \\___|\\_______)\\_______)_______)\"_____/ |___|\\__/|___|\\_______)         \\__|  \\\"_____/    \n" +
                "                                                                                                  \n" +
                " ___      ___      __    ________   _______       _______  ____  ____ _____  ___  _____  ___   _______  _______   \n" +
                "|\"  \\    /\"  |    /\"\"\\  (\"      \"\\ /\"     \"|     /\"      \\(\"  _||_ \" (\\\"   \\|\"  \\(\\\"   \\|\"  \\ /\"     \"|/\"      \\  \n" +
                " \\   \\  //   |   /    \\  \\___/   :|: ______)    |:        |   (  ) : |.\\\\   \\    |.\\\\   \\    (: ______):        | \n" +
                " /\\\\  \\/.    |  /' /\\  \\   /  ___/ \\/    |      |_____/   |:  |  | . ): \\.   \\\\  |: \\.   \\\\  |\\/    | |_____/   ) \n" +
                "|: \\.        | //  __'  \\ //  \\__  // ___)_      //      / \\\\ \\__/ //|.  \\    \\. |.  \\    \\. |// ___)_ //      /  \n" +
                "|.  \\    /:  |/   /  \\\\  (:   / \"\\(:      \"|    |:  __   \\ /\\\\ __ //\\|    \\    \\ |    \\    \\ (:      \"|:  __   \\  \n" +
                "|___|\\__/|___(___/    \\___)_______)\\_______)    |__|  \\___|__________)\\___|\\____\\)\\___|\\____\\)\\_______)__|  \\___) \n" +
                "                                                                                                                  ");
    }

    public static void intro() {
        print("Here is your current position:");
        myMap.printMap();
    }

    public static void userMove() {
        Scanner input = new Scanner(System.in);
        print("Where would you like to move? (R, L, U, D) ");
        String move = input.next();
        switch(move) {
            case "L":
                print("You chose to go left.");
                if (myMap.canIMoveLeft()) {
                    myMap.moveLeft();
                } else if (myMap.isThereAPit("L")) {
                    navigatePit("L");
                } else {
                    print(wall);
                }
                break;
            case "R":
                print("You chose to go right.");
                if (myMap.canIMoveRight()) {
                    myMap.moveRight();
                } else if (myMap.isThereAPit("R")) {
                    navigatePit("R");
                } else {
                    print(wall);
                }
                break;
            case "U":
                print("You chose to go up.");
                if (myMap.canIMoveUp()) {
                    myMap.moveUp();
                } else if (myMap.isThereAPit("U")) {
                    navigatePit("U");
                } else {
                    print(wall);
                }
                break;
            case "D":
                print("You chose to go down.");
                if (myMap.canIMoveDown()) {
                    myMap.moveDown();
                } else if (myMap.isThereAPit("D")) {
                    navigatePit("D");
                } else {
                    print(wall);
                }
                break;
            case "stop":
            case "exit":
            case "quit":
                print("Game over.");
                System.exit(0);
                break;
            default:
                print("Please try again.");
                userMove();
        }
        if(myMap.didIWin()) {
            print("Congratulations, you made it out alive!");
            print("And you did it in " + moves + " moves!");
            System.exit(0);
        } else {
            movesMessage();
            userMove();
        }
    }

    public static void movesMessage() {
        MazeRunner.moves++;
        if (MazeRunner.moves == 50) {
            print("Warning: You have made 50 moves. You have 50 remaining before the maze exit closes.");
        } else if (MazeRunner.moves == 75) {
            print("Alert! You have made 75 moves. You only have 25 moves left to escape.");
        } else if (MazeRunner.moves == 90) {
            print("DANGER! You have made 90 moves. You only have 10 moves left to escape!");
        } else if (MazeRunner.moves == 100) {
            print("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
            print("Sorry, but you didn't escape in time- you lose!");
            System.exit(0);
        } else {
            userMove();
        }
    }

    public static void navigatePit(String dir) {
        Scanner input = new Scanner(System.in);
        print("Watch out! There's a pit ahead, jump it?");
        String response = input.nextLine();
        if(response.toLowerCase().startsWith("y")) {
            myMap.jumpOverPit(dir);
            print("You jumped over a pit!");
        } else {
            userMove();
        }

    }


    public static void print(String text) {
        System.out.println(text);
    }
}
