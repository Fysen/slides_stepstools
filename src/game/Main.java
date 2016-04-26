package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.CENTER;

public class Main extends Application implements EventHandler<ActionEvent> {

    // Variables declared in top-level to allow access to them from methods outside 'start'.
    private int dice;
    private int numPlayers = -1;
    private int turn;
    private ImageView[] allPlayers = new ImageView[4];

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set the stage
        primaryStage.setTitle("Slides and Step-stools");

        // Set some formatting for the stage.
        primaryStage.setHeight(732);
        primaryStage.setWidth(900);
        primaryStage.setResizable(false);

        // Import images.
        Image imgboard = new Image("./img/board.png");
        Image imgp1 = new Image("./img/p1gamepiece.png");
        Image imgp2 = new Image("./img/p2gamepiece.png");
        Image imgp3 = new Image("./img/p3gamepiece.png");
        Image imgp4 = new Image("./img/p4gamepiece.png");

        ImageView ivboard = new ImageView();
        ivboard.setImage(imgboard);

        // Initialize the array that stores all players as ImageViews.
        allPlayers[0] = new ImageView();
        allPlayers[0].setImage(imgp1);
        allPlayers[0].setX(-10);
        allPlayers[1] = new ImageView();
        allPlayers[1].setImage(imgp2);
        allPlayers[1].setX(5);
        allPlayers[2] = new ImageView();
        allPlayers[2].setImage(imgp3);
        allPlayers[2].setX(20);
        allPlayers[3] = new ImageView();
        allPlayers[3].setImage(imgp4);
        allPlayers[3].setX(34);

        // Create a menu VBox
        Label rollresultlbl = new Label("Roll Result:");
        Label rollresult = new Label("");
        Button roll = new Button("Roll");
        roll.setOnAction(e -> {
            dice = getSpinnerNumber();
            rollresult.setText(Integer.toString(dice));
            move(allPlayers[turn % numPlayers], dice);
            turn++;
        });
        Button save = new Button("Save Game");
        save.setOnAction(e -> saveGame());
        Button load = new Button("Load Game");
        load.setOnAction(e -> loadGame());
        Button newgame = new Button("New Game");
        newgame.setOnAction(e -> newGame());
        Button exit = new Button("Exit");
        exit.setOnAction(e -> System.exit(0));
        VBox menu = new VBox();

        // A little formatting.
        menu.setPrefWidth(200);
        menu.setAlignment(BOTTOM_CENTER);
        menu.setSpacing(10);

        // Add all the things made to the menu VBox.
        menu.getChildren().addAll(rollresultlbl, rollresult, roll, save, load, newgame, exit);

        // Some inner formatting of the menu VBox.
        VBox.setMargin(rollresult, new Insets(0, 0, 400, 0));
        VBox.setMargin(exit, new Insets(0, 0, 20, 0));

        // AnchorPane for board.
        StackPane board = new StackPane();
        AnchorPane boardtiles = new AnchorPane();
        AnchorPane.setBottomAnchor(allPlayers[0], 0.0);
        AnchorPane.setBottomAnchor(allPlayers[1], 0.0);
        AnchorPane.setBottomAnchor(allPlayers[2], 0.0);
        AnchorPane.setBottomAnchor(allPlayers[3], 0.0);
        AnchorPane.setLeftAnchor(allPlayers[0], (0.0 + allPlayers[0].getX()));
        AnchorPane.setLeftAnchor(allPlayers[1], (0.0 + allPlayers[1].getX()));
        AnchorPane.setLeftAnchor(allPlayers[2], (0.0 + allPlayers[2].getX()));
        AnchorPane.setLeftAnchor(allPlayers[3], (0.0 + allPlayers[3].getX()));
        boardtiles.getChildren().addAll(allPlayers[0], allPlayers[1], allPlayers[2], allPlayers[3]);
        board.getChildren().addAll(ivboard, boardtiles);

        // Full scene layout.
        HBox aggregate = new HBox();
        aggregate.getChildren().addAll(board, menu);
        aggregate.setPrefSize(704, 702);
        Scene scene = new Scene(aggregate);

