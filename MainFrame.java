
import javax.swing.JFrame; //MainFrame inherenits from JFrame
import java.awt.Color;//used for seeting frame background color


public class MainFrame extends JFrame{

    public MainFrame(){
        GamePanel panelOne = new GamePanel(); //Creating GamePanel within the frame
        panelOne.setLocation(0,0);//setting location
        panelOne.setSize(this.getSize());//setting size same size as frame
        panelOne.setBackground(Color.LIGHT_GRAY);//setting background color
        panelOne.setVisible(true);//setting so it show up on the screen
        this.add(panelOne);//adding the created panel to the frame

        addKeyListener(new KeyChecker(panelOne));//this alows for binding the keys to actions

        addMouseListener(new MouseChecker(panelOne));
        
    }

}