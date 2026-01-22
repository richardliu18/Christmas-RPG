package object;

import entity.Entity;
import mainpkg.GamePanel;

public class OBJ_present extends Entity{

    GamePanel gp;
    int value = 5;

    public OBJ_present(GamePanel gp){

    super(gp);
    this.gp = gp;
    type = type_consumable;
    name = "present";
    down1 = setup("objects/bigPresent", gp.tileSize, gp.tileSize);
    description = name + "-\n" + "A healing present";
    
    }
    public void use(Entity entity){

        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "You opened the "+name+ "!/n"+ "You have gained " + value + " life";
        entity.life += value;

        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }


    }
}
