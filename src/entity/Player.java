package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import mainpkg.GamePanel;
import mainpkg.KeyHandler;
import object.OBJ_Coal;
import object.OBJ_GrinchGoo;
import object.OBJ_cleaner;
import object.OBJ_cookieShield;
import object.OBJ_sled;
import object.OBJ_smokingPipe;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    // public int hasCleaner = 0;

    

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
    }


    public void setDefaultValues(){

        worldX=gp.tileSize*3; //player start position
        worldY=gp.tileSize*3;
        speed=4;
        defaultSpeed=4;
        direction="down";
        
        //player status
        maxLife = 10;
        life = maxLife;
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_smokingPipe(gp);
        currentShield = new OBJ_cookieShield(gp);
        attack = getAttack();
        defense = getDefense();
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        projectile = new OBJ_Coal(gp);
        // projectile = new OBJ_GrinchGoo(gp);
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength* currentWeapon.attackValue;
    }
    public int getDefense(){   
        return defense = dexterity*currentShield.defenseValue;
    }
    public void getPlayerImage(){

        up1=setup("player/up1", gp.tileSize, gp.tileSize);
        up2=setup("player/up2", gp.tileSize, gp.tileSize);
        down1=setup("player/down1", gp.tileSize, gp.tileSize);
        down2=setup("player/down2", gp.tileSize, gp.tileSize);
        right1=setup("player/right1", gp.tileSize, gp.tileSize);
        right2=setup("player/right2", gp.tileSize, gp.tileSize);
        left1=setup("player/left1", gp.tileSize, gp.tileSize);
        left2=setup("player/left2", gp.tileSize, gp.tileSize);
        
    }
    public void getPlayerAttackImage(){
        
        if(currentWeapon.type == type_pipe){
            attackUp1 = setup("/player/pipeAttackUp1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/pipeAttackUp2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/pipeAttackDown1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/pipeAttackDown2", gp.tileSize, gp.tileSize*2);
            attackRight1 = setup("/player/pipeAttackRight1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/pipeAttackRight2", gp.tileSize*2, gp.tileSize);
            attackLeft1 = setup("/player/pipeAttackLeft1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/pipeAttackLeft2", gp.tileSize*2, gp.tileSize);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setup("/player/axeAttackUp1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/player/axeAttackUp2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/player/axeAttackDown1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/player/axeAttackDown2", gp.tileSize, gp.tileSize*2);
            attackRight1 = setup("/player/axeAttackRight1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/player/axeAttackRight2", gp.tileSize*2, gp.tileSize);
            attackLeft1 = setup("/player/axeAttackLeft1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/player/axeAttackLeft2", gp.tileSize*2, gp.tileSize);
        }

    }
 

    public void update(){

        if(attacking ==true){
            attacking();
        }
        else if(keyH.upPressed==true || keyH.downPressed==true|| keyH.leftPressed==true||keyH.rightPressed==true||keyH.enterPressed==true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPressed == true){
                direction = "left";
            }
            else if(keyH.rightPressed == true){
                direction = "right";
            }

            //Check TILE collision
            collisionOn=false;
            gp.cChecker.checkTile(this);

            //check npc collision
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //check event
            gp.eHandler.checkEvent();

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObj(objIndex);

            //check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //if collision is false, player can move
            if(collisionOn==false && keyH.enterPressed ==false){
                switch(direction){
                    case"up":
                        worldY -= speed;
                        break;
                    case"down":
                        worldY += speed;
                        break;
                    case"right":
                        worldX += speed;
                        break;
                    case"left":
                        worldX -= speed;
                        break;

                }
            }
            if(keyH.enterPressed == true && attackCanceled == false){
                attacking=true;
                spriteCounter=0;
            }

            attackCanceled=false;
            gp.keyH.enterPressed=false;
            
            spriteCounter++;
            if(spriteCounter>15){
                if(spriteNum == 1){
                    spriteNum=2;
                }
                else if(spriteNum==2){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
    }
    if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter==30 && projectile.haveMana(this) == true){

        //set projectile
        projectile.set(worldX, worldY, direction, true, this);
        //add to list
        gp.projectileList.add(projectile);
        shotAvailableCounter=0;
        //subtract mana
        projectile.subtractMana(this);


    }
    //This needs to be outside key if statement
    if(invincible == true){
        invincibleCounter++;
        if(invincibleCounter > 50){
            invincible=false;
            invincibleCounter=0;
        }
    }
    if(shotAvailableCounter<30){
        shotAvailableCounter++;
    }
    }
    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum=1;
        }
        if(spriteCounter >5 && spriteCounter <=25){
            spriteNum=2;

            //save the current wolrdX, worldY, solidarea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust players worldx/y for attackArea
            switch(direction){
                case"up":worldY-=attackArea.height; break;
                case"down":worldY+=attackArea.height; break;
                case"left":worldX-=attackArea.width; break;
                case"right":worldX+=attackArea.width; break;
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            //check monster collision with the updated worldX and Y and solidarea
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height= solidAreaHeight;
            damageMonster(monsterIndex, attack);


        }
        if(spriteCounter >25){
            spriteNum=1;
            spriteCounter=1;
            attacking=false;
        }
    }
    public void pickUpObj(int i){

        if(i!=999){

        String text;

        if(inventory.size() != maxInventorySize){

            inventory.add(gp.obj[i]);
            gp.playSE(5);
            text = "Got a " + gp.obj[i].name + "!";
        }
        else{
            text = "You cannot carry anymore!";
        }
        gp.ui.addMessage(text);
        gp.obj[i] = null;
        }
    }
    public void interactNPC(int i){

        if(gp.keyH.enterPressed){
            if(i != 999){
                attackCanceled=true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
    
            }
        }
    public void contactMonster(int i){
        if(i!=999){
            if(invincible == false && gp.monster[i].dying == false){
                gp.playSE(4);
                int damage = gp.monster[i].attack - defense;
                if(damage<0){
                    damage=0;
                }
                if(speed>defaultSpeed){
                    speed -=2;
                    if(speed<2){
                        speed=2;
                    }
                }
                life-=damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack){
        if(i != 999){
            if(gp.monster[i].invincible==false){

                // int n = (int)(Math.random()*4)+6;
                // gp.playSE(n);

                int damage = attack - gp.monster[i].defense;
                if(damage<0){
                    damage=0;
                }
                gp.monster[i].life-=damage;
                gp.ui.addMessage(damage + " damage");

                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life<=0){
                    gp.monster[i].dying = true;
                    gp.playSE(5);
                    gp.ui.addMessage("Killed "+ gp.monster[i].name);
                    gp.ui.addMessage("Exp + "+ gp.monster[i].exp);
                    exp+=gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void setItems(){
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_cleaner(gp));
        inventory.add(new OBJ_sled(gp));
    }
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp=nextLevelExp*2;
            maxLife+=2;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You are level " + level + " now!";
        }
    }
    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()){

            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_pipe || selectedItem.type == type_axe){

                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){

                currentShield = selectedItem;
                defense = getDefense();
    
            }
            if(selectedItem.type == type_consumable){

                selectedItem.use(this);
                inventory.remove(itemIndex);

            }

        }
    }
    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        int tempScreenX=screenX;
        int tempScreenY=screenY;

        switch(direction){
            case "up":
                if(attacking==false){
                    if(spriteNum==1){image=up1;}
                    if(spriteNum==2){image=up2;}
                }
                if(attacking==true){
                    tempScreenY=screenY-gp.tileSize;
                    if(spriteNum==1){image=attackUp1;}
                    if(spriteNum==2){image=attackUp2;}    
                }
                    break;
            case "down":
                if(attacking==false){
                    if(spriteNum==1){image=down1;}
                    if(spriteNum==2){image=down2;}
                }
                if(attacking==true){
                    if(spriteNum==1){image=attackDown1;}
                    if(spriteNum==2){image=attackDown2;}    
                }
                    break;
            case "right":
            if(attacking==false){
                if(spriteNum==1){image=right1;}
                if(spriteNum==2){image=right2;}
            }
            if(attacking==true){
                if(spriteNum==1){image=attackRight1;}
                if(spriteNum==2){image=attackRight2;}    
            }
                break;
            case "left":
            if(attacking==false){
                if(spriteNum==1){image=left1;}
                if(spriteNum==2){image=left2;}
            }
            if(attacking==true){
                tempScreenX=screenX-gp.tileSize;
                if(spriteNum==1){image=attackLeft1;}
                if(spriteNum==2){image=attackLeft2;}    
            }
                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));//reset to normal after invincible
        
        //debug
        // g2.setFont(new Font("Arial", Font.PLAIN, 26));
        // g2.setColor(Color.black);
        // g2.drawString("Invincible: "+invincibleCounter, gp.tileSize, gp.tileSize*5);
    }
}
