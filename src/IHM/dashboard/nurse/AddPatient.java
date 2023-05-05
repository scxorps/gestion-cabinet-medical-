package IHM.dashboard.nurse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import BDD.bdd;
import IHM.FQ;
import IHM.Login;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class AddPatient extends JFrame {
    
    private JLabel title;
    private JLabel dateLabel;
    private JLabel nomlabel;
    private JLabel prenomLabel;
    private JLabel ageLabel;
    private JLabel sexeLabel;
    private JLabel num_telephoneLabel;
    private JLabel adresselLabel;
    private JLabel doctorLabel;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;
    private JComboBox sexeField;
    private JComboBox doctorField;
    private JTextField num_telephoneField;
    private JTextField adressField;
    private JButton addButton;
    private JButton cancelButton;

    public AddPatient(JFrame f) {
        super("Ajouter Patient");
        bdd db = new bdd();
        dragFrame move = new dragFrame(this);

        // this.addMouseListener(move);
        // this.addMouseMotionListener(move);

        
        JLabel xExit = new JLabel("X");
        JLabel _minimise = new JLabel("_");
        ImageIcon backGround = new ImageIcon("./src/IMJ/form2.jpg");
        JLabel bgLab = new JLabel(backGround);
        ImageIcon logo = new ImageIcon("./assets/logo/Logo.png");
        JLabel lgLab = new JLabel(logo);
        lgLab.setBounds(50,50,100, 68);
        bgLab.setBounds(0, 0, 800, 550);
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
        title = new JLabel("Ajouter un patient");
        title.setBounds(165, 20, 300, 20);
        title.setFont(new Font("monospace", Font.BOLD, 20));
        add(title);
        
        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        JDatePickerImpl dateField = new JDatePickerImpl(datePanel);
        dateLabel = new JLabel("Render-vous");
        dateLabel.setBounds(50, 50, 150, 30);
        dateLabel.setFont(new Font("monospace", Font.BOLD, 14));
        dateField.setBounds(250, 50, 200, 30);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String rdv = dateObj.format(formatter).toString();
        String[] dates = rdv.split("-", 3);
        dateField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.Date date = (java.util.Date) dateField.getModel().getValue();
                java.sql.Date d = new java.sql.Date(date.getTime());

                LocalDate today = LocalDate.now();
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                if(!today.isBefore(localDate)){
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas selectionner la date avant Aujourd'hui", "Erreur", JOptionPane.ERROR_MESSAGE);
                    LocalDate dateObj = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String rdv = dateObj.format(formatter).toString();
                    String[] dates = rdv.split("-", 3);
                    dateField.getModel().setDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]) - 1,
                    Integer.parseInt(dates[2]));
                    dateField.getModel().setSelected(true);
                }
                LocalDate dateToCheck = LocalDate.of(date.getYear(), date.getMonth(), date.getDay()); // Replace with the date you want to check


                // Create a LocalDate object for the date you want to check
                
               

                

                if(db.isWaitingRoomFull(d)){
                    JOptionPane.showMessageDialog(null, "Salle d'attente est complete", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                            dateField.getModel().setValue(null);
                        }
                        else
                        System.out.println("Salle d'attence n'est pas cmplete");
                        
                    }
                });
                dateField.getModel().setDate(Integer.parseInt(dates[0]), Integer.parseInt(dates[1]) - 1,
                Integer.parseInt(dates[2]));
        dateField.getModel().setSelected(true);
                
        nomlabel = new JLabel("Nom de patient");
        nomlabel.setBounds(50, 100, 150, 30);
        nomField = new JTextField();
        nomField.setBounds(250, 100, 200, 30);
        nomField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
                    e.consume();
                }
            }
        });

        prenomLabel = new JLabel("Prenom de patient");
        prenomLabel.setBounds(50, 150, 150, 30);
        prenomField = new JTextField();
        prenomField.setBounds(250, 150, 200, 30);
        prenomField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || c == KeyEvent.VK_SPACE)) {
                    e.consume();
                }
            }

        });
        ageLabel = new JLabel("age de patient");
        ageLabel.setBounds(50, 200, 150, 30);
        ageField = new JTextField();
        ageField.setBounds(250, 200, 200, 30);
        ageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }

        });

        sexeLabel = new JLabel("Sexe");
        sexeLabel.setBounds(50, 250, 150, 30);
        String[] sexe = { "Choisir votre sexe", "Homme", "Femme" };
        sexeField = new JComboBox<>(sexe);
        sexeField.setBounds(250, 250, 200, 30);

        num_telephoneLabel = new JLabel("N=°Telephone");
        num_telephoneLabel.setBounds(50, 300, 150, 30);
        num_telephoneField = new JTextField();
        num_telephoneField.setBounds(250, 300, 200, 30);
        num_telephoneField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ((Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }

        });

        adresselLabel = new JLabel("Adresse");
        adresselLabel.setBounds(50, 350, 150, 30);
        adressField = new JTextField();
        adressField.setBounds(250, 350, 200, 30);

        doctorLabel = new JLabel("Docteur assigné");
        doctorLabel.setBounds(50, 450, 150, 30);
        Object[][] doctors = db.getDoctor();

        Item[] cbDoctor = new Item[doctors.length + 1];
        cbDoctor[0] = new Item("None", "Selectionner le docteur");
        for (int i = 0; i < doctors.length; i++) {
            String desc =  doctors[i][1] + " / " + doctors[i][2];
            String id = doctors[i][0].toString();

            cbDoctor[i+1] = new Item(id, desc);

        }
        doctorField = new JComboBox(cbDoctor);
        doctorField.setBounds(150, 450, 300, 30);


        addButton = new JButton("Ajouter");
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
            //boolean isdateEmpty = dateField.getText().isEmpty();
            boolean isNameEmpty = nomField.getText().isEmpty();
            boolean isSurnameEmpty = prenomField.getText().isEmpty();
            boolean isAgeEmpty = ageField.getText().isEmpty();
            boolean isGenderEmpty = sexeField.getSelectedItem().equals("Choisir votre sexe");
            boolean isPhoneEmpty = num_telephoneField.getText().isEmpty();
            boolean isAdressEmpty = adressField.getText().isEmpty();
            boolean isdateEmpty = dateField.getModel().getValue() == null;
            Item doctor = (Item) doctorField.getSelectedItem();
            boolean isDoctorEmpty = doctor.getId().equals("None");


            if ( isdateEmpty ||isNameEmpty || isSurnameEmpty || isAgeEmpty || isGenderEmpty || isPhoneEmpty || isAdressEmpty || isDoctorEmpty) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tout les cases", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String nom = nomField.getText(),
                        prenom = prenomField.getText(),
                        age = ageField.getText(),
                        gender = sexeField.getSelectedItem().toString(),
                        phone = num_telephoneField.getText(),
                        adress = adressField.getText(),
                        dc = doctor.getId();

                        java.util.Date date = (Date) dateField.getModel().getValue();
                        java.sql.Date d = new java.sql.Date(date.getTime());
                if (db.InsertPatient(nom, prenom, age, gender, phone, adress, d, dc)) {
                    JOptionPane.showMessageDialog(null, "Patient est bien ajouté", "Succes", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon("./img/logo.jpg"));
                    dispose();
                    f.dispose();
                    new DashboardNurse(Login.id, Login.name, Login.role, Login.age, Login.username, Login.password, Login.role, 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

            // if(db.InsertExportation(produit, qte, nom)){
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
        add(dateLabel);
        add(dateField);
        add(nomlabel);
        add(nomField);
        add(doctorField);
        add(doctorLabel);
        add(prenomLabel);
        add(prenomField);
        add(ageLabel);
        add(ageField);
        add(sexeLabel);
        add(sexeField);
        add(num_telephoneLabel);
        add(num_telephoneField);
        add(adresselLabel);
        add(adressField);
        add(bgLab);
         this.add(lgLab);
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