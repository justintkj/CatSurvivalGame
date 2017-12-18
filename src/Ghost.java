import java.awt.*;

import javax.swing.*;

public class Ghost {
    Image img;
    int x,y,dx,dy;
    boolean isAlive = true;

    public Ghost (double startx, double starty, double dx, double dy) {
        x = (int)startx;
        y = (int)starty;
        this.dx = (int)dx;
        this.dy = (int)dy;
        ImageIcon icon = new ImageIcon("ghost.png");
        img = icon.getImage();
        img = img.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
    }
    public Image getImage() {
        return img;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void move() {
        x += dx;
        y += dy;
    }
}
