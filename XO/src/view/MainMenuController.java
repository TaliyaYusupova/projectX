package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.MainMenu;
import tools.Helper;

public class MainMenuController {
    @FXML
    Button startBtn;
    @FXML
    Button settingBtn;
    @FXML
    Button exitBtn;

    @FXML
    public void initialize() {

    }

    @FXML
    public void onStartBtnClicked() {
        Helper.goTo(MainMenu.class, startBtn, "/view/gameField.fxml");
    }

    @FXML
    public void onSettingsBtnClicked() {

    }

    @FXML
    public void onExitBtnClicked() {

    }


}
