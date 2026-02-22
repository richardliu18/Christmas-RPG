package object;

import entity.Entity;
import entity.Projectile;
import mainpkg.GamePanel;

public class OBJ_GrinchGoo extends Projectile{


    GamePanel gp;

    public OBJ_GrinchGoo(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Grinch Goo";
        speed = 4;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
        
    }
    public void getImage(){
        up1 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        up2 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        down1 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        down2 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        right1 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        right2 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        left1 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
        left2 = setup("projectile/grinchGoo", gp.tileSize, gp.tileSize);
    }
    public boolean haveMana(Entity user){

        boolean haveAmmo = false;
        if(user.ammo >= useCost){
            haveAmmo = true;
        }

        return haveAmmo;
    }
    public void subtractMana(Entity user){
        user.ammo -= useCost;
    }
}

