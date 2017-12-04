package menu;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuItem extends StackPane {
    public MenuItem(String name, int width, int height, int textSize) {
        Rectangle bg = new Rectangle(width, height, Color.WHITE);
        bg.setOpacity(0.5);

        Text text = new Text(name);
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", FontWeight.BOLD, textSize));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
        FillTransition st = new FillTransition(Duration.seconds(0.5), bg);
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