        // show the board scene.
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(0 % 4);
    }

    @Override
    public void handle(ActionEvent event) {

        String eventcase = event.getSource().toString().toLowerCase(); // For finding keywords for event.
        // New Game event handlers.
        if (eventcase.contains("two")) {
            // Reassign per-game variables.
            numPlayers = 2;
            turn = 0;

            // Only have the relevant pieces shown.
            allPlayers[0].setVisible(true);
            allPlayers[1].setVisible(true);
            allPlayers[2].setVisible(false);
            allPlayers[3].setVisible(false);

            // Reset positions for relevant pieces.
            for (int i = 0; i < 2; i++) {
                AnchorPane.setLeftAnchor(allPlayers[i], allPlayers[i].getX());
                AnchorPane.setBottomAnchor(allPlayers[i], 0.0);
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        if (eventcase.contains("three")) {
            numPlayers = 3;
            turn = 0;
            allPlayers[0].setVisible(true);
            allPlayers[1].setVisible(true);
            allPlayers[2].setVisible(true);
            allPlayers[3].setVisible(false);
            for (int i = 0; i < 3; i++) {
                AnchorPane.setLeftAnchor(allPlayers[i], allPlayers[i].getX());
                AnchorPane.setBottomAnchor(allPlayers[i], 0.0);
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
        if (eventcase.contains("four")) {
            numPlayers = 4;
            turn = 0;
            allPlayers[0].setVisible(true);
            allPlayers[1].setVisible(true);
            allPlayers[2].setVisible(true);
            allPlayers[3].setVisible(true);
            for (int i = 0; i < 4; i++) {
                AnchorPane.setLeftAnchor(allPlayers[i], allPlayers[i].getX());
                AnchorPane.setBottomAnchor(allPlayers[i], 0.0);
            }
            ((Node) (event.getSource())).getScene().getWindow().hide();
        }
    }

    private void newGame() {
        // Gump asking for number of players in new game. VBox because we want the question above the choices.
        VBox root = new VBox();
        final Stage dialog = new Stage();

        // Some formatting.
        dialog.setTitle("New Game");
        dialog.setWidth(200);
        dialog.setHeight(125);
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL); // Makes it so you can't click anywhere but this gump.

        // Create VBox to hold the question and option buttons.
        VBox dialogVbox = new VBox();

        // Some formatting.
        dialogVbox.setPadding(new Insets(15, 15, 15, 15));

        // Question label.
        dialogVbox.getChildren().add(new Text("How many players?"));

        // Make buttons for number of players (with HBox so the buttons are laid out horizontally).
        HBox numPlayersButtons = new HBox();
        Button two = new Button("Two");
        two.setOnAction(this);
        Button three = new Button("Three");
        three.setOnAction(this);
        Button four = new Button("Four");
        four.setOnAction(this);

        // Add them to the HBox.
        numPlayersButtons.getChildren().addAll(two, three, four);
        numPlayersButtons.setAlignment(CENTER);
        numPlayersButtons.setSpacing(10);

        // Add the question and the buttons to the main VBox.
        root.getChildren().add(dialogVbox);
        root.getChildren().add(numPlayersButtons);
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    private void saveGame() {
        Date date = new Date();
        List<String> lines = Arrays.asList(Integer.toString(numPlayers), Integer.toString(turn));
        Path file = Paths.get("savedata" + date.getTime() + ".txt");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadGame() {

    }

    private int getSpinnerNumber() {

        return 1 + (int)(Math.random() * (6));
    }

    private void move(ImageView player, int spaces) {

        if (numPlayers < 1) {
            newGame();
        } else {
            for (int i = 0; i < spaces; i++) {
                if (AnchorPane.getLeftAnchor(player) > 605 && AnchorPane.getBottomAnchor(player) > 640) {
                    System.out.println("Player " + ((turn % numPlayers) + 1) + " won!");
                    break;
                }
                if (AnchorPane.getLeftAnchor(player) + 88 > 690) {
                    AnchorPane.setLeftAnchor(player, player.getX());
                    AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) + 54);
                    continue;
                }
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) + 88);
            }
            // Checkers for 'slides' or 'step-stools'.
            // Landing on 18
            if ((AnchorPane.getBottomAnchor(player) == 108) && (AnchorPane.getLeftAnchor(player) - player.getX() == 88)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) + 176);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) + 162);
            }
            //Landing on 34
            if ((AnchorPane.getBottomAnchor(player) == 216) && (AnchorPane.getLeftAnchor(player) - player.getX() == 88)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) + 176);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) - 162);
            }
            // Landing on 38
            if ((AnchorPane.getBottomAnchor(player) == 216) && (AnchorPane.getLeftAnchor(player) - player.getX() == 440)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) - 264);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) + 270);
            }
            // Landing on 42
            if ((AnchorPane.getBottomAnchor(player) == 270) && (AnchorPane.getLeftAnchor(player) - player.getX() == 88)) {
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) + 270);
            }
            // Landing on 62
            if ((AnchorPane.getBottomAnchor(player) == 378) && (AnchorPane.getLeftAnchor(player) - player.getX() == 440)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) - 176);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) - 216);
            }
            // Landing on 66
            if ((AnchorPane.getBottomAnchor(player) == 432) && (AnchorPane.getLeftAnchor(player) - player.getX() == 88)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) + 440);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) - 108);
            }
            // Landing on 69
            if ((AnchorPane.getBottomAnchor(player) == 432) && (AnchorPane.getLeftAnchor(player) - player.getX() == 352)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) + 264);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) + 216);
            }
            // Landing on 95
            if ((AnchorPane.getBottomAnchor(player) == 594) && (AnchorPane.getLeftAnchor(player) - player.getX() == 528)) {
                AnchorPane.setLeftAnchor(player, AnchorPane.getLeftAnchor(player) - 264);
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) - 162);
            }
            // Landing on 103
            if ((AnchorPane.getBottomAnchor(player) == 648) && (AnchorPane.getLeftAnchor(player) - player.getX() == 528)) {
                AnchorPane.setBottomAnchor(player, AnchorPane.getBottomAnchor(player) - 486);
            }
            if (AnchorPane.getLeftAnchor(player) > 605 && AnchorPane.getBottomAnchor(player) > 640) {
                System.out.println("Player " + ((turn % numPlayers) + 1) + " won!");
            }

        }
    }
}