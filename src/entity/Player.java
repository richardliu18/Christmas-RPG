package entity;

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

        setDefaultValues();
        getPlayerImage();
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

        up1=setup("player/up1");
        up2=setup("player/up2");
        down1=setup("player/down1");
        down2=setup("player/down2");
        right1=setup("player/right1");
        right2=setup("player/right2");
        left1=setup("player/left1");
        left2=setup("player/left2");
        
    }
 

    public void update(){

        if(keyH.upPressed==true || keyH.downPressed==true|| keyH.leftPressed==true||keyH.rightPressed==true){
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

            gp.keyH.enterPressed=false;
            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObj(objIndex);

            //if collision is false, player can move
            if(collisionOn==false){
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

    }
    public void pickUpObj(int i){
        if(i!=999){
           
           }
        }
    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    
    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum==1){
                    image=up1;
                }
                if(spriteNum==2){
                    image=up2;
                }
                    break;
            case "down":
                if(spriteNum==1){
                    image=down1;
                }
                if(spriteNum==2){
                    image=down2;
                }
                    break;
            case "right":
                if(spriteNum==1){
                    image=right1;
                }
                if(spriteNum==2){
                    image=right2;
                }
                break;
            case "left":
                if(spriteNum==1){
                    image=left1;
                }
                if(spriteNum==2){
                    image=left2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
