package menu;

import javafx.scene.layout.VBox;

public class SubMenu extends VBox {
    public SubMenu(MenuItem... items) {
        setSpacing(20);
        setTranslateY(150);
        setTranslateX(250);
        for (MenuItem item : items) {
            getChildren().addAll(item);
        }
    }
}