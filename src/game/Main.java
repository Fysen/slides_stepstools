package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
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


public class Main extends Application implements EventHandler<ActionEvent> {
    Player[] players;

    public static void main(String[] args) {

        Application.launch(args);

    }

    @Override
    public void start(Stage stage) {
        // Set the stage
        stage.setTitle("Slides and Step-stools");
        Image boardpng = new Image("./img/board.png");
        ImageView ivboard = new ImageView();
        ivboard.setImage(boardpng);
        Group root = new Group();
        Scene scene = new Scene(root);

        // Create board StackPane
        StackPane board = new StackPane();
        board.getChildren().add(ivboard);

        // Create a menu VBox
        Label rollresult = new Label("Roll Result:");
        Button roll = new Button("Roll");
        roll.setOnAction(this);
        Button save = new Button("Save Game");
        save.setOnAction(this);
        Button load = new Button("Load Game");
        load.setOnAction(this);
        Button newgame = new Button("New Game");
        newgame.setOnAction(this);
        Button exit = new Button("Exit");
        exit.setOnAction(this);
        VBox menu = new VBox();
        menu.setPrefWidth(200);
        menu.setAlignment(BOTTOM_CENTER);
        menu.setSpacing(50);
        menu.getChildren().add(rollresult);
        menu.getChildren().add(roll);
        menu.getChildren().add(save);
        menu.getChildren().add(load);
        menu.getChildren().add(newgame);
        menu.getChildren().add(exit);
        VBox.setMargin(rollresult, new Insets(0, 0, 100, 0));
        VBox.setMargin(exit, new Insets(0, 0, 50, 0));
        HBox window = new HBox();
        window.getChildren().add(board);
        window.getChildren().add(menu);
        root.getChildren().add(window);

        stage.setHeight(732);
        stage.setWidth(900);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        String eventcase = event.getSource().toString().toLowerCase();
        if(eventcase.contains("roll")) {util.roll();}
        if(eventcase.contains("save")) {util.saveGame();}
        if(eventcase.contains("load")) {util.loadGame();}
        if(eventcase.contains("new")) {newGame();}
        if(eventcase.contains("exit")) {System.exit(0);}
        if(eventcase.contains("two")) {
            util.setBoard(2);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        if(eventcase.contains("three")) {
            util.setBoard(3);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        if(eventcase.contains("four")) {
            util.setBoard(4);
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }

    private void newGame() {
        // Gump asking for number of players in new game.
        VBox root = new VBox();
        final Stage dialog = new Stage();
        dialog.setWidth(200);
        dialog.setHeight(125);
        dialog.setResizable(false);
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox();
        dialogVbox.setPadding(new Insets(15, 15, 15, 15));
        dialogVbox.getChildren().add(new Text("How many players?"));
        HBox numPlayersButtons = new HBox();
        Button two = new Button("Two");
        two.setOnAction(this);
        Button three = new Button("Three");
        three.setOnAction(this);
        Button four = new Button("Four");
        four.setOnAction(this);
        numPlayersButtons.getChildren().add(two);
        numPlayersButtons.getChildren().add(three);
        numPlayersButtons.getChildren().add(four);
        numPlayersButtons.setAlignment(CENTER);
        numPlayersButtons.setSpacing(10);
        root.getChildren().add(dialogVbox);
        root.getChildren().add(numPlayersButtons);
        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}