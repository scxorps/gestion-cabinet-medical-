package IHM.dashboard.doctor;

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

public class Privé extends JPanel {
    private JLabel title;
    //private JTabbedPane tabbedPane;
    //private JPanel dayPanel;
    //private JPanel weekPanel;
    //private JPanel monthPanel;
    public static JButton sumBtn;
    public static JButton printBtn;
   
    private JLabel dayLabel;
    private JLabel weekLabel;
    private JLabel monthLabel;
    //private JLabel passwordLabel;
    //private JLabel ageLabel;

    private JTextField dayField;
    private JTextField weekField;
    private JTextField monthField;
    //private JPasswordField passwordField;
    //private JTextField ageField;


  
   
    public static JFrame F;

    public Privé(JFrame frame) {
        bdd db = new bdd();
        setLayout(null);

        ImageIcon bg = new ImageIcon("./img/bgP.jpg");
        JLabel lab = new JLabel(bg);
        lab.setBounds(0, 0, 1200, 800);

        this.F = frame;
        title = new JLabel("Caisse");
        title.setFont(new Font("monospace", Font.BOLD, 25));
        title.setBounds(520, 210, 200, 30);
        
        //dayPanel = new JPanel();

        dayLabel = new JLabel("Total /Jour:");
        dayLabel.setFont(new Font("monospace", Font.BOLD, 15));
        dayLabel.setBounds(420, 270, 200, 30);

        dayField = new JTextField();
        dayField.setBounds(550, 270, 200, 30);
        dayField.setEditable(false);
        dayField.setFont(new Font("monospace", Font.BOLD, 15));
        dayField.setHorizontalAlignment(JTextField.CENTER);
        dayField.setBorder(new LineBorder(Color.black, 1));
        dayField.setBackground(Color.white);
        dayField.setForeground(Color.gray);

        //dayPanel.add(dayLabel);
        //dayPanel.add(dayField);
        //dayPanel.add(editBtn);

        //weekPanel = new JPanel();

        weekLabel = new JLabel("Total /semaine:");
        weekLabel.setFont(new Font("monospace", Font.BOLD, 15));
        weekLabel.setBounds(420, 320, 200, 30);

        weekField = new JTextField();
        weekField.setBounds(550, 320, 200, 30);
        weekField.setEditable(false);
        weekField.setFont(new Font("monospace", Font.BOLD, 15));
        weekField.setHorizontalAlignment(JTextField.CENTER);
        weekField.setBorder(new LineBorder(Color.black, 1));
        weekField.setBackground(Color.white);
        weekField.setForeground(Color.gray);

       // weekPanel.add(weekLabel);
        //weekPanel.add(weekField);
        //weekPanel.add(editBtn);

        //monthPanel = new JPanel();

        monthLabel = new JLabel("Total /mois:");
        monthLabel.setFont(new Font("monospace", Font.BOLD, 15));
        monthLabel.setBounds(420, 370, 200, 30);

        monthField = new JTextField();
        monthField.setBounds(550, 370, 200, 30);
        monthField.setEditable(false);
        monthField.setFont(new Font("monospace", Font.BOLD, 15));
        monthField.setHorizontalAlignment(JTextField.CENTER);
        monthField.setBorder(new LineBorder(Color.black, 1));
        monthField.setBackground(Color.white);
        monthField.setForeground(Color.gray);

        //monthPanel.add(monthLabel);
        //monthPanel.add(monthField);
        //monthPanel.add(editBtn);

        /*passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("monospace", Font.BOLD, 15));
        passwordLabel.setBounds(200, 250, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 250, 200, 30);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("monospace", Font.BOLD, 15));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBorder(new LineBorder(Color.black, 1));
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.gray);;

        ageLabel = new JLabel("Age :");
        ageLabel.setFont(new Font("monospace", Font.BOLD, 15));
        ageLabel.setBounds(200, 300, 100, 30);

        ageField = new JTextField();
        ageField.setBounds(300, 300, 200, 30);
        ageField.setEditable(false);
        ageField.setFont(new Font("monospace", Font.BOLD, 15));
        ageField.setHorizontalAlignment(JTextField.CENTER);
        ageField.setBorder(new LineBorder(Color.black, 1));
        ageField.setBackground(Color.white);
        ageField.setForeground(Color.gray);*/

        sumBtn = new JButton("Calculer");
        sumBtn.setBounds(470, 420, 100, 30);
        sumBtn.setFont(new Font("monospace", Font.BOLD, 15));
        sumBtn.setBackground(Color.white);
        sumBtn.setForeground(Color.black);
        sumBtn.setFocusable(false);

        printBtn = new JButton("imprimer");
        printBtn.setBounds(570, 420, 100, 30);
        printBtn.setFont(new Font("monospace", Font.BOLD, 15));
        printBtn.setBackground(Color.white);
        printBtn.setForeground(Color.black);
        printBtn.setFocusable(false);


        sumBtn.addActionListener(e ->{
            int p = db.getTotPrix();
            dayField.setText(""+ p +"");
            weekField.setText(""+ p +"");
            monthField.setText(""+ p +"");

        });
        /*editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editBtn.getText().equals("Edit")) {
                    nomField.setEditable(true);
                    nomField.setForeground(Color.black);
                    prenomField.setEditable(true);
                    prenomField.setForeground(Color.black);
                    usernameField.setEditable(true);
                    usernameField.setForeground(Color.black);
                    passwordField.setEditable(true);
                    passwordField.setForeground(Color.black);
                    ageField.setEditable(true);
                    ageField.setForeground(Color.black);
                    editBtn.setText("Save");
                } else {
                    nomField.setEditable(false);
                    nomField.setForeground(Color.gray);
                    prenomField.setEditable(false);
                    prenomField.setForeground(Color.gray);
                    usernameField.setEditable(false);
                    usernameField.setForeground(Color.gray);
                    passwordField.setEditable(false);
                    passwordField.setForeground(Color.gray);
                    ageField.setEditable(false);
                    ageField.setForeground(Color.gray);
                    editBtn.setText("Edit");
                    //db.updateUserInformtion(id, nomField.getText(), prenomField.getText(), usernameField.getText(), passwordField.getText(), ageField.getText());
                }
            }
        });*/

        //tabbedPane.add("Jour", dayPanel);
        //tabbedPane.add("semaine", weekPanel);
        //tabbedPane.add("mois", monthPanel);
        add(dayLabel);
        add(dayField);
        add(weekLabel);
        add(weekField);
        add(monthLabel);
        add(monthField);

        /*add(passwordLabel);
        add(passwordField);
        add(ageLabel);
        add(ageField);*/
        add(sumBtn);
        add(printBtn);
        //add(tabbedPane);
        add(title);
        //add(editBtn);
        add(lab);

        setSize(1200, 800);
    }
}

