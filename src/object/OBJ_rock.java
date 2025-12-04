package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainpkg.GamePanel;

public class OBJ_rock extends SuperObject{

    GamePanel gp;

    public OBJ_rock(GamePanel gp){

        this.gp=gp;

        name = "rock";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/rock.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
            
        }catch(IOException e){
            e.printStackTrace();
        }

    
    }
}
