package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_axe extends Entity{

    public OBJ_axe(GamePanel gp){
        super(gp);

        type = type_axe;
        name = "Axe";
        down1 = setup("objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 4;
        attackArea.width = 30;
        attackArea.height = 36;
        description = name + " -\n" + "Tree chopper";

    }

}
