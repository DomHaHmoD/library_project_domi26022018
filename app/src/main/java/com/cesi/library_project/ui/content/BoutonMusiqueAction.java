import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoutonMusiqueAction extends AbstractAction {
    public BoutonMusiqueAction(String texte){
        super(texte);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Vous avez cliqué sur Musique");
    }
}
