
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;



public class IntroScreen extends JComponent{
    
    String title;
    int width;
    int height;
    int x;
    int y;

    ArrayList<Wall> introWalls = new ArrayList<>();  // a list for all the intro wall animation cordinates

    public IntroScreen(int x, int y){
        this.title = "DODGE A BLOCK";
    }

    public void paint(Graphics2D gtd) {

        int borderThickness = 20;

        //Setting up (Centering)
        int realBorderX = this.x+35;  // offset to make intro centered X-axis
        int realBordery = this.y+20;  // offset to make intro centered Y-axis
        int height = (int)(700 * 0.9); //setting up the height
        int width = (int)(700 * 0.9); //setting up the width


        // Background and border of intro screen
        gtd.setColor(Color.GRAY);  // border color
        gtd.drawRect(realBorderX,realBordery, width, height);
        gtd.fillRect(realBorderX,realBordery, width, height);
        gtd.setColor(Color.WHITE);  // border background color
        gtd.fillRect(realBorderX+borderThickness,realBordery+borderThickness,width-(2*borderThickness), height-(2*borderThickness)); //fills in the rectagnle controls bo
     }
}
