
/*
 * This class is the class made for wall items in the game
 * it sets a spawning var x
 * sets a spawning var y
 * sets a width 
 * sets a height
 * uses the items spawn x-cord to move with the camera
 */


import java.awt.Color;  // Used for different colors
import java.awt.Rectangle;  // used for creating rectangle
import java.util.ArrayList;
import java.awt.Graphics2D;  // used for acting drawing rectangle on screen

public class Wall {

    //Class variables
    int x;  // x value
    int y;  // y value
    int width;  // width
    int height;  // height
    int startX; // starting get cord
    int cameraX;

    ArrayList<Wall> walls = new ArrayList<>(); // this will be returned to be used in GamePanel

    Rectangle hitBox;  // for creating rectangle object

    public Wall(int x, int y, int width, int height){  // Constructor takes in a x cord, y cord, width, and height value when made

        this.x = x;  // sets x-axis for the wall block
        this.y = y;  // sets y-axis for the wall block
        startX = x;  // this will use the camera frame to ensure that the item doesn't change position when the player moves (Paradox)
        this.width = width;  //sets the width for the wall
        this.height = height;  // sets the height for the wall
        hitBox = new Rectangle(x, y, width, height);  // creates rectangle with args

    }

    public void draw(Graphics2D gtd){  // draw method takes a Graphics2D object for arg
        gtd.setColor(Color.BLACK);  // sets color for border
        gtd.drawRect(x,y,width,height);  // draws border
        gtd.setColor(Color.WHITE);  // sets color to white
        gtd.fillRect(x+1,y+1,width-2, height-2);  // fills in the rectagnle
    }

    public void drawBrick(Graphics2D gtd){  // draw method takes a Graphics2D object for arg
        Color darkRed = new Color(178, 23, 18);
        gtd.setColor(Color.WHITE);  // sets color for border
        gtd.fillRect(x,y-3,width,height+4);  // draws border
        gtd.setColor(darkRed);  // sets color to white
        gtd.fillRect(x+1,y+1,width-5, height-5);  // fills in the rectagnle
    }

    public int set(int cameraX){
        x = startX - cameraX; // changes the x from the start to the camera movement
        hitBox.x = x; // sets the hitbox to x
        return x;

    }

    //These functions use the list to generate walls for the program
    public void makeFloor(int xrange){
        //takes in xaxis (length of wall) and generates a floor
        for(int i = 0; i<=xrange; i+=100){
            walls.add(new Wall(i, 600, 100,100));
        }
    }
    public void makeTower(int xpoint, int towerHeight){
        // takes a x point and creates a tower # of blocks high
        for(int i = 0; i < (towerHeight*100); i+=100){
            walls.add(new Wall(xpoint, 600-i, 100, 100));
        }
    }
    public void makeFloorGlitch(int xpoint){
        int i;
        for(i = xpoint; i <=xpoint+400; i+=100){
            walls.add(new Wall(i, 600,100, 100));
        }

        walls.add(new Wall(i+200, 600, 100, 100));
        walls.add(new Wall(i+300, 600, 100, 100));

        walls.add(new Wall(i+600, 600, 100, 100));

        walls.add(new Wall(i+1000, 600, 100, 100));

        walls.add(new Wall(i+1300, 600, 100, 100));
        walls.add(new Wall(i+1400, 600, 100, 100));

        //Create the glitch here space 100, two blocks, space 100, one block, space 200, one block, space 200, 2 block, 

    }
    public void populateWalls(){
        int x = 5000;
        makeFloor(x);
        makeFloorGlitch(x+100);
        makeTower(7300, 2);
        makeTower(7400, 2);
        makeTower(7600, 3);
        makeTower(7700, 3);
        makeTower(8000, 4);
        makeTower(8100, 4);



    }

    //public void makeMultipleTowers(int startX, int xspace, int towerHeight){
    //    //TODO: use make tower function to create multiple towers
    //   int i = 0;
    //}

    public ArrayList<Wall> returnList(){
        return walls;
    }
}
