import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoutonFilmAction extends AbstractAction {
    public BoutonFilmAction(String texte){
        super(texte);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Vous avez cliqué sur Film");
    }
}