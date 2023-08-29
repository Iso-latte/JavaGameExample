
import java.awt.event.KeyAdapter;//Object that we look for key action
import java.awt.event.KeyEvent;//used for key events



public class KeyChecker extends KeyAdapter {

    GamePanel panel;//class variable we want this to be used thorought the class KeyChecker

    public KeyChecker(GamePanel panel){ //Constructor; this object gets passed what panel it is working on
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e){
        panel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e){
        panel.keyReleased(e);
    }

}
