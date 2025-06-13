package GuitarTabMaker.GUIWindows;

import java.awt.*;

public interface Window {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int screenSizeWidth = (int) (screenSize.getWidth());
    int screenSizeHeight = (int) ( screenSize.getHeight());
    Color background_c = new Color(97, 115, 124);//92, 94, 118);
    Color button_off_c = new Color(91, 114, 124);
    Color button_on_c = new Color(62, 79, 96);
    Color button_hover = new Color(42, 59, 76);
    Color text_c = new Color(26, 32, 37);
    Color fretboard_c = new Color(93, 92, 79);
    Color fretboard_num_c = new Color(73, 72, 69);
    Color function_panel_c = new Color(73, 72, 69);
    Color menu_panel_c = new Color(73, 72, 69);
    Color menu_item_c = new Color(83, 82, 79);
    Color text_area_c = new Color(72, 84, 94);
    String font = "Monospaced";




}
