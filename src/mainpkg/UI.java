package mainpkg;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
    public String message = "";
    int messageCounter=0;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;//0:first screen, 1:second screen


  


    public UI(GamePanel gp){
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);


        //Create HUD object
        Entity heart = new OBJ_Heart(gp);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        noHeart = heart.image3;
        

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){

        this.g2 = g2;
        g2.setFont(arial_30);
        g2.setColor(Color.BLACK);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        
        
    
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
        int x;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x=gp.screenWidth/2 - length/2;
        return x;
    }
}
