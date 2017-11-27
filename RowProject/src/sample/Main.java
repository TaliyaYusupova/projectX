package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Image image = new Image(getClass().getResourceAsStream("background.png"));
        ImageView img = new ImageView(image);
        img.setFitHeight(600);
        img.setFitWidth(900);
        root.getChildren().add(img);

        MenuItem newGame = new MenuItem("NEW GAME");
        MenuItem options = new MenuItem("SETTINGS");
        MenuItem exitGame = new MenuItem("EXIT");
        SubMenu mainMenu = new SubMenu(
                newGame,options,exitGame
        );
        MenuItem sound = new MenuItem("SOUND");
        MenuItem video = new MenuItem("VIDEO");
        MenuItem optionsBack = new MenuItem("BACK");
        SubMenu optionsMenu = new SubMenu(
                sound,video,optionsBack
        );
        MenuItem NG2 = new MenuItem("2 X 2");
        MenuItem NG3 = new MenuItem("GAME WITH BOT");
        MenuItem NG4 = new MenuItem("BACK");
        SubMenu newGameMenu = new SubMenu(
                NG2,NG3,NG4
        );
        MenuBox menuBox = new MenuBox(mainMenu);

        newGame.setOnMouseClicked(event->menuBox.setSubMenu(newGameMenu));
        NG2.setOnMouseClicked(event -> {
            try {
                GoGame(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        options.setOnMouseClicked(event->menuBox.setSubMenu(optionsMenu));
        exitGame.setOnMouseClicked(event-> System.exit(0));
        optionsBack.setOnMouseClicked(event->menuBox.setSubMenu(mainMenu));
        NG4.setOnMouseClicked(event-> menuBox.setSubMenu(mainMenu));
        root.getChildren().addAll(menuBox);

        Scene scene = new Scene(root,900,600);
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
        primaryStage.setTitle("Pause");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static class MenuItem extends StackPane{
        public  MenuItem(String name){
            Rectangle bg = new Rectangle(400,100,Color.WHITE);
            bg.setOpacity(0.5);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Arial",FontWeight.BOLD,27));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg,text);
            FillTransition st = new FillTransition(Duration.seconds(0.5),bg);
            setOnMouseEntered(event -> {
                st.setFromValue(Color.DARKGRAY);
                st.setToValue(Color.DARKGOLDENROD);
                st.setCycleCount(Animation.INDEFINITE);
                st.setAutoReverse(true);
                st.play();
            });
            setOnMouseExited(event -> {
                st.stop();
                bg.setFill(Color.WHITE);
            });
        }
    }
    private static class MenuBox extends Pane{
        static SubMenu subMenu;
        public MenuBox(SubMenu subMenu){
            MenuBox.subMenu = subMenu;

            setVisible(false);
            Rectangle bg = new Rectangle(900,600,Color.LIGHTBLUE);
            bg.setOpacity(0.4);
            getChildren().addAll(bg, subMenu);
        }
        public void setSubMenu(SubMenu subMenu){
            getChildren().remove(MenuBox.subMenu);
            MenuBox.subMenu = subMenu;
            getChildren().add(MenuBox.subMenu);
        }
    }

    private static class SubMenu extends VBox{
        public SubMenu(MenuItem...items){
            setSpacing(20);
            setTranslateY(150);
            setTranslateX(250);
            for(MenuItem item : items){
                getChildren().addAll(item);
            }
        }
    }
    public void GoGame(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("GAME");
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}