package VARpedia;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
          Setup the initial application window
         */
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/mainMenu.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setTitle("VARpedia");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        /*
           Prompt user before exiting application
         */
        Platform.setImplicitExit(false);
        primaryStage.setOnCloseRequest(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want to quit?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.get() == ButtonType.OK) {
                primaryStage.close();
                Platform.exit();
                System.exit(0);
            } else {
                e.consume();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
