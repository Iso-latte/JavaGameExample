
 import javax.swing.JFrame;//For exit on close
 import java.awt.Dimension;//for getting screen size
 import java.awt.Toolkit; //For gettinf screen size
 
 
 public class App {
     public static void main(String[] args) {
         MainFrame frame = new MainFrame(); //Creates MainFrame from MainFrame.java This sets up the frame of the game and adds a panel to frame see file for info
 
         frame.setSize(700,700); //setting the size of the frame
 
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //Getting get screen size
         double height = (screenSize.getHeight()/2)-(frame.getSize().getHeight()/2); //setting up the height
         double width = (screenSize.getWidth()/2)-(frame.getSize().getWidth()/2); //setting up the width
         frame.setLocation((int)width,(int)height); //Now seting the frame to the location
 
         frame.setResizable(false); //We do not want the frame to be resizable
         frame.setTitle("Dodge A Block"); //Title of window
         frame.setVisible(true); //We can see it on the screen
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes the screen and stops the application
     }
 }