package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import tools.Helper;

import static tools.Constants.GAME_FIELD_LENGTH;
import static tools.Constants.GAME_FIELD_WIDTH;

public class GameFieldController {
    @FXML
    GridPane pane;
    public static int[][] gameField = new int[GAME_FIELD_WIDTH][GAME_FIELD_LENGTH];
    //Для идентификации того, кто делает ход
    private int counter = 1;
    private int count = 0;

    private enum GAME_MODE {
        threePlayers, twoPlayers, onePlayer
    }

    private Bot bot1;
    private Bot bot2;

    @FXML
    public void initialize() {
        fillGameField();

        GAME_MODE mode = GAME_MODE.threePlayers;


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
                        insertImage((ImageView) node, null);
                        if (win()) {
                            stopGameAndDisplayWinner();
                        } else if (draw()) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            System.out.println(gameField);

                            alert.setTitle("GAME OVER");
                            alert.setHeaderText(null);
                            alert.setContentText("Nobody win");

                            alert.showAndWait();
                        }
                        updateCounter();
                        if (counter == 3) {
                            if (mode.equals(GAME_MODE.twoPlayers) || mode.equals(GAME_MODE.onePlayer)) {
                                bot1.makeMove();
                                insertImage((ImageView) node, bot1);

                                if (win()) {
                                    stopGameAndDisplayWinner();
                                }

                            }
                        } else if (counter == 2) {
                            if (mode.equals(GAME_MODE.onePlayer)) {

                                bot2.makeMove();
                                insertImage((ImageView) node, bot2);

                                if (win()) {
                                    stopGameAndDisplayWinner();
                                }

                                updateCounter();

                                bot1.makeMove();
                                insertImage((ImageView) node, bot1);

                                if (win()) {
                                    stopGameAndDisplayWinner();
                                }

                            }
                        }

                    }
                }
            }
        });


    }

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
    private void insertImage(ImageView cell, Bot bot) {
        if (bot != null) {
            cell.setImage(new Image(bot.getCounter() + ".jpg"));
        } else {
            switch (counter) {
                case 1:
                    cell.setImage(new Image("/view/1.png"));
                    //вставить х
                    break;
                case 2:
                    cell.setImage(new Image("/view/2.jpg"));
                    //вставить о
                    break;

            }
        }
    }

    //Заканчиваем игру
    private void stopGameAndDisplayWinner() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        System.out.println(gameField);

        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);
        alert.setContentText("Player" + counter + " win!");

        alert.showAndWait();


    }


}
