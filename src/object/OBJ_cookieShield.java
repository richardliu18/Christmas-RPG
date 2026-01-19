package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_cookieShield extends Entity{

    public OBJ_cookieShield(GamePanel gp){
        super(gp);

        name = "Cookie Shield";
        down1 = setup("/objects/cookieShield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
    }

}
