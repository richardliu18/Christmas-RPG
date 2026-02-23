package mainpkg;

import entity.NPC_Santa;
import interactiveTile.iTree;
import monster.MON_Grinch;
import object.OBJ_Heart;
import object.OBJ_axe;
import object.OBJ_cane;
import object.OBJ_mana;
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
    
        int i = 0;
      gp.obj[i] = new OBJ_sled(gp);
      gp.obj[i].worldX = gp.tileSize*9;
      gp.obj[i].worldY = gp.tileSize*8;
        i++;

      gp.obj[i] = new OBJ_rock(gp);
      gp.obj[i].worldX = gp.tileSize*10;
      gp.obj[i].worldY = gp.tileSize*8;
      i++;

      gp.obj[i] = new OBJ_axe(gp);
      gp.obj[i].worldX = gp.tileSize*11;
      gp.obj[i].worldY = gp.tileSize*8;
      i++;

      gp.obj[i] = new OBJ_mintShield(gp);
      gp.obj[i].worldX = gp.tileSize*20;
      gp.obj[i].worldY = gp.tileSize*8;
      i++;

      gp.obj[i] = new OBJ_present(gp);
      gp.obj[i].worldX = gp.tileSize*13;
      gp.obj[i].worldY = gp.tileSize*8;
      i++;

      gp.obj[i] = new OBJ_cane(gp);
      gp.obj[i].worldX = gp.tileSize*14;
      gp.obj[i].worldY = gp.tileSize*8;
      i++;

      gp.obj[i] = new OBJ_cane(gp);
      gp.obj[i].worldX = gp.tileSize*15;
      gp.obj[i].worldY = gp.tileSize*8;
      i++;

      gp.obj[i] = new OBJ_mana(gp);
      gp.obj[i].worldX = gp.tileSize*15;
      gp.obj[i].worldY = gp.tileSize*9;
      i++;


      gp.obj[i] = new OBJ_mana(gp);
      gp.obj[i].worldX = gp.tileSize*15;
      gp.obj[i].worldY = gp.tileSize*10;
      i++;

      gp.obj[i] = new OBJ_Heart(gp);
      gp.obj[i].worldX = gp.tileSize*15;
      gp.obj[i].worldY = gp.tileSize*11;
      i++;


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
    public void setInteractiveTile(){

        int i = 0;
        gp.iTile[i] = new iTree(gp, 14,8);
    }
}
