import java.awt.*;

import javax.swing.*;

public class Frame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("CatGame");
        frame.add(new Board());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FullScreenSize.width, FullScreenSize.height);
        frame.setVisible(true);
    }
}
