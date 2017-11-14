package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import tools.Helper;

public class GameModeController {
    @FXML
    Button threePlayersBtn, botBtn;

    @FXML
    public void initialize() {

    }

    @FXML
    public void onThreePlayersBtnClicked() {
        Helper.goTo(getClass(), threePlayersBtn, "/view/gameField.fxml");

    }

    @FXML
    public void onBotBtnClicked() {
        //Helper.goTo(getClass(), botBtn, "/view/gameField.fxml");

    }
}
