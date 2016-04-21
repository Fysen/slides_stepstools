package sample;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override public void start(Stage stage) {
// load the image
        Image image = new Image("./img/p1gamepiece.png");

// simple displays ImageView the image as is
        ImageView iv1 = new ImageView();
        iv1.setImage(image);

// resizes the image to have width of 100 while preserving the ratio and using
// higher quality filtering method; this ImageView is also cached to
// improve performance
        ImageView iv2 = new ImageView();
        iv2.setImage(image);
        iv2.setFitWidth(400);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        Group root = new Group();
        Scene scene = new Scene(root);
        HBox box = new HBox();
        box.getChildren().add(iv1);
        box.getChildren().add(iv2);
        root.getChildren().add(box);

        stage.setTitle("ImageView");
        stage.setWidth(415);
        stage.setHeight(200);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}