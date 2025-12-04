package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainpkg.GamePanel;

public class OBJ_cleaner extends SuperObject{

    GamePanel gp;

    public OBJ_cleaner(GamePanel gp){

        this.gp=gp;

        name = "cleaner";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/cleaner.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }

    
    }

}
