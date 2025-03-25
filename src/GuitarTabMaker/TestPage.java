package GuitarTabMaker;

import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;



class ShapeDrawing extends JComponent {
    private int fret_num = 16;
    private int spacing = 33;
    private int c_width = 18;
    public int right_border = 80;
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        //Draw the fretboard
        for(int i = 1; i <7; i++){
            int x = spacing *i;
            g2.drawLine( right_border, getHeight()-x, getWidth(), getHeight()-x);
            for (int n = 1; n <=fret_num; n++){
                int step = getWidth()/fret_num * n;
                g2.drawOval( right_border+step-2*c_width, getHeight()-x-c_width/2, c_width, c_width);
            }
        }
        //Draw the string picker
        g2.drawRect(0,getHeight()-spacing*6-c_width, right_border, spacing*6+c_width);
    }
}

public class TestPage {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = (int) (screenSize.getWidth()*0.8);
    static int height = (int) (screenSize.getHeight()*0.8);
    public static void main(String[] args) {
        JFrame frame = new JFrame("My first JFrame");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ShapeDrawing ());
        frame.setVisible(true);
    }

}