package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_mana extends Entity{

    GamePanel gp;

    public OBJ_mana(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Mana";
        image = setup("/objects/manaFull", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manaEmpty", gp.tileSize, gp.tileSize);
    }

}
