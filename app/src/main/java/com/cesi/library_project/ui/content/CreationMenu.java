import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationMenu extends JFrame {
    private JMenu test1 = new JMenu("Fichier");
    private JMenu test1_2 = new JMenu("Sous ficher");
    private JMenu test2 = new JMenu("Edition");

    private JMenuItem item1 = new JMenuItem("Ouvrir");
    private JMenuItem item2 = new JMenuItem("Fermer");
    private JMenuItem item3 = new JMenuItem("Lancer");
    private JMenuItem item4 = new JMenuItem("Arrêter");

    private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Choix 1");
    private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Choix 2");

    private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Radio 1");
    private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Radio 2");

    public CreationMenu(JMenuBar menuBar) {
        //On initialise nos menus
        test1.add(item1);

        //On ajoute les éléments dans notre sous-menu
        test1_2.add(jcmi1);
        test1_2.add(jcmi2);
        //Ajout d'un séparateur
        test1_2.addSeparator();
        //On met nos radios dans un ButtonGroup
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrmi1);
        bg.add(jrmi1);
        //On présélectionne la première radio
        jrmi1.setSelected(true);

        test1_2.add(jrmi1);
        test1_2.add(jrmi2);

        //Ajout du sous-menu dans notre menu
        test1.add(test1_2);
        //Ajout d'un séparateur
        test1.addSeparator();
        item2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        test1.add(item2);
        test2.add(item3);
        test2.add(item4);

        //L'ordre d'ajout va déterminer l'ordre d'apparition dans le menu de gauche à droite
        //Le premier ajouté sera tout à gauche de la barre de menu et inversement pour le dernier
        menuBar.add(test1);
        menuBar.add(test2);
    }

}