package view;

import javafx.fxml.FXML;

import static tools.Constants.GAME_FIELD_LENGTH;
import static tools.Constants.GAME_FIELD_WIDTH;

public class GameModeController {
    private int[][] gameField = new int[GAME_FIELD_WIDTH][GAME_FIELD_LENGTH];
    //Для идентификации того, кто делает ход
    private int counter = 1;
    @FXML
    public void initialize() {
        fillGameField();
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
        insertImage();
    }

    private void insertImage() {
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

    @FXML
    public void onCell00() {
        makeMove(0, 0);
        if (win()) {
            stopGameAndDisplayWinner();
        }
        updateCounter();
    }

    @FXML
    public void onCell01() {
        makeMove(0, 1);
        if (win()) {
            stopGameAndDisplayWinner();
        }
        updateCounter();
    }

    // и т д


}
