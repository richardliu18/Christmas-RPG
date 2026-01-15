package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_cleaner extends Entity{

    public OBJ_cleaner(GamePanel gp){

        super(gp);

        name = "cleaner";
        down1 = setup("objects/cleaner", gp.tileSize, gp.tileSize);

    
    }

}
