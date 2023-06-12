package IHM.dashboard.doctor;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.MouseInputAdapter;

import IHM.FQ;
import IHM.Login;
import IHM.dashboard.nurse.WaitingRoom;

import java.awt.*;

import java.awt.event.MouseEvent;

import java.io.IOException;
import java.sql.Date;

public class DashboardDoctor extends JFrame {
    
    private JButton Consultation;
    private JButton privé;
    private JButton informations;
    
    private JButton logout;
    
    public DashboardDoctor(String id, String name, String surname, String age, String username, String password, String role, int page) {
        
        super("Tableau de bord");
        JLabel xExit = new JLabel("X");
        JLabel _minimise= new JLabel("_");
        xExit.setFont(new Font("monospace", Font.BOLD,15));
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
        System.out.println(name + " " + role);
        JTabbedPane tabbedPane = new JTabbedPane();
        
        dragFrame move = new dragFrame(this);
        
        this.addMouseListener(move);
        this.addMouseMotionListener(move);
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(new Conspage(this,role,id));
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.add(new Privé(this));
        
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.add(new Informations(this, id, name, surname, age, username, password));

        // Add the panels to the tabbed pane
        tabbedPane.addTab("Tab 1", panel1);
        tabbedPane.addTab("Tab 2", panel2);
        tabbedPane.addTab("Tab 3", panel3);
        tabbedPane.setBounds(200, -25, 1200, 800);
    

        this.Consultation = new JButton("Consultation");
        this.Consultation.setBounds(0, 200, 200, 60);
        this.Consultation.setForeground(Color.WHITE);
        this.Consultation.setFocusable(false);
        this.Consultation.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
        this.Consultation.setBackground(new Color(0X011b45));
        this.Consultation.setFont(new Font("Arial", Font.BOLD, 16));
        this.Consultation.addActionListener(e -> {
            
            tabbedPane.setSelectedIndex(0);           
            this.Consultation.setBackground(new Color(0X011b45));
            this.privé.setBackground(new Color(0x07337a));            
            this.Consultation.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
            this.privé.setBorder(null);
            this.informations.setBackground(new Color(0x07337a));
            this.informations.setBorder(null);

        });

        this.privé = new JButton("Privé");
        this.privé.setBounds(0, 260, 200, 60);
        this.privé.setForeground(Color.white);
        this.privé.setBackground(new Color(0x07337a));
        this.privé.setFocusable(false);
        this.privé.setBorder(null);
        this.privé.setFont(new Font("Arial", Font.BOLD, 16));
        this.privé.addActionListener(e -> {
            
            tabbedPane.setSelectedIndex(1);
            this.Consultation.setBackground(new Color(0x07337a));
            this.privé.setBackground(new Color(0X011b45));            
            this.Consultation.setBorder(null);
            this.privé.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
            this.informations.setBackground(new Color(0x07337a));
            this.informations.setBorder(null);

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
            this.Consultation.setBackground(new Color(0x07337a));
            this.Consultation.setBorder(null);
            this.privé.setBackground(new Color(0x07337a));
            this.privé.setBorder(null);
            this.informations.setBackground(new Color(0X011b45));
            this.informations.setBorder(new MatteBorder(0, 2, 2, 0, Color.red));
        });

  

        this.logout = new JButton("Deconnexion");
        this.logout.setBounds(0, 710, 200, 60);
        this.logout.setBackground(Color.RED);
        this.logout.setForeground(Color.WHITE);
        this.logout.setFocusable(false);
        this.logout.setFont(new Font("Arial", Font.BOLD, 16));
        this.logout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment vous deconnecter?", "Deconnexion",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                try {
                    new Login();
                } catch (LineUnavailableException | IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        ImageIcon logo2 = new ImageIcon("./src/IMJ/logo1.png");
       JLabel lglab2 = new JLabel(logo2);
       lglab2.setBounds(0, 0, 200, 200);
        add(xExit);
        add(_minimise);

        JPanel p = new JPanel();
        p.setBounds(0, 0, 200, 900);
        p.setLayout(null);
        p.setBackground(new Color(0x07337a));
        setUndecorated(true);
        setLayout(null);
        this.setSize(1400, 770);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        add(lglab2);
        p.add(this.privé);
        p.add(this.Consultation);
        p.add(this.informations);
        p.add(this.logout);
        add(p);
        add(tabbedPane);
    }
    public static void main(String[] args) {
        //new DashboardDoctor(Login.name,Login.role,Login.id,1);
        new DashboardDoctor(Login.id, Login.name, Login.surname, Login.age, Login.username, Login.password, Login.role, 1);
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
