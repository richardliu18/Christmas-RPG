package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_mana extends Entity{

    GamePanel gp;

    public OBJ_mana(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickup;
        name = "Mana";
        value = 1;
        down1 = setup("/objects/manaFull", gp.tileSize, gp.tileSize);
        image = setup("/objects/manaFull", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/manaEmpty", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity){
        gp.playSE(5);
        gp.ui.addMessage("Mana + "+ value);
        entity.mana += value;
    }

}
