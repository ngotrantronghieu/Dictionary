package dictionary.dictionarypro;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Objects;

/**
 * This is a public Main class.
 */

public class Main extends Application {

    /**
     * This is a public static void main method.
     * @param args is the default argument of the main method
     */

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This is a public void start method override the start method in the Application class.
     * @param primaryStage is the stage of the UI
     */

    @Override

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(this.getClass().getResource("newHome2.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Dictionary");
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream(
                "29-data-dictionary.png"))));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            }
        });
    }
}
