package IHM;

import javax.swing.*;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;




public class FQ extends JOptionPane {

    public FQ(){
        String[] options = {"Oui", "Non"};
        int choice = JOptionPane.showOptionDialog(null, "Voulez vous vraiment vous deconnecter?", "Deconnexion",0,
        JOptionPane.YES_NO_OPTION, null, options, null);
        if(choice == YES_OPTION)
            System.exit(0);
    }

}
