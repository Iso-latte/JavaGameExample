
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;


public class Player { //THIS IS OUR PLAYER YAY!
    
    //CLASS VARIABLES
    GamePanel panel;  // the panel that the charater will appear on

    int x;  // starting x cord
    int y;  // starting y cord

    int width;  // width of char
    int height;  // height of char
    
    Color babyBlue;

    double xspeed;
    double yspeed;

    Rectangle hitBox;

    boolean keyLeft;
    boolean keyRight;
    boolean keyUp;
    boolean keyDown;

    int points;


    //CONSTRUCTOR: Sets up player given x and y and the panel its on
    public Player(int x, int y, GamePanel panel){

        this.panel = panel; // The panel that the character spawns in "See GamePanel.java for more info"
        this.x = x;
        this.y = y;

        // SETS UP HITBOX OF CHARACTER
        width = 75; // size of the HITBOX
        height = 75;  // size of HITBOX
        hitBox = new Rectangle(x, y, width, height); //This is the hitbox for the character

        babyBlue = new Color(137,207,240);
    }

    public void set(){

        xspeed ++;

        if (xspeed>5){ 
            xspeed = 5;
        }



        if(keyUp){

            hitBox.y +=1;
            //cycles walls
            for (Wall wall: panel.walls){
                if(wall.hitBox.intersects(hitBox)) yspeed =-8;
            }
        
            //for (Coin coin: panel.coins){
            //    if(coin.hitBox.intersects(hitBox)) yspeed =-8;
            //}

        }

        yspeed+=0.2;  // HOW QUICKLY THE CHARACTER FALLS BACK DOWN (THE HIGHER, THE STRONGER GRAVITY)

        //Horizaontal Collusion
        hitBox.x += xspeed; //We need the hitbox to be following our character at all time
        for(Wall wall: panel.walls){ //cycling through all the walls in the game panel
            if(hitBox.intersects(wall.hitBox)){ //checking whether our hit box intersects with the wall's 
                hitBox.x -=xspeed; //cancel out the speed
                while(!wall.hitBox.intersects(hitBox)) hitBox.x += Math.signum(xspeed);  
                hitBox.x -=Math.signum(xspeed);
                panel.cameraX += x - hitBox.x;
                xspeed = 0;
                hitBox.x = x;
            }
        }


        //Verticle Collusion

        hitBox.y += yspeed;
        for(Wall wall: panel.walls){
            if(hitBox.intersects(wall.hitBox)){
                hitBox.y -=yspeed;
                while(!wall.hitBox.intersects(hitBox)) hitBox.y += Math.signum(yspeed);  
                hitBox.y -=Math.signum(yspeed);
                yspeed = 0;
                y = hitBox.y;
            }
        }



        //HIT BOX COLLISION FOR COINS

        //hitBox.x += xspeed; //We need the hitbox to be following our character at all time
        for(Coin coin: panel.coins){ //cycling through all the walls in the game panel
            if(hitBox.intersects(coin.hitBox)){ //checking whether our hit box intersects with the wall's 
                if (coin.getHit() == false){
                    coin.setHit();
                    points+=coin.value;
                }

            }
        }


        panel.cameraX += xspeed; //This will change the camera based on the characters x-axis
        y += yspeed;


        hitBox.x = x;
        hitBox.y = y;

        //Death Code
        if (y>800) panel.reset();
    }

    
    public void drawRight(Graphics2D gtd){
        gtd.setColor(Color.BLACK);
        gtd.fillRect(x,y,width,height);
        gtd.setColor(Color.WHITE);
        gtd.fillRect(x+23,y+30,30, 27);
        gtd.setColor(babyBlue);
        gtd.fillRect(x,y+7,width,10);
    }

    public void setPoints(int points){
        this.points = 0;
    }

    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }

    public int getPoints(){
        return this.points;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

}
