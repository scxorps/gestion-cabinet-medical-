package IHM.dashboard.nurse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.Date;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import BDD.Doctor;
import BDD.bdd;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import IHM.Login;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class EditNurse extends JFrame {

    private JLabel usernamelabel;
    private JLabel mdpsLabel;
    private JTextField usernameField;
    private JTextField mdpsField;
    private JButton addButton;
    private JButton cancelButton;

    public EditNurse(String id, String username, String mdps, JFrame f) {
        super("Modifier votre compte");
        bdd db = new bdd();

        JLabel xExit = new JLabel("X");
        JLabel _minimise = new JLabel("_");
        xExit.setFont(new Font("monospace", Font.BOLD, 15));
        xExit.setForeground(Color.black);
        xExit.setBounds(470, 7, 40, 20);
        xExit.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mousePressed(MouseEvent ev) {
                dispose();
                Patients.addBtn.setEnabled(true);
                Patients.editBtn.setEnabled(true);
                Patients.deleteBtn.setEnabled(true);
                Patients.printBtn.setEnabled(true);
            }

            @Override
            public void mouseEntered(MouseEvent ev) {
                xExit.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent ev) {
                xExit.setForeground(Color.black);
            }
        });

        _minimise.setFont(new Font("monospace", Font.BOLD, 15));
        _minimise.setForeground(Color.black);
        _minimise.setBounds(440, 2, 10, 20);
        ImageIcon backGround = new ImageIcon("./src/IMJ/bg.jpg");
        JLabel bgLab = new JLabel(backGround);
        bgLab.setBounds(0, 0, 800, 550);

        _minimise.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setState(Frame.ICONIFIED);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                _minimise.setForeground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                _minimise.setForeground(Color.black);
            }
        });

        usernamelabel = new JLabel("username");
        usernamelabel.setBounds(50, 100, 150, 30);
        usernameField = new JTextField(username);
        usernameField.setBounds(250, 100, 200, 30);
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                    showMessageDialog(null, " Utiliser Uniquement les lettres !", "forme non accepté", ERROR_MESSAGE);
                }
            }

        });

        mdpsLabel = new JLabel("mot de passe");
        mdpsLabel.setBounds(50, 150, 150, 30);
        mdpsField = new JTextField(mdps);
        mdpsField.setBounds(250, 150, 200, 30);
        mdpsField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                    showMessageDialog(null, " Utiliser Uniquement les lettres !", "forme non accepté", ERROR_MESSAGE);
                }
            }

        });

        addButton = new JButton("Confirmer");
        addButton.setBounds(100, 500, 100, 30);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.GREEN);
        cancelButton = new JButton("Annuler");
        cancelButton.setBounds(300, 500, 100, 30);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.RED);

        cancelButton.addActionListener(e -> {
            dispose();
            Patients.addBtn.setEnabled(true);
            Patients.editBtn.setEnabled(true);
            Patients.deleteBtn.setEnabled(true);
            Patients.printBtn.setEnabled(true);
        });
        addButton.addActionListener(e -> {
            boolean isNameEmpty = usernameField.getText().isEmpty();
            boolean isSurnameEmpty = mdpsField.getText().isEmpty();
        
            // if(db.InsertExportation(produit, qte, username)){
            // JOptionPane.showMessageDialog(null, "Exportation a été bien ajouté",
            // "Succes", JOptionPane.INFORMATION_MESSAGE);
            // dispose();
            // new Exportation(u);

            // }
            // else
            // JOptionPane.showMessageDialog(null, "Erreur est survenue lors de l'ajout",
            // "Erreur", JOptionPane.ERROR_MESSAGE);
            // }
            // else{
            // JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs",
            // "Erreur", JOptionPane.ERROR_MESSAGE);
            //
        });
       
        add(addButton);
        add(cancelButton);
        add(xExit);
        add(_minimise);
        add(usernamelabel);
        add(usernameField);
        add(mdpsLabel);
        add(mdpsField);
        add(bgLab);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);

    }
}

class dragFrame extends MouseInputAdapter {

    private int posX, posY;
    private final JFrame frame;

    dragFrame(JFrame frame) {
        this.frame = frame;
    }

    public void mousePressed(MouseEvent e) {
        posX = e.getX();
        posY = e.getY();
    }

    public void mouseDragged(MouseEvent evt) {
        this.frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);

    }
}

class Item {
    private String id;
    private String description;

    public Item(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }
}
