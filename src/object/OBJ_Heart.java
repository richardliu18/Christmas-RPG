package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gp){

        super(gp);

        name = "Heart";

        image = setup("objects/fullHeart", gp.tileSize, gp.tileSize);
        image2 = setup("objects/halfHeart", gp.tileSize, gp.tileSize);
        image3 = setup("objects/noHeart", gp.tileSize, gp.tileSize);
    
    }

}
