package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_cookieShield extends Entity{

    public OBJ_cookieShield(GamePanel gp){
        super(gp);

        type = type_shield;
        name = "Cookie Shield";
        down1 = setup("/objects/cookieShield", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = name + " -\n" + "A shield made \nfrom gingerbread";
    }

}
