package main;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.MenuBox;
import menu.MenuItem;
import menu.SubMenu;
import tools.Constants;

import java.io.File;

public class Main extends Application {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        stage = primaryStage;
        Image image = new Image(getClass().getResourceAsStream("background.png"));
        ImageView img = new ImageView(image);
        img.setFitHeight(Constants.SCREEN_HEIGHT);
        img.setFitWidth(Constants.SCREEN_WIDTH);
        root.getChildren().add(img);

        String file = "musicfile.mp3";
        Media backgroundSound = new Media(new File(file).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(backgroundSound);
        mediaPlayer.play();

        MenuItem newGame = new MenuItem("NEW GAME", 400, 100, 27);
        MenuItem options = new MenuItem("SETTINGS", 400, 100, 27);
        MenuItem exitGame = new MenuItem("EXIT", 400, 100, 27);
        SubMenu mainMenu = new SubMenu(
                newGame,options,exitGame
        );
        MenuItem sound = new MenuItem("SOUND", 400, 100, 27);
        MenuItem optionsBack = new MenuItem("BACK", 400, 100, 27);
        SubMenu optionsMenu = new SubMenu(
                sound,optionsBack
        );
        MenuItem NG2 = new MenuItem("2 X 2", 400, 100, 27);
        MenuItem NG3 = new MenuItem("GAME WITH BOT", 400, 100, 27);
        MenuItem NG4 = new MenuItem("BACK", 400, 100, 27);
        SubMenu newGameMenu = new SubMenu(
                NG2,NG3,NG4
        );
        MenuBox menuBox = new MenuBox(mainMenu);

        newGame.setOnMouseClicked(event->menuBox.setSubMenu(newGameMenu));
        NG2.setOnMouseClicked(event -> {
            try {
                goTo(primaryStage, "/gamefield/gameField.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
        sound.setOnMouseClicked(event -> mediaPlayer.pause());
        exitGame.setOnMouseClicked(event-> System.exit(0));
        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
        NG4.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
        root.getChildren().addAll(menuBox);

        Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1),menuBox);
                if (!menuBox.isVisible()) {
                    ft.setFromValue(0);
                    ft.setToValue(1);
                    ft.play();
                    menuBox.setVisible(true);
                }
                else{
                    ft.setFromValue(1);
                    ft.setToValue(0);
                    ft.setOnFinished(evt ->   menuBox.setVisible(false));
                    ft.play();

                }
            }
        });
        primaryStage.setTitle(Constants.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void goTo(Stage primaryStage, String path) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        primaryStage.setTitle("GAME");
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
