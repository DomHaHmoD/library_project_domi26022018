package com.cesi.library_project.ui.content;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;


public class MainFrame extends JFrame {

    public JPanel container = new JPanel ();
    private JPanel NavBar = new JPanel ();
    private JPanel LeftBar = new JPanel ();
    private JPanel Content = new JPanel ();
    private JMenuBar menuBar = new JMenuBar ();
    private JTextField textField;

    public MainFrame() {
        super ();
        build (); //On initialise notre fenêtre
        frame (); //On crée les 3 zones

        List<String> test = Locale.Category.read ();
        /*
        Category.insert("test insertion2");
        Category.delete(9);
        Category.update(10, "TEST UPDATE");
        */
        Bouton btnList = new Bouton ();
        btnList.addBtn (test, LeftBar);

        //MENU
        //new CreationMenu(menuBar);
        //this.setJMenuBar(menuBar);

        //LISTE DEROULANTE
        //ListeDeroulante comboBox = new ListeDeroulante();
        //NavBar.add(comboBox);
        /*
        JScrollPane tableau = Oeuvre.CreateTab();
        Content.add(tableau);
*/
        javax.swing.SwingUtilities.invokeLater (new Runnable () {
            public void run() {
                TableFilterDemo newContentPane = new TableFilterDemo ();
                newContentPane.setOpaque (true); //content panes must be opaque
                Content.add (newContentPane);
            }
        });


    }

    public void build() {
        this.setTitle ("BIBLIOTHEQUE CESI MAIN FRAME");
        this.setExtendedState (Frame.MAXIMIZED_BOTH);
        this.setUndecorated (true);
        this.setBackground (Color.white);
        this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        pack ();
    }

    private void frame() {

        this.setContentPane (container);

        NavBar.setPreferredSize (new Dimension (1920, 100));
        NavBar.setBackground (Color.blue);

        LeftBar.setPreferredSize (new Dimension (220, 920));
        LeftBar.setBackground (Color.yellow);

        Content.setPreferredSize (new Dimension (1610, 920));
        Content.setBackground (Color.green);

        container.add (NavBar, BorderLayout.NORTH);
        container.add (LeftBar, BorderLayout.EAST);
        container.add (Content, BorderLayout.CENTER);

        container.setLayout (new FlowLayout ());

        JLabel label = new JLabel ("Bienvenue dans votre bibliothèque multimédia");
        NavBar.add (label);

        //ZONE DE SAISIE
        textField = new JTextField ();
        textField.setColumns (10);
        textField.setPreferredSize (new Dimension (290, 40));
        NavBar.add (textField);
        //Bouton Rechercher
        JButton recherche = new JButton ("RECHERCHER");
        NavBar.add (recherche);
    }
}
