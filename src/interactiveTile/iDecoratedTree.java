package interactiveTile;

import mainpkg.GamePanel;

public class iDecoratedTree extends InteractiveTile{

     GamePanel gp;

    public iDecoratedTree(GamePanel gp, int col, int row){

        super(gp, row, col);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles/dectree", gp.tileSize, gp.tileSize);
        destructible = true;
    }
}
