package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_sled extends Entity{

    public OBJ_sled(GamePanel gp){

        super(gp);
        
        name = "sled";

        down1 = setup("objects/sled", gp.tileSize, gp.tileSize);

    
    }
}
