package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_cane extends Entity{

    GamePanel gp;

    public OBJ_cane(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickup;
        name = "Cane";
        down1 = setup("/objects/cane", gp.tileSize, gp.tileSize);
        value = 1;
    }

    public void use(Entity entity){

        gp.ui.addMessage("Cane + "+ value);
        gp.player.coin += value;
    }
}
