package it.polimi.se2019.view;

import javax.swing.*;
import java.awt.*;

public class AmmoTileViewerGUI {


    public AmmoTileViewerGUI(String content){

        JPanel body = new JPanel(new BorderLayout(8, 8));
        ImageIcon pic = new ImageIcon(new ImageIcon("src/main/sprite/ammo/ammo_"+ content +".png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
        JLabel image = new JLabel(pic);
        image.setIcon(pic);

        body.add(new JLabel("You picked this drop:"), BorderLayout.NORTH);
        body.add(image, BorderLayout.SOUTH);



        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setTitle("Drop desclaimer");
        dialog.getContentPane().add(body);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }


}
