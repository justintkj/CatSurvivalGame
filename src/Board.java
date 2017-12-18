import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Board extends JPanel implements ActionListener{
    Cat character;
    Image img;
    Timer time;
    HighscoreManager hm;
    int score =0;
    ArrayList<Rat> listOfRat = new ArrayList<Rat>();
    static Font font = new Font ("Arial", Font.BOLD, 24);

    public Board() {
        character = new Cat();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon icon = new ImageIcon("Background.jpg");
        img = icon.getImage();
        FullScreenSize.update();
        img = img.getScaledInstance(FullScreenSize.width,FullScreenSize.height,java.awt.Image.SCALE_SMOOTH);
        time = new Timer(1, this);
        time.start();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 20, TimeUnit.MILLISECONDS);
        Music.playSound();
        hm = new HighscoreManager();
    }
    Runnable helloRunnable = new Runnable() {
        public void run() {
            Counter.count++;
            Counter.ratcount++;
            Fire();
            spawnRat();
        }
    };
    public void spawnRat() {
        if(Counter.ratcount >= 50) {
            Rat r = new Rat(Math.random()*FullScreenSize.width, Math.random()*FullScreenSize.height,
                    Math.random()*20-10,Math.random()*20-10);
            listOfRat.add(r);
            Counter.ratcount=0;
        } else {
            Counter.ratcount++;
        }
        repaint();
    }
    public void Fire() {
        if(Counter.count >= 20) {
        character.fire();
        Counter.count =0;
        } else {
            ArrayList<Bullet> listOfBullet = character.getBullet();
            for(int i = 0; i < listOfBullet.size(); i ++) {
                Bullet b = listOfBullet.get(i);
                for(int j = 0; j < listOfRat.size(); j ++) {
                    Rat r = listOfRat.get(j);
                    if(Math.abs(b.getX()-r.getX()) <= 30 && Math.abs(b.getY()-r.getY()) <= 30) {
                        score += 10;
                        listOfRat.remove(j);
                    }
                    if(Math.abs(character.getX()-r.getX()) <= 50 && Math.abs(character.getY()-r.getY()) <= 50) {
                        hm.addScore("justin",score);
                        score =0;
                        //listOfBullet.clear();
                        listOfRat.clear();
                    }

                }
                b.move();
                if(b.getX() >= FullScreenSize.width || b.getX()<0 || b.getY() <0 || b.getY()>FullScreenSize.height) {
                    listOfBullet.remove(i);
                }
            }
            for(int i = 0; i < listOfRat.size(); i ++) {
                Rat r = listOfRat.get(i);
                r.move();
                if(r.getX() >= FullScreenSize.width || r.getX()<0 || r.getY() <0 || r.getY()>FullScreenSize.height) {
                    listOfRat.remove(i);
                }
            }
        }
        repaint();
    }
    public void actionPerformed(ActionEvent e) {
        character.move();
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(img,0,0,null);
        g2d.drawImage(character.getImage(), character.getX(), character.getY(),null);

        ArrayList<Bullet> listOfBullet = character.getBullet();
        for(int i = 0; i < listOfBullet.size(); i ++) {
            Bullet b = listOfBullet.get(i);
            g2d.drawImage(b.getImage(),b.getX(), b.getY(),null);
        }

        for(int i = 0; i < listOfRat.size(); i ++) {
            Rat r = listOfRat.get(i);
                g2d.drawImage(r.getImage(), r.getX(), r.getY(), null);
        }
        g2d.setFont(font);
        g2d.setColor(Color.BLUE);
        g2d.drawString("SCORE: " + score, FullScreenSize.width-500, 50);
        g2d.drawString("Highscore: " + hm.getHighscoreString(), FullScreenSize.width-500, 150);
    }
    //determines which key pressed
    private class AL extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            character.keyReleased(e);
        }
        public void keyPressed(KeyEvent e) {
            character.keyPressed(e);
        }
    }

}
