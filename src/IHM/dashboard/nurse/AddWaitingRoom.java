package IHM.dashboard.nurse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import BDD.bdd;
import IHM.FQ;
import IHM.Login;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddWaitingRoom extends JFrame {
    
    private JLabel patientLabel;
    private JComboBox<Item> patientField;

    private JButton addButton;
    private JButton cancelButton;

    public AddWaitingRoom(JFrame f) {
        super("Ajouter Patient");
        bdd db = new bdd();
        dragFrame move = new dragFrame(this);

        // this.addMouseListener(move);
        // this.addMouseMotionListener(move);

        JLabel xExit = new JLabel("X");
        JLabel _minimise = new JLabel("_");
        ImageIcon backGround = new ImageIcon("./src/IMJ/bg.jpg");
        JLabel bgLab = new JLabel(backGround);
        bgLab.setBounds(0, 0, 800, 550);
        xExit.setFont(new Font("monospace", Font.BOLD, 15));
        xExit.setForeground(Color.black);
        xExit.setBounds(470, 7, 40, 20);
        xExit.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mousePressed(MouseEvent ev) {
                dispose();
                WaitingRoom.addBtn.setEnabled(true);
                WaitingRoom.editBtn.setEnabled(true);
                WaitingRoom.deleteBtn.setEnabled(true);
                WaitingRoom.printBtn.setEnabled(true);
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

        patientLabel = new JLabel("Nom de patient");
        patientLabel.setBounds(50, 100, 150, 30);
        Object[][] patients = db.getPatientsOnCurrentDay();

        Item[] cbPatient = new Item[patients.length];
        for (int i = 0; i < patients.length; i++) {
            String desc = patients[i][1] + " / " + patients[i][2] + " / " + patients[i][5];
            String id = patients[i][0].toString();

            cbPatient[i] = new Item(id, desc);

        }
        patientField = new JComboBox(cbPatient);
        patientField.setBounds(150, 100, 300, 30);

        addButton = new JButton("Ajouter");
        addButton.setBounds(100, 400, 100, 30);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.GREEN);
        cancelButton = new JButton("Annuler");
        cancelButton.setBounds(300, 400, 100, 30);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.RED);

        cancelButton.addActionListener(e -> {
            dispose();
            WaitingRoom.addBtn.setEnabled(true);
            WaitingRoom.editBtn.setEnabled(true);
            WaitingRoom.deleteBtn.setEnabled(true);
            WaitingRoom.printBtn.setEnabled(true);
        });
        addButton.addActionListener(e -> {

            Item item = (Item) patientField.getSelectedItem();
            System.out.println(item.getId() + " : " + item.getDescription());

            if(db.InsertWaitingRoom(item.getId())){
            f.dispose();

















            
            dispose();
            new DashboardNurse(Login.name, Login.role, 0);

            }
            else
            JOptionPane.showMessageDialog(null, "Erreur est survenue lors de l'ajout",
            "Erreur", JOptionPane.ERROR_MESSAGE);
            
         

        });
        add(addButton);
        add(cancelButton);
        add(xExit);
        add(_minimise);
        add(patientLabel);
        add(patientField);

        add(bgLab);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
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
