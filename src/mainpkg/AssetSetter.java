package mainpkg;

import entity.NPC_Santa;
import monster.MON_Grinch;
import object.OBJ_axe;
import object.OBJ_mintShield;
import object.OBJ_present;
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

      gp.obj[2] = new OBJ_axe(gp);
      gp.obj[2].worldX = gp.tileSize*11;
      gp.obj[2].worldY = gp.tileSize*8;

      gp.obj[3] = new OBJ_mintShield(gp);
      gp.obj[3].worldX = gp.tileSize*20;
      gp.obj[3].worldY = gp.tileSize*8;

      gp.obj[4] = new OBJ_present(gp);
      gp.obj[4].worldX = gp.tileSize*13;
      gp.obj[4].worldY = gp.tileSize*8;



    }
    public void setNPC(){
        gp.npc[0] = new NPC_Santa(gp);
        gp.npc[0].worldX = gp.tileSize*6;
        gp.npc[0].worldY = gp.tileSize*6;
    }
    public void setMonster(){

        int i = 0;
        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*13;
        gp.monster[i].worldY = gp.tileSize*13;
        i++;

        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*10;
        gp.monster[i].worldY = gp.tileSize*10;
        i++;

        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*12;
        gp.monster[i].worldY = gp.tileSize*12;
        i++;
        
        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*11;
        gp.monster[i].worldY = gp.tileSize*11;
        i++;

        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*10;
        gp.monster[i].worldY = gp.tileSize*11;
        i++;

        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*9;
        gp.monster[i].worldY = gp.tileSize*11;
        i++;

        gp.monster[i] = new MON_Grinch(gp);
        gp.monster[i].worldX = gp.tileSize*8;
        gp.monster[i].worldY = gp.tileSize*11;
        i++;

    }
}
