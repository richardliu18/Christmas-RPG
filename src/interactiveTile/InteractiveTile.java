package interactiveTile;

import entity.Entity;
import mainpkg.GamePanel;

public class InteractiveTile extends Entity{

    GamePanel gp;
    public boolean destructible = false;

    public InteractiveTile(GamePanel gp, int col, int row){
        super(gp);
        this.gp = gp;
    }
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }
    public void update(){

            if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 20){
                    invincible=false;
                    invincibleCounter=0;
                }
            }
    }
    public void playSE(){

    }
    public InteractiveTile getChangedForm(){
        InteractiveTile tile = null;
        return tile;
    }

}
