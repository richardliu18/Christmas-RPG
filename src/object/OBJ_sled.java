package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_sled extends Entity{

    GamePanel gp;
    int value = 2;

    public OBJ_sled(GamePanel gp){

        super(gp);

        this.gp=gp;
        
        name = "Sled";
        type = type_consumable;
        down1 = setup("objects/sled", gp.tileSize, gp.tileSize);
        description = name + " -\n" + "Boosts speed";
    
    }
    public void use(Entity entity){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You hop on a "+name+ "!/n"+ "You have gained " + value + " speed";
        entity.speed += value;


    }
}
