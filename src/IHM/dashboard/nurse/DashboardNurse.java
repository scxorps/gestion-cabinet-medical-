package IHM.dashboard.nurse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.MouseInputAdapter;

import IHM.FQ;
import IHM.Login;

public class DashboardNurse extends JFrame {

    private JButton Acceuil;
    private JButton Patient;
    private JButton informations;
    private JButton logout;

    public DashboardNurse(String id, String name, String surname, String age, String username, String password,
            String role, int page) {

        super("Tableau de bord");
        System.out.println(name + " " + role);
        JTabbedPane tabbedPane = new JTabbedPane();

        dragFrame move = new dragFrame(this);

        this.addMouseListener(move);
        this.addMouseMotionListener(move);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(new WaitingRoom(this));

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.add(new Patients(this));

        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.add(new Informations(this, id, name, surname, age, username, password));
        // Add the panels to the tabbed pane
        tabbedPane.addTab("Tab 1", panel1);
        tabbedPane.addTab("Tab 2", panel2);
        tabbedPane.addTab("Tab 3", panel3);
        tabbedPane.setBounds(200, -25, 1200, 800);
        ImageIcon logo = new ImageIcon("./src/IMJ/Logo.png");
        JLabel lgLab = new JLabel(logo);
        lgLab.setBounds(50, 50, 100, 68);
        JLabel xExit = new JLabel("X");
        JLabel _minimise = new JLabel("_");
        xExit.setFont(new Font("monospace", Font.BOLD, 15));
        xExit.setForeground(Color.black);
        xExit.setBounds(1360, 7, 40, 20);
        xExit.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mousePressed(MouseEvent ev) {
                new FQ();
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
        add(xExit);

        _minimise.setFont(new Font("monospace", Font.BOLD, 15));
        _minimise.setForeground(Color.black);
        _minimise.setBounds(1330, 2, 10, 20);

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
        add(_minimise);

        this.Acceuil = new JButton("Acceuil");
        this.Acceuil.setBounds(0, 200, 200, 60);
        this.Acceuil.setForeground(Color.white);
        this.Acceuil.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
        this.Acceuil.setBackground(new Color(0X011b45));
        this.Acceuil.setFocusable(false);
        this.Acceuil.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
        this.Acceuil.setFont(new Font("Arial", Font.BOLD, 16));

        this.Acceuil.addActionListener(e -> {
            tabbedPane.setSelectedIndex(0);
            this.Acceuil.setBackground(new Color(0X011b45));
            this.Patient.setBackground(new Color(0x07337a));
            this.informations.setBackground(new Color(0x07337a));
            this.Patient.setBorder(null);
            this.informations.setBorder(null);
            this.Acceuil.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
        });

        this.Patient = new JButton("Patients");
        this.Patient.setBounds(0, 260, 200, 60);
        this.Patient.setForeground(Color.WHITE);
        this.Patient.setFocusable(false);
        this.Patient.setBorder(null);
        this.Patient.setBackground(new Color(0x07337a));
        this.Patient.setFont(new Font("Arial", Font.BOLD, 16));

        this.Patient.addActionListener(e -> {

            tabbedPane.setSelectedIndex(1);
            this.Acceuil.setBackground(new Color(0x07337a));
            this.Patient.setBackground(new Color(0X011b45));
            this.informations.setBackground(new Color(0x07337a));
            this.informations.setBorder(null);
            this.Acceuil.setBorder(null);
            this.Patient.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));

        });

        this.informations = new JButton("Informations");
        this.informations.setBounds(0, 320, 200, 60);
        this.informations.setForeground(Color.white);
        this.informations.setBackground(new Color(0x07337a));
        this.informations.setFocusable(false);
        this.informations.setBorder(null);
        this.informations.setFont(new Font("Arial", Font.BOLD, 16));

        this.informations.addActionListener(e -> {

            tabbedPane.setSelectedIndex(2);
            this.Patient.setBackground(new Color(0x07337a));
            this.Acceuil.setBackground(new Color(0x07337a));
            this.informations.setBackground(new Color(0X011b45));
            this.Patient.setBorder(null);
            this.Acceuil.setBorder(null);
            this.informations.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
        });

        this.logout = new JButton("Deconnexion");
        this.logout.setBounds(0, 710, 200, 60);
        this.logout.setBackground(Color.RED);
        this.logout.setForeground(Color.WHITE);
        this.logout.setFocusable(false);
        this.logout.setFont(new Font("Arial", Font.BOLD, 16));
        this.logout.addActionListener(e -> {
            String[] options = { "Oui", "Non" };
            int choice = JOptionPane.showOptionDialog(null, "Voulez vous vraiment vous deconnecter?", "Deconnexion", 0,
                    JOptionPane.YES_NO_OPTION, null, options, null);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    new Login();
                } catch (LineUnavailableException | IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        switch (page) {
            case 0: {
                tabbedPane.setSelectedIndex(0);
                this.Acceuil.setBackground(new Color(0X011b45));
                this.Patient.setBackground(new Color(0x07337a));
                this.informations.setBackground(new Color(0x07337a));
                this.Patient.setBorder(null);
                this.informations.setBorder(null);
                this.Acceuil.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
                break;
            }
            case 1: {
                tabbedPane.setSelectedIndex(1);
                this.Acceuil.setBackground(new Color(0x07337a));
                this.Patient.setBackground(new Color(0X011b45));
                this.informations.setBackground(new Color(0x07337a));
                this.informations.setBorder(null);
                this.Acceuil.setBorder(null);
                this.Patient.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
                break;
            }
            case 2: {
                tabbedPane.setSelectedIndex(2);
                this.Patient.setBackground(new Color(0x07337a));
                this.Acceuil.setBackground(new Color(0x07337a));
                this.informations.setBackground(new Color(0X011b45));
                this.Patient.setBorder(null);
                this.Acceuil.setBorder(null);
                this.informations.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
                break;
            }
        }

        JPanel p = new JPanel();
        p.setBounds(0, 0, 200, 900);
        p.setLayout(null);
        p.setBackground(new Color(0x07337a));

        ImageIcon logo2 = new ImageIcon("./src/IMJ/logo1.png");
        JLabel lglab2 = new JLabel(logo2);

        lglab2.setBounds(0, 0, 200, 200);
        p.add(lglab2);
        setUndecorated(true);
        setLayout(null);
        this.setSize(1400, 770);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        // this.add(lgLab);
        p.add(this.Patient);
        p.add(this.Acceuil);
        p.add(this.informations);
        p.add(this.logout);
        add(p);
        add(tabbedPane);

    }

    public static void main(String[] args) {
        new DashboardNurse(Login.id, Login.name, Login.role, Login.age, Login.username, Login.password, Login.role, 0);
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
