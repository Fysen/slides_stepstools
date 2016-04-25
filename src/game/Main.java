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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.CENTER;

//public static final Image IMGP1 = new Image("./img/p1gamepiece.png");
//public static final Image IMGP2 = new Image("./img/p1gamepiece.png");
//public static final Image IMGP3 = new Image("./img/p1gamepiece.png");
//public static final Image IMGP4 = new Image("./img/p1gamepiece.png");

public class Main extends Application implements EventHandler<ActionEvent> {

    private Stage window;
    private Scene scene0, scene1, scene2, scene3;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set the stage
        window = primaryStage;
        window.setTitle("Slides and Step-stools");

        // Set some formatting for the stage.
        window.setWidth(900);
        window.setResizable(false);

        // Import images.
        Image imgboard = new Image("./img/board.png");
        Image imgp1 = new Image("./img/p1gamepiece.png");
        Image imgp2 = new Image("./img/p2gamepiece.png");
        Image imgp3 = new Image("./img/p3gamepiece.png");
        Image imgp4 = new Image("./img/p4gamepiece.png");

        ImageView ivboard = new ImageView();
        ivboard.setImage(imgboard);

        // Initialize the array that stores all players as ImageViews.
        ImageView[] allPlayers = new ImageView[4];
        allPlayers[0] = new ImageView();
        allPlayers[0].setImage(imgp1);
        allPlayers[1] = new ImageView();
        allPlayers[1].setImage(imgp2);
        allPlayers[2] = new ImageView();
        allPlayers[2].setImage(imgp3);
        allPlayers[3] = new ImageView();
        allPlayers[3].setImage(imgp4);

        // Create a menu VBox
        Label rollresult = new Label("Roll Result:");
        Button roll = new Button("Roll");
        roll.setOnAction(e -> util.roll());
        Button save = new Button("Save Game");
        save.setOnAction(e -> util.saveGame());
        Button load = new Button("Load Game");
        load.setOnAction(e -> util.loadGame());
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
        menu.getChildren().add(rollresult);
        menu.getChildren().add(roll);
        menu.getChildren().add(save);
        menu.getChildren().add(load);
        menu.getChildren().add(newgame);
        menu.getChildren().add(exit);

        // Some inner formatting of the menu VBox.
        VBox.setMargin(rollresult, new Insets(0, 0, 400, 0));
        VBox.setMargin(exit, new Insets(0, 0, 20, 0));

        // Stackpane for blank board.
        StackPane blankboard = new StackPane();
        blankboard.getChildren().addAll(ivboard);

        // StackPane for 2-player game.
        StackPane twoplayers = new StackPane();
        twoplayers.getChildren().addAll(ivboard);

        // StackPane for 3-player game.
        StackPane threeplayers = new StackPane();
        threeplayers.getChildren().addAll(ivboard);

        // StackPane for 4-player game.
        StackPane fourplayers = new StackPane();
        fourplayers.getChildren().addAll(ivboard);

        // Layout for blank board.
        HBox layout0 = new HBox();
        layout0.getChildren().addAll(blankboard, menu);
        scene0 = new Scene(layout0);

        // Layout for 2-player game.
        HBox layout1 = new HBox();
        layout1.getChildren().addAll(twoplayers, menu);
        scene1 = new Scene(layout1);

        // Layout for 3-player game.
        HBox layout2 = new HBox();
        layout2.getChildren().addAll(threeplayers, menu);
        scene2 = new Scene(layout2);

        // Layout for 4-player game.
        HBox layout3 = new HBox();
        layout3.getChildren().addAll(fourplayers, menu);
        scene3 = new Scene(layout3);


        window.setScene(scene3);
        window.show();

    }

    @Override
    public void handle(ActionEvent event) {
        String eventcase = event.getSource().toString().toLowerCase(); // For finding keywords for event.

        // Menu button event handlers.
        if(eventcase.contains("roll")) {util.roll();}
        if(eventcase.contains("save")) {util.saveGame();}
        if(eventcase.contains("load")) {util.loadGame();}
        if(eventcase.contains("new")) {newGame();}
        if(eventcase.contains("exit")) {System.exit(0);}

        // New Game event handlers.
        if(eventcase.contains("two")) {
            System.out.println("Yeah, that was a button.");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        if(eventcase.contains("three")) {
            System.out.println("Yeah, that was a button.");
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        if(eventcase.contains("four")) {
            System.out.println("Yeah, that was a button.");
            ((Node)(event.getSource())).getScene().getRoot().getScene().getWindow().hide();
        }
    }

    private void newGame() {
        // Gump asking for number of players in new game. VBox because we want the question above the choices.
        VBox root = new VBox();
        final Stage dialog = new Stage();

        // Some formatting.
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
        numPlayersButtons.getChildren().add(two);
        numPlayersButtons.getChildren().add(three);
        numPlayersButtons.getChildren().add(four);
        numPlayersButtons.setAlignment(CENTER);
        numPlayersButtons.setSpacing(10);

        // Add the question and the buttons to the main VBox.
        root.getChildren().add(dialogVbox);
        root.getChildren().add(numPlayersButtons);
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}