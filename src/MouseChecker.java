
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseChecker implements MouseListener {

    GamePanel panel;

    public MouseChecker(GamePanel panel){
        this.panel = panel;
    }

    public void mousePressed(MouseEvent e) {
        ;
     }
 
     public void mouseReleased(MouseEvent e) {
        ;
     }
 
     public void mouseEntered(MouseEvent e) {
         ;
     }
 
     public void mouseExited(MouseEvent e) {
         ;
     }
 
     public void mouseClicked(MouseEvent e) {
        panel.mouseClicked(e);
     }


}
