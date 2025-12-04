package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainpkg.GamePanel;

public class OBJ_present extends SuperObject{

    GamePanel gp;

    public OBJ_present(GamePanel gp){

    this.gp=gp;
        
    name = "present";
    collision = true;

    try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/bigPresent.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }

    
    }
}
