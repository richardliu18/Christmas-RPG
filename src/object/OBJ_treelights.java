package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_treelights extends Entity{

    public OBJ_treelights(GamePanel gp){

        super(gp);

        name = "treelights";

        down1 = setup("objects/treelights");
    }
}
