package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import mainpkg.GamePanel;

public class OBJ_treelights extends SuperObject{

    GamePanel gp;

    public OBJ_treelights(GamePanel gp){

        this.gp=gp;

        name = "treelights";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/treelights.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
