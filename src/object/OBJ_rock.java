package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_rock extends Entity{


    public OBJ_rock(GamePanel gp){

        super(gp);

        name = "rock";
        down1 = setup("objects/rock", gp.tileSize, gp.tileSize);


    
    }
}
