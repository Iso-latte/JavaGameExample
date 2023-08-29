
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class MessageBox extends JComponent{
    
    String s;
    int x;
    int y;

    public MessageBox(String s, int x, int y){
        
        this.s = s;
        this.x = x;
        this.y = y;

    }

    public void paint(Graphics2D gtd) {

        gtd.setColor(Color.RED);
        Font font = new Font("Dialog", Font.PLAIN, 50);
        gtd.setFont(font);
        gtd.drawString(this.s, this.x, this.y);

    }
    

    public void paintTitle(Graphics2D gtd) {

        //Font myFont = this.setFont("Vintage.ttf");

        //Setting up 
        int borderThickness = 20;
        int realBorderX = this.x ;
        int realBordery = this.y ;
        int width = 1000;
        int height = 150;

        
        gtd.setColor(Color.GRAY); // border color
        gtd.drawRect (realBorderX,realBordery, width, height);//MIGHT CHANGE
        gtd.fillRect(realBorderX, realBordery, width, height);
        gtd.setColor(Color.WHITE); // background color
        gtd.fillRect(realBorderX,realBordery+borderThickness,width-(2*borderThickness), height-(2*borderThickness)); //fills in the rectagnle

        //gtd.setFont(myFont);
        gtd.setColor(Color.BLACK);
        Font font = new Font("Serif", Font.BOLD, 50);
        gtd.setFont(font);
        gtd.drawString(this.s, 120, 190);
     }
}
