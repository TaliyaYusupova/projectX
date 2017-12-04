package menu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tools.Constants;

public class MenuBox extends Pane {
    static SubMenu subMenu;

    public MenuBox(SubMenu subMenu) {
        MenuBox.subMenu = subMenu;

        setVisible(false);
        Rectangle bg = new Rectangle(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Color.LIGHTBLUE);
        bg.setOpacity(0.4);
        getChildren().addAll(bg, subMenu);
    }

    public void setSubMenu(SubMenu subMenu) {
        getChildren().remove(MenuBox.subMenu);
        MenuBox.subMenu = subMenu;
        getChildren().add(MenuBox.subMenu);
    }
}