import java.awt.*;

import javax.swing.*;

public class Bullet {
    int x ,y;
    int dir=0;
    int speed = 25;
    Image img;
    Boolean visible = true;
    public Bullet (int startx, int starty, int dir) {
        x = startx;
        y = starty;
        this.dir = dir;
        ImageIcon newBullet = new ImageIcon("bullet.png");
        img = newBullet.getImage();
        img = img.getScaledInstance(70,70,java.awt.Image.SCALE_SMOOTH);
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
    public void move() {
        if(dir == 0 ||dir == 1 || dir == 7) {
            x = x + speed;
        } else if(dir == 3 || dir == 4 || dir ==5) {
            x = x - speed;
        }
        if(dir == 1 ||dir == 2 || dir == 3) {
            y = y - speed;
        } else if(dir == 5 || dir == 6 || dir == 7) {
            y = y + speed;
        }
    }
}

