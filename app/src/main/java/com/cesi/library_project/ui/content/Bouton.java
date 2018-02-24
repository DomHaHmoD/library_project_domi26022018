package com.cesi.library_project.ui.content;

import javax.swing.*;
        import java.awt.*;
        import java.util.List;

public class Bouton {

    private void Bouton() {
    }

    public void addBtn(List<String> list, JPanel zone) {

        for (String item : list) {
            JButton addBtn = new JButton(item);
            addBtn.setPreferredSize(new Dimension (190, 40));
            zone.add(addBtn);
        }
    }
}