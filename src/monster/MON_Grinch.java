package monster;

import java.util.Random;

import javax.swing.DefaultListCellRenderer;

import entity.Entity;
import mainpkg.GamePanel;
import object.OBJ_GrinchGoo;
import object.OBJ_Heart;
import object.OBJ_cane;
import object.OBJ_mana;

public class MON_Grinch extends Entity{

    GamePanel gp;

    public MON_Grinch(GamePanel gp) {
        
        super(gp);
        this.gp=gp;
        
        name = "Grinch";
        speed=1;
        maxLife=4;
        life=maxLife;
        type = type_monster;
        attack = 3;
        defense = 1;
        exp = 4;
        projectile = new OBJ_GrinchGoo(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height=30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage(){
        up1 = setup("/monster/grinch1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/grinch2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/grinch1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/grinch2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/grinch1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/grinch2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/grinch1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/grinch2", gp.tileSize, gp.tileSize);

    }
    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120){ // Every 2seconds

            Random random = new Random();
            int i = random.nextInt(100)+1; // pick a random number 1-100
    
            if(i<=25){
                direction = "up";
            }
            if(i>25 && i<=50){
                direction = "down";
            }
            if(i>50 && i<=75){
                direction = "left";
            }
            if(i>75 && i<=100){
                direction = "right";
            }
            actionLockCounter=0;
        }
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
        }

    int speedCounter = 0;
    int defaultSpeed;

    public void damageReaction(){
        speedCounter++;
        if(speedCounter==1){
            defaultSpeed = speed;
        }
        if(speedCounter>1 && speedCounter <= 20){
            actionLockCounter = 0;
            direction = gp.player.direction;
            speed = defaultSpeed+2;
        }
        if(speedCounter >=20){
            speed=defaultSpeed;
            speedCounter=0;
        }
    }
    public void checkDrop(){

        int i = new Random().nextInt(100)+1;

        if(i < 50){
            dropItem(new OBJ_cane(gp));
        }
        if(i >= 50 && i <=75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i>=75 && i<=100){
            dropItem(new OBJ_mana(gp));
        }
    }
}
