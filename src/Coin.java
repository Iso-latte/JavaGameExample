/*
 * This class is the class made for wall items in the game
 * it sets a spawning var x
 * sets a spawning var y
 * sets a width 
 * sets a height
 * uses the items spawn x-cord to move with the camera
 */


// Used for different colors
// used for acting drawing rectangle on screen
import java.awt.*;  // used for creating rectangle

public class Coin{

    //Class variables
    int x;  // x value
    int y;  // y value
    int radius;  // width
    int startX; // starting get cord
    int cameraX;
    Color color;
    boolean hit;

    int value=1; //point value of a coin

    Rectangle hitBox;


    public Coin(int x, int y, int radius ,Color color){  // Constructor takes in a x cord, y cord, width, and height value when made

        this.x = x;  // sets x-axis center of circle
        this.y = y;  // sets y-axis center of circle
        startX = x;  // this will use the camera frame to ensure that the item doesn't change position when the player moves (Paradox)
        this.radius = radius;  //sets the width for the wall
        this.color = color;
        hitBox = new Rectangle(this.x-radius, y-radius, radius*2, radius*2);
    }

    public void drawCircle(Graphics gtd) {
        //gtd.setColor(Color.BLACK);
        //gtd.fillRect(this.x-this.radius, y-this.radius, this.radius*2, this.radius*2);
        if (this.hit == false){
        gtd.setColor(Color.DARK_GRAY);
        gtd.fillOval(this.x-this.radius+1, this.y-this.radius+1, 2*this.radius, 2*this.radius);
        gtd.setColor(this.color);
        gtd.fillOval(this.x-this.radius+2, this.y-this.radius+2, 2*(this.radius-2), 2*(this.radius-2));
        gtd.setColor(Color.DARK_GRAY);
        gtd.fillOval(this.x-this.radius+8, this.y-this.radius+8, 2*(this.radius-8), 2*(this.radius-8));
        gtd.setColor(Color.ORANGE);
        gtd.fillOval(this.x-this.radius+8, this.y-this.radius+8, 2*(this.radius-10), 2*(this.radius-10));
        }
        else if (this.hit = true){
            gtd.setColor(Color.LIGHT_GRAY);
            gtd.fillRect(this.x-this.radius, y-this.radius, this.radius*2, this.radius*2);
        }
    }

    public int set(int cameraX){
        x = startX - cameraX; // changes the x from the start to the camera movement
        hitBox.x = x; // sets the hitbox to x
        return x;

    }

    public void setHit(){
        this.hit = true;
    }

    public void hitFalse(){
        this.hit = false;
    }

    public boolean getHit(){
        return this.hit;
    }
    
    public int getValue(){
        return this.value;
    }

    public void setValue(){
        this.value = 1;
    }

}
