package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainpkg.GamePanel;

public class OBJ_sled extends SuperObject{

    GamePanel gp;

    public OBJ_sled(GamePanel gp){

        this.gp=gp;
        
        name = "sled";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sled.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }

    
    }
}
