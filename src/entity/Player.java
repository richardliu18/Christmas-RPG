package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import mainpkg.GamePanel;
import mainpkg.KeyHandler;

public class Player extends Entity {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

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
    }


    public void setDefaultValues(){

        worldX=gp.tileSize*3; //player start position
        worldY=gp.tileSize*3;
        speed=4;
        direction="down";
        
        //player status
        maxLife = 10;
        life = maxLife;


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

        attackUp1 = setup("/player/attackUp1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/player/attackUp2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/player/attackDown1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/player/attackDown2", gp.tileSize, gp.tileSize*2);
        attackRight1 = setup("/player/attackRight1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/player/attackRight2", gp.tileSize*2, gp.tileSize);
        attackLeft1 = setup("/player/attackLeft1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/player/attackLeft2", gp.tileSize*2, gp.tileSize);
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
    //This needs to be outside key if statement
    if(invincible == true){
        invincibleCounter++;
        if(invincibleCounter > 60){
            invincible=false;
            invincibleCounter=0;
        }
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
            damageMonster(monsterIndex);


        }
        if(spriteCounter >25){
            spriteNum=1;
            spriteCounter=1;
            attacking=false;
        }
    }
    public void pickUpObj(int i){
        if(i!=999){
           
           }
        }
    public void interactNPC(int i){

        if(gp.keyH.enterPressed){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else{
                if(gp.keyH.enterPressed==true){
                    attacking = true;
                }
            }
        }
        
    }
    public void contactMonster(int i){
        if(i!=999){
            if(invincible == false){
            life-=1;
            invincible = true;
            }
        }
    }
    public void damageMonster(int i){
        if(i != 999){
            if(gp.monster[i].invincible==false){

                gp.monster[i].life-=1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life<=0){
                    gp.monster[i]=null;
                }
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
