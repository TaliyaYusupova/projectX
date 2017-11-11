package view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import static tools.Constants.GAME_FIELD_LENGTH;
import static tools.Constants.GAME_FIELD_WIDTH;

public class GameFieldController {
    @FXML
    GridPane pane;
    private int[][] gameField = new int[GAME_FIELD_WIDTH][GAME_FIELD_LENGTH];
    //Для идентификации того, кто делает ход
    private int counter = 1;

    @FXML
    public void initialize() {
        fillGameField();

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
                            stopGameAndDisplayWinner();
                        }
                        updateCounter();
                    }
                }
            }
        });

    }

    //Заполнить игровое поле
    private void fillGameField() {
        for (int i = 0; i < GAME_FIELD_WIDTH; i++) {
            for (int k = 0; k < GAME_FIELD_LENGTH; k++) {
                gameField[i][k] = 0;
            }
        }
    }


    private boolean win() {
        return false;
    }

    private void updateCounter() {
        counter++;
        if (counter > 3) {
            counter = 1;
        }
    }

    private void makeMove(int c1, int c2) {
        gameField[c1][c2] = counter;
    }

    private void insertImage(ImageView cell) {
        switch (counter) {
            case 1:
                //вставить х
                break;
            case 2:
                //вставить о
                break;
            case 3:
                //вставить треугольник
                break;

        }
    }

    private void stopGameAndDisplayWinner() {
    }


}
