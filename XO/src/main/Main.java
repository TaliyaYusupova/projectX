package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.Constants;

public class Main extends Application {
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
        primaryStage.setTitle(Constants.APP_NAME);
        primaryStage.setScene(new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        primaryStage.show();

    }
}
