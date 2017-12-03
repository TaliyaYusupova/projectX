package gamefield;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import tools.Constants;

import java.io.IOException;

import static tools.Constants.GAME_FIELD_LENGTH;
import static tools.Constants.GAME_FIELD_WIDTH;

public class GameFieldController {
    @FXML
    GridPane pane;
    private static int[][] gameField = new int[GAME_FIELD_WIDTH][GAME_FIELD_LENGTH];
    //Для идентификации того, кто делает ход
    private int counter = 1;
    private int count = 0;

    @FXML
    public void initialize() {
        fillGameField();
        //Отслеживание нажатий
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            for (Node node : pane.getChildren()) {
                if (node instanceof ImageView) {
                    if (node.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {

                        int c1, c2;

                        if (GridPane.getRowIndex(node) == null) {
                            c1 = 0;
                        } else {
                            c1 = GridPane.getRowIndex(node);
                        }

                        if (GridPane.getColumnIndex(node) == null) {
                            c2 = 0;
                        } else {
                            c2 = GridPane.getColumnIndex(node);
                        }
                        System.out.println("Node: " + node + " at " + c1 + "/" + c2);

                        makeMove(c1, c2);
                        insertImage((ImageView) node);
                        if (win()) {
                            stopGameAndDisplayWinner(false);
                        } else if (draw()) {
                            stopGameAndDisplayWinner(true);
                        }
                        updateCounter();

                    }
                }
            }
        });


    }

    //Проверка на ничью
    private boolean draw() {
        for (int[] i : gameField) {
            for (int k : i) {
                if (k == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //Заполнить игровое поле
    private void fillGameField() {
        for (int i = 0; i < GAME_FIELD_WIDTH; i++) {
            for (int k = 0; k < GAME_FIELD_LENGTH; k++) {
                gameField[i][k] = 0;
            }
        }
    }

    //Проверить, выиграл или нет
    private boolean win() {
        //Проверка по горизонтали
        int WIN_COUNT = 2;
        for (int i = 0; i < gameField.length; i++) {
            for (int k = 0; k < gameField.length - 1; k++) {
                if (gameField[i][k] == gameField[i][k + 1] && gameField[i][k] != 0 && gameField[i][k + 1] != 0) {
                    count++;
                }
            }
            if (count >= WIN_COUNT) {
                System.out.println("hor");
                return true;
            } else {
                count = 0;
            }
        }
        //Проверка по вертикали
        count = 0;
        for (int i = 0; i < gameField.length; i++) {
            for (int k = 0; k < gameField.length - 1; k++) {
                if (gameField[k][i] == gameField[k + 1][i] && gameField[k][i] != 0 && gameField[k + 1][i] != 0) {
                    count++;
                }
            }
            if (count >= WIN_COUNT) {
                System.out.println("ver");

                return true;
            } else {
                count = 0;
            }
        }

        //Проверка по главной диагонали
        if (checkDiagonals()) {
            System.out.println("dig");
            return true;
        }

        return false;
    }

    private boolean checkDiagonals() {
        int sum1 = 1;
        for (int i = 0; i < gameField.length; i++) {
            sum1 *= gameField[i][i];
        }

        int sum2 = 1;
        for (int i = 0; i < gameField.length; i++) {
            sum2 *= gameField[i][gameField.length - i - 1];
        }

        return sum1 == 1 || sum2 == 8;
    }


    //Обновляем счетчик
    private void updateCounter() {
        counter++;
        if (counter > 2) {
            counter = 1;
        }
    }

    //Делаем ход
    private void makeMove(int c1, int c2) {
        gameField[c1][c2] = counter;
    }

    //Вставляем изображение
    private void insertImage(ImageView cell) {
        switch (counter) {
            case 1:
                cell.setImage(new Image("/gamefield/1.png"));
                //вставить х
                break;
            case 2:
                cell.setImage(new Image("/gamefield/2.jpg"));
                //вставить о
                break;
        }
    }

    //Заканчиваем игру
    private void stopGameAndDisplayWinner(boolean draw) {
        final Stage dialog = new Stage();
        dialog.setTitle("Game Over");
        dialog.setMinWidth(350);
        dialog.setMinHeight(200);
        dialog.setWidth(350);
        dialog.setHeight(200);
        Button yes = new Button("Restart");
        Button no = new Button("Exit");
        Label displayLabel;
        if (!draw) {
            displayLabel = new Label("Player" + counter + " win!");
        } else {
            displayLabel = new Label("Nobody win!");

        }
        displayLabel.setFont(Font.font(null, FontWeight.BOLD, 14));

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner((Stage) Main.stage.getScene().getWindow());


        yes.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    dialog.close();
                    goTo(Main.stage, "/gamefield/gameField.fxml");
                });
        no.addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    dialog.close();
                    System.exit(0);
                });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(displayLabel, yes, no);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.show();


    }

    private void goTo(Stage primaryStage, String path) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
        } catch (IOException ignored) {
        }
        primaryStage.setTitle("GAME");
        Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
