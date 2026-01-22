package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_mintShield extends Entity{

    public OBJ_mintShield(GamePanel gp){
        super(gp);
        
        type = type_shield;
        name = "Mint Shield";
        down1 = setup("objects/mintShield", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = name + " -\n" + "A shield made \nfrom peppermint";
    }

}
