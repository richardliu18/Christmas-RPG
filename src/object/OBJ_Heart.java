package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_Heart extends Entity{

    public OBJ_Heart(GamePanel gp){

        super(gp);

        name = "Heart";

        image = setup("objects/fullHeart");
        image2 = setup("objects/halfHeart");
        image3 = setup("objects/noHeart");
    
    }

}
