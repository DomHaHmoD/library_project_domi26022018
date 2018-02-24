import javax.swing.*;
import java.awt.event.ActionEvent;

public class BoutonLitteratureAction extends AbstractAction {
    public BoutonLitteratureAction(String texte){
        super(texte);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Vous avez cliqué sur Littérature");
    }
}
