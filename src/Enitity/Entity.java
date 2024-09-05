package Enitity;

import java.awt.image.BufferedImage;

/**This will be the parent class for all entities like players and monsters*/

public class Entity {

    public int x,y;
    public int speed;

    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;



}
