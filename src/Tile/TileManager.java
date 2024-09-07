package Tile;

import main.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTilenum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10]; //ten for testing as size
        getTileImage();
        mapTilenum = new int[gp.maxScreenRow][gp.maxScreenCol];
        loadMap();
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PLAYER_Sprite/grass.png")));
            tile[1] = new Tile();
            tile[1].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PLAYER_Sprite/water.png")));
            tile[2] = new Tile();
            tile[2].image =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/PLAYER_Sprite/wall.png")));




        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/PLAYER_Sprite/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            int col = 0;

            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                while (col < gp.maxScreenCol) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTilenum[row][col] = num;  // Swapped col and row here to store correctly
                    col++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;  // Reset column to 0 when you reach the end of the row
                    row++;    // Move to the next row
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (row < gp.maxScreenRow) {  // Traverse through rows
            while (col < gp.maxScreenCol) {  // Traverse through columns in each row

                int tileNum = mapTilenum[row][col];  // Get the tile number for this row and column

                // Draw the tile image at the correct x and y position
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);

                col++;
                x += gp.tileSize;  // Increment x to move to the next tile horizontally
            }

            col = 0;  // Reset column index for the next row
            x = 0;    // Reset x position for the next row
            y += gp.tileSize;  // Move down one tile vertically
            row++;
        }
    }

}
