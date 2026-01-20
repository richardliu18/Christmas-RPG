package mainpkg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

public class UI {

    Graphics2D g2;
    GamePanel gp;
    Font arial_30;
    BufferedImage fullHeart, halfHeart, noHeart;
    public boolean messageOn = false;
    // public String message = "";
    // int messageCounter=0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;//0:first screen, 1:second screen
    public int slotCol = 0;
    public int slotRow = 0;


    public UI(GamePanel gp){
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);


        //Create HUD object
        Entity heart = new OBJ_Heart(gp);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        noHeart = heart.image3;
        

    }
    public void addMessage(String text){

        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(arial_30);
        g2.setColor(Color.BLACK);

        //title state
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //play state
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        //pause state
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //dialogue state
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //character state
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        
    
    }
    public void drawInventory(){
        
        //Frame
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        for(int i=0; i<gp.player.inventory.size(); i++){

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;
            
            if(i == 4 || i == 9|| i==14){
                slotX = slotXstart;
                slotY += slotSize;
            }
            
            
        }

        //CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        //draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //Desciption frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight ;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        //Description text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){
            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")){
                g2.drawString(line, textX, textY);
                textY+=32;
            }        
        }

        
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol+(slotRow*5);
        return itemIndex;
    }
    public void drawPlayerLife(){
        
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //max life
        while(i < gp.player.maxLife/2){
            g2.drawImage(noHeart, x, y, null);
            i++;
            x+=gp.tileSize;
        }
        //reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        //current life
        while(i < gp.player.life){
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if(i<gp.player.life){
                g2.drawImage(fullHeart, x, y, null);
            }
            i++;
            x+=gp.tileSize;
        }
    }
    public void drawMessage(){
        
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*10;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));

        for(int i=0; i<message.size(); i++){

            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++
                messageCounter.set(i, counter);//set counter to the array
                messageY+=25;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }


        }
    }
    public void drawTitleScreen(){

        //background color;
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        if(titleScreenState == 0){
        //Title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "A Christmas Game";
        int x = getXforCenterText(text);
        int y = gp.tileSize*3;

        //shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);

        //Main color
        g2.setColor(new Color(180, 10,10));
        g2.drawString(text, x, y);

        //snowman image
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        //menu
        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenterText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    else if(titleScreenState == 1){

        //MODE selection screen;
        g2.setColor(new Color(180, 10,10));


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "Select the Mode";
        int x = getXforCenterText(text);
        int y = gp.tileSize*2;

        //shadow
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+5, y+5);
        //Main color
        g2.setColor(new Color(180, 10,10));
        g2.drawString(text, x, y);

        

        //snowman
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(42F));

        text = "SOLO";
        x = getXforCenterText(text);
        y += gp.tileSize*5;
        g2.drawString(text, x, y);
        if(commandNum==0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "DUOS";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum==1){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Back";
        x = getXforCenterText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum==2){
            g2.drawString(">", x-gp.tileSize, y);
        }

    }
}
    public void drawDialogueScreen(){

        // window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;
        drawSubWindow(x,y,width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize/2;
        y += gp.tileSize;

        for(String line : currentDialogue.split("/n")){
        g2.drawString(line, x, y);
        y+=40;
        }

    }
    public void drawCharacterScreen(){
        
        //Create a frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 32;

        //names
        g2.drawString("Level", textX, textY);
        textY +=lineHeight;
        g2.drawString("Life", textX, textY);
        textY+= lineHeight;
        g2.drawString("Stength", textX, textY);
        textY +=lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY+= lineHeight;
        g2.drawString("Attack", textX, textY);
        textY+= lineHeight;
        g2.drawString("Defense", textX, textY);
        textY +=lineHeight;
        g2.drawString("Exp", textX, textY);
        textY+= lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY +=lineHeight;
        g2.drawString("Coin", textX, textY);
        textY+= lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY +=lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY+= lineHeight;

        //Values
        int tailX = (frameX + frameWidth) - 30;
        //Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAligntoRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY+= lineHeight;

        //weapon and shield image
        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-13, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-13, null);


    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);

    }
    public void drawPauseScreen(){


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);

    }
    public int getXforCenterText(String text){
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x=gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAligntoRightText(String text, int tailX){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}
