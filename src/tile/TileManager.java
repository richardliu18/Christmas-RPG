package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import mainpkg.GamePanel;
import mainpkg.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp=gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/worldMap01.txt");

    }
     public void getTileImage(){
    
            setup(0, "snow", false);
            setup(1, "snowtree", true);
            setup(2, "cleantree", true);
            setup(3, "dectree", true);
            setup(4, "ice", true);
            setup(5, "present", false);

        }

        public void setup(int index, String imageName, boolean collision){
            UtilityTool uTool = new UtilityTool();
            try{
                tile[index] = new Tile();
                tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/"+imageName + ".png"));
                tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
                tile[index].collision = collision;

            }catch(IOException e){
                e.printStackTrace();
            }


        }
        public void loadMap(String filePath){
            try{
                InputStream is = getClass().getResourceAsStream(filePath);
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
               
                int col=0;
                int row=0;
                while(col<gp.maxWorldCol&&row<gp.maxWorldRow){

                    String line = br.readLine(); // make a line into a string

                    while(col<gp.maxWorldCol){

                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]); //go from string to integer

                        mapTileNum[col][row] = num;//store

                        col++;//move
                    }
                    if(col==gp.maxWorldCol){
                        col=0;
                        row++;//next row
                    }
                }
                br.close();
            }catch(Exception e){

            }
        }
        public void draw(Graphics2D g2){
            // g2.drawImage(tile[0].image, 0,0,gp.tileSize, gp.tileSize, null);
            // g2.drawImage(tile[1].image, 48,0,gp.tileSize, gp.tileSize, null);
            // g2.drawImage(tile[2].image, 96,0,gp.tileSize, gp.tileSize, null);
            // g2.drawImage(tile[3].image, 144,0,gp.tileSize, gp.tileSize, null);

            int worldCol=0;
            int worldRow=0;
        

            while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){

                int tileNum=mapTileNum[worldCol][worldRow];

                int worldX = worldCol*gp.tileSize;
                int worldY = worldRow*gp.tileSize;
                int screenX = worldX-gp.player.worldX+gp.player.screenX;
                int screenY = worldY-gp.player.worldY+gp.player.screenY;

                if(worldX + gp.tileSize>gp.player.worldX - gp.player.screenX && 
                worldX -gp.tileSize< gp.player.worldX+gp.player.screenX && 
                worldY + gp.tileSize>gp.player.worldY-gp.player.screenY&&
                worldY - gp.tileSize<gp.player.worldY+gp.player.screenY){

                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
                

                worldCol++;
                
                if(worldCol == gp.maxWorldCol){
                    worldCol=0;
                   
                    worldRow++;
                }
                
            }

     }

    
}
