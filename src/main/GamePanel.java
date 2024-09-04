package main;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 16; //16x16 tiles
    //moedern computers have much higher resoltion, so 16x16 may look very small inside
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread; //when you want to repeat drawing the screen again and again, threads can be extremely useful




    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {


        //the game loop exists here
    }
}
