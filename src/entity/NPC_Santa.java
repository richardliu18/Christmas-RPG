package entity;


import java.util.Random;

import mainpkg.GamePanel;


public class NPC_Santa extends Entity{

    public NPC_Santa(GamePanel gp){

        super(gp);
        direction = "down";
        speed=1;

        getImage();
        setDialogue();

    }
    public void setDialogue(){
        dialogues[0] = "Ho, Ho, Ho";
        dialogues[1] = "Thank you for stoppin by to help";
        dialogues[2] = "I am super unc now and I need you /nto do things for me";
        dialogues[3] = "Have fun!";
    }
    public void speak(){

        super.speak();
        
    }
    public void getImage(){

        up1=setup("npc/santa/santaUp1");
        up2=setup("npc/santa/santaUp2");
        down1=setup("npc/santa/santaDown3");
        down2=setup("npc/santa/santaDown2");
        right1=setup("npc/santa/santaRight1");
        right2=setup("npc/santa/santaRight2");
        left1=setup("npc/santa/santaLeft1");
        left2=setup("npc/santa/santaLeft2");
        
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
        }
        

}
