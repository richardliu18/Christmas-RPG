package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_present extends Entity{

    
    public OBJ_present(GamePanel gp){

    super(gp);
    name = "present";
    collision = true;

    down1 = setup("objects/bigPresent");
    }
}
