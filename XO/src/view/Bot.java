package view;

public class Bot {
    private int counter;

    public Bot(int counter){
        this.counter = counter;
    }
    public void makeMove(){
        int[][] gameField = GameFieldController.gameField;

    }

    public int getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return "Bot" + counter;
    }
}
