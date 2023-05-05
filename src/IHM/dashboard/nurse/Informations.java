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
   
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel ageLabel;

    private JTextField nomField;
    private JTextField prenomField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField ageField;


  
   
    public static JFrame F;

    public Informations(JFrame frame, String id, String name, String surname, String age, String username, String password) {
        bdd db = new bdd();
        setLayout(null);

        ImageIcon bg = new ImageIcon("./img/bgP.jpg");
        JLabel lab = new JLabel(bg);
        lab.setBounds(0, 0, 1200, 800);

        this.F = frame;
        title = new JLabel("Informations");
        title.setFont(new Font("monospace", Font.BOLD, 25));
        title.setBounds(450, 20, 200, 50);

        nomLabel = new JLabel("Nom :");
        nomLabel.setFont(new Font("monospace", Font.BOLD, 15));
        nomLabel.setBounds(200, 100, 100, 30);

        nomField = new JTextField(name);
        nomField.setBounds(300, 100, 200, 30);
        nomField.setEditable(false);
        nomField.setFont(new Font("monospace", Font.BOLD, 15));
        nomField.setHorizontalAlignment(JTextField.CENTER);
        nomField.setBorder(new LineBorder(Color.black, 1));
        nomField.setBackground(Color.white);
        nomField.setForeground(Color.gray);

        prenomLabel = new JLabel("Prenom :");
        prenomLabel.setFont(new Font("monospace", Font.BOLD, 15));
        prenomLabel.setBounds(200, 150, 100, 30);

        prenomField = new JTextField(surname);
        prenomField.setBounds(300, 150, 200, 30);
        prenomField.setEditable(false);
        prenomField.setFont(new Font("monospace", Font.BOLD, 15));
        prenomField.setHorizontalAlignment(JTextField.CENTER);
        prenomField.setBorder(new LineBorder(Color.black, 1));
        prenomField.setBackground(Color.white);
        prenomField.setForeground(Color.gray);

        usernameLabel = new JLabel("Username :");
        usernameLabel.setFont(new Font("monospace", Font.BOLD, 15));
        usernameLabel.setBounds(200, 200, 100, 30);

        usernameField = new JTextField(username);
        usernameField.setBounds(300, 200, 200, 30);
        usernameField.setEditable(false);
        usernameField.setFont(new Font("monospace", Font.BOLD, 15));
        usernameField.setHorizontalAlignment(JTextField.CENTER);
        usernameField.setBorder(new LineBorder(Color.black, 1));
        usernameField.setBackground(Color.white);
        usernameField.setForeground(Color.gray);

        passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("monospace", Font.BOLD, 15));
        passwordLabel.setBounds(200, 250, 100, 30);

        passwordField = new JPasswordField(password);
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

        ageField = new JTextField(age);
        ageField.setBounds(300, 300, 200, 30);
        ageField.setEditable(false);
        ageField.setFont(new Font("monospace", Font.BOLD, 15));
        ageField.setHorizontalAlignment(JTextField.CENTER);
        ageField.setBorder(new LineBorder(Color.black, 1));
        ageField.setBackground(Color.white);
        ageField.setForeground(Color.gray);

        editBtn = new JButton("Edit");
        editBtn.setBounds(300, 350, 200, 30);
        editBtn.setFont(new Font("monospace", Font.BOLD, 15));
        editBtn.setBackground(Color.white);
        editBtn.setForeground(Color.black);
        editBtn.setFocusable(false);
        editBtn.addActionListener(new ActionListener() {
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
                    db.updateUserInformtion(id, nomField.getText(), prenomField.getText(), usernameField.getText(), passwordField.getText(), ageField.getText());
                }
            }
        });

        
        
        add(nomLabel);
        add(nomField);
        add(prenomLabel);
        add(prenomField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(ageLabel);
        add(ageField);
        add(editBtn);
        add(title);
        //add(editBtn);
        add(lab);

        setSize(1200, 800);
    }
}

