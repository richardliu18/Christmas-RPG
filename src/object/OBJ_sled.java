package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_sled extends Entity{

    public OBJ_sled(GamePanel gp){

        super(gp);
        
        name = "Sled";

        down1 = setup("objects/sled", gp.tileSize, gp.tileSize);
        description = name + " -\n" + "Boosts speed";
    
    }
}
