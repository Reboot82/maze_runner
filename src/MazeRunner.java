import java.util.*;

public class MazeRunner {
    public static int moves = 0;
    public static Maze myMap = new Maze();

    public static void main(String[] args) {
        welcome();
        intro();
        userMove();


    }

    public static void welcome() {
        System.out.println(" __   __  ___  _______ ___      ______   ______  ___      ___  _______      ___________ ______        ___      ___      __    ________   _______       _______  ____  ____ _____  ___  _____  ___   _______  _______   \n" +
                "|\"  |/  \\|  \"|/\"     \"|\"  |    /\" _  \"\\ /    \" \\|\"  \\    /\"  |/\"     \"|    (\"     _   \")    \" \\      |\"  \\    /\"  |    /\"\"\\  (\"      \"\\ /\"     \"|     /\"      \\(\"  _||_ \" (\\\"   \\|\"  \\(\\\"   \\|\"  \\ /\"     \"|/\"      \\  \n" +
                "|'  /    \\:  (: ______)|  |   (: ( \\___)/ ____  \\\\   \\  //   (: ______)     )__/  \\\\__// ____  \\      \\   \\  //   |   /    \\  \\___/   :|: ______)    |:        |   (  ) : |.\\\\   \\    |.\\\\   \\    (: ______):        | \n" +
                "|: /'        |\\/    | |:  |    \\/ \\   /  /    ) :)\\\\  \\/.    |\\/    |          \\\\_ / /  /    ) :)     /\\\\  \\/.    |  /' /\\  \\   /  ___/ \\/    |      |_____/   |:  |  | . ): \\.   \\\\  |: \\.   \\\\  |\\/    | |_____/   ) \n" +
                " \\//  /\\'    |// ___)_ \\  |___ //  \\ (: (____/ //: \\.        |// ___)_         |.  |(: (____/ //     |: \\.        | //  __'  \\ //  \\__  // ___)_      //      / \\\\ \\__/ //|.  \\    \\. |.  \\    \\. |// ___)_ //      /  \n" +
                " /   /  \\\\   (:      \"( \\_|:  (:   _) \\        /|.  \\    /:  (:      \"|        \\:  | \\        /      |.  \\    /:  |/   /  \\\\  (:   / \"\\(:      \"|    |:  __   \\ /\\\\ __ //\\|    \\    \\ |    \\    \\ (:      \"|:  __   \\  \n" +
                "|___/    \\___|\\_______)\\_______)_______)\"_____/ |___|\\__/|___|\\_______)         \\__|  \\\"_____/       |___|\\__/|___(___/    \\___)_______)\\_______)    |__|  \\___|__________)\\___|\\____\\)\\___|\\____\\)\\_______)__|  \\___) \n" +
                "                                                                                                                                                                                                                       \n");
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
//                    myMap.printMap();
                } else if (myMap.isThereAPit("L")) {
                    myMap.jumpOverPit("L");
                    print("You jumped over a pit!");
                } else {
                    print("Sorry, you've hit a wall.");
                }
                break;
            case "R":
                print("You chose to go right.");
                if (myMap.canIMoveRight()) {
                    myMap.moveRight();
//                    myMap.printMap();
                } else if (myMap.isThereAPit("R")) {
                    myMap.jumpOverPit("R");
                    print("You jumped over a pit!");
                } else {
                    print("Sorry, you've hit a wall.");
                }
                break;
            case "U":
                print("You chose to go up.");
                if (myMap.canIMoveUp()) {
                    myMap.moveUp();
//                    myMap.printMap();
                } else if (myMap.isThereAPit("U")) {
                    myMap.jumpOverPit("U");
                    print("You jumped over a pit!");
                } else {
                    print("Sorry, you've hit a wall.");
                }
                break;
            case "D":
                print("You chose to go down.");
                if (myMap.canIMoveDown()) {
                    myMap.moveDown();
//                    myMap.printMap();
                } else if (myMap.isThereAPit("D")) {
                    myMap.jumpOverPit("D");
                    print("You jumped over a pit!");
                } else {
                    print("Sorry, you've hit a wall.");
                }
                break;
            default:
                print("Please try again.");
                userMove();
        }
        if(myMap.didIWin()) {
            print("Congratulations, you made it out alive!");
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
            System.exit(0);
        } else {
            userMove();
        }
    }


    public static void print(String text) {
        System.out.println(text);
    }
}
