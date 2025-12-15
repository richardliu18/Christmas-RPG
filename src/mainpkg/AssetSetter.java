package mainpkg;

import entity.NPC_Santa;
import object.OBJ_rock;
import object.OBJ_sled;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){
      gp.obj[0] = new OBJ_sled(gp);
      gp.obj[0].worldX = gp.tileSize*9;
      gp.obj[0].worldY = gp.tileSize*8;

      gp.obj[1] = new OBJ_rock(gp);
      gp.obj[1].worldX = gp.tileSize*10;
      gp.obj[1].worldY = gp.tileSize*8;



    }
    public void setNPC(){
        gp.npc[0] = new NPC_Santa(gp);
        gp.npc[0].worldX = gp.tileSize*6;
        gp.npc[0].worldY = gp.tileSize*6;
    }
}
