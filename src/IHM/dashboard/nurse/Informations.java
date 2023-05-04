package IHM.dashboard.nurse;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BDD.bdd;
import IHM.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Informations extends JPanel {
    private JLabel title;
    private JPanel tabPanel;
    public static JButton editBtn;
   

  
   
    public static JFrame F;

    public Informations(JFrame frame) {
        bdd db = new bdd();
        setLayout(null);

        ImageIcon bg = new ImageIcon("./img/bgP.jpg");
        JLabel lab = new JLabel(bg);
        lab.setBounds(0, 0, 1200, 800);

        this.F = frame;
        title = new JLabel("Informations");
        title.setFont(new Font("monospace", Font.BOLD, 25));
        title.setBounds(450, 20, 200, 50);
        
        editBtn = new JButton("Modifier");
        editBtn.setBounds(1000, 200, 150, 70);
        editBtn.setFont(new Font("Arial", Font.BOLD, 20));
        editBtn.setBackground(new Color(0X011b45));
        editBtn.setForeground(Color.WHITE);
        editBtn.setFocusable(false);
        // editBtn.addActionListener(e -> {
        //     new EditNurse(id,username,mdps, frame );
        //     }
        add(title);
        //add(editBtn);
        add(lab);

        setSize(1200, 800);
    }
}

