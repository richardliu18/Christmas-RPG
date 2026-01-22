package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_smokingPipe extends Entity{

    public OBJ_smokingPipe(GamePanel gp){
        super(gp);

        type = type_pipe;
        name = "Smoking Pipe";
        down1 = setup("objects/smokingPipe",gp.tileSize, gp.tileSize);
        attackValue=2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = name + " -\n" + "Frosty's pipe";

        
    }
}
