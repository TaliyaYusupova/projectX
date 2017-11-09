package tools;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    //Метод для переключения между экранами
    public static void goTo(Class c, Button button, String path) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(c.getResource(path));
        Parent root1 = null;
        try {
            root1 = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Другая форма");
        stage.setScene(new Scene(root1, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        stage.show();
    }
}
