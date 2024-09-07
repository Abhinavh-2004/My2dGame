package main;

import Enitity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTileSize = 16; //16x16 tiles
    //moedern computers have much higher resoltion, so 16x16 may look very small inside
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);

    Thread gameThread; //when you want to repeat drawing the screen again and again, threads can be extremely useful

    KeyHandler keyH = new KeyHandler();

    Player player = new Player(this,keyH);




    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {


        //we use the sleep method for the game loop
        double drawInterval = 1000000000/FPS; //0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval; //if the system time htis this time, raw the screeen again is the approach

        //the game loop exists here
        while(gameThread != null){
            //long currentTime = System.nanoTime(); //nano is a very small unit time, perfect for the purpose of a fast enough perception interval
            //System.out.println(currentTime);
            //System.out.println("The game loop is running");






            //I update information such as character position
            //Draw the screen with the updated information
            update();
            //the update and draw needs an interval to percieve and work
            //find out the time now and also set the interval

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; //now this is milliseconds
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            /**As long as the game loop continues, it can keep updating and repainting*/


        }
    }
    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //the parent class is Jpanel
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2); //the rectangle is just to check if it prints a thing on the window
        g2.dispose(); //when drawing is done then dispose memory
        /**To move the rectangel, we need keyboard input {dfound in KeyHandler class}*/


    }
}
