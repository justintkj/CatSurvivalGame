import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class Cat {
int x, dx, y, dy, dir;
Image still;
static ArrayList<Bullet> listOfBullet = new ArrayList<Bullet>();
public Cat() {
    ImageIcon image = new ImageIcon("cat.png");
    still = image.getImage();
    still = still.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);

    x = 10;
    y = 172;
}
public void fire() {
    Bullet b = new Bullet(x, y, dir);
    listOfBullet.add(b);
}
public void move() {
    if((dx >0 && x <= FullScreenSize.width-100) || (dx <0 && x > 0)) {
        x = x + dx;
    }
    if((dy >0 && y <= FullScreenSize.height-200) || (dy <0 && y > 0)) {
        System.out.println(y);
        y = y + dy;
    }

}
public int getX() {
    return x;
}
public int getY() {
    return y;
}
public Image getImage() {
    return still;
}
public ArrayList<Bullet> getBullet() {
    return listOfBullet;
}
public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if( key == KeyEvent.VK_LEFT) {
        dx = -10;
    }
    if (key == KeyEvent.VK_RIGHT) {
        dx = 10;
    }
    if(key == KeyEvent.VK_UP) {
        dy = -10;
    }
    if (key == KeyEvent.VK_DOWN) {
        dy = 10;
    }
    if (key == KeyEvent.VK_SPACE) { //rotates
        if (dir == 7) {
            dir = 0;
        } else {
            dir++;
        }
    }
}

public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    if (key == KeyEvent.VK_RIGHT) {
        dx = 0;
    }
    if (key == KeyEvent.VK_LEFT) {
        dx = 0;
    }
    if (key == KeyEvent.VK_UP) {
        dy = 0;
    }
    if (key == KeyEvent.VK_DOWN) {
        dy = 0;
    }
}

}
