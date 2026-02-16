package object;

import entity.Projectile;
import mainpkg.GamePanel;

public class OBJ_Coal  extends Projectile{

    GamePanel gp;

    public OBJ_Coal(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Coal";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
        
    }
    public void getImage(){
        up1 = setup("projectile/coal1", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/coal2", gp.tileSize, gp.tileSize);
        down1 = setup("projectile/coal1", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/coal2", gp.tileSize, gp.tileSize);
        right1 = setup("projectile/coal1", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/coal2", gp.tileSize, gp.tileSize);
        left1 = setup("projectile/coal1", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/coal2", gp.tileSize, gp.tileSize);

    }



}
