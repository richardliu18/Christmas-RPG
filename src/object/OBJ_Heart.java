package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_Heart extends Entity{

    GamePanel gp;

    public OBJ_Heart(GamePanel gp){

        super(gp);
        this.gp=gp;

        type = type_pickup;
        name = "Heart";
        value = 2;
        down1 = setup("objects/fullHeart", gp.tileSize, gp.tileSize);

        image = setup("objects/fullHeart", gp.tileSize, gp.tileSize);
        image2 = setup("objects/halfHeart", gp.tileSize, gp.tileSize);
        image3 = setup("objects/noHeart", gp.tileSize, gp.tileSize);
    
    }
    public void use(Entity entity){

        gp.playSE(5);
        gp.ui.addMessage("life + "+ value);
        entity.life += value;

    }

}
