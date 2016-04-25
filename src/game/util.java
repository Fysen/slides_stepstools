package game;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class util {

    static int roll() {
        System.out.println("You rolled.");
        return 0;
    }

    static void saveGame() {
        System.out.println("You saved.");
    }

    static void loadGame() {
        System.out.println("You loaded.");
    }

    static ImageView[] setBoard(int numPlayers) {
        ImageView[] players = new ImageView[numPlayers];
        if (players.length == 2) {
            players[0].setImage(new Image("./img/p1gamepiece.png"));
            players[0].setX(15);
            players[0].setY(748);
            players[1].setImage(new Image("./img/p2gamepiece.png"));
            players[1].setX(30);
            players[1].setY(748);
        }
        return players;
    }
}
