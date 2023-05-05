package IHM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
import BDD.*;
import IHM.dashboard.nurse.DashboardNurse;

public class Login extends JFrame {

    bdd b;
    Doctor d = null;
    Nurse n = null;

    public static String id = null;
    public static String name = null;
    public static String surname = null;
    public static String username = null;
    public static String password = null;
    public static String age = null;
    public static String role = null;

    public static Color cabCol = new Color(0x1D4D99);

    public Login() throws LineUnavailableException, IOException {
        this.b = new bdd();

        dragFrame move = new dragFrame(this);
        this.addMouseListener(move);
        this.addMouseMotionListener(move);

        ImageIcon backGround = new ImageIcon("./src/IMJ/cabimg.png");
        ImageIcon logo = new ImageIcon("./src/IMJ/mylogo.png");
        JLabel lgLab = new JLabel(logo);
        lgLab.setBounds(50, 50, 100, 68);
        JLabel bgLab = new JLabel(backGround);
        JLabel xExit = new JLabel("X");
        JLabel _minimise = new JLabel("_");

        xExit.setFont(new Font("monospace", Font.BOLD, 15));
        xExit.setForeground(Color.black);
        xExit.setBounds(950, 7, 40, 20);
        xExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new FQ();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                xExit.setForeground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                xExit.setForeground(Color.black);
            }
        });

        JTextField usernameField = new JTextField() {

            private final String placeholder = "Nom D'utilisateur";
            private final Color placeholdercolor = new Color(0xC2C2C2);

            @Override
            protected void paintComponent(final Graphics graphics) {
                super.paintComponent(graphics);

                if (!getText().isEmpty()) {

                    return;
                } else if (placeholder == null || placeholder.trim().isEmpty()) {
                    // rien à afficher
                    return;
                }

                final Graphics2D g = (Graphics2D) graphics;
                g.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(placeholdercolor == null ? getDisabledTextColor() : placeholdercolor);
                g.drawString(placeholder, getInsets().left, graphics.getFontMetrics()
                        .getMaxAscent() + 15);
            }

        };

        usernameField.setFont(new Font("monospace", Font.BOLD, 17));
        usernameField.setBounds(340, 210, 300, 50);
        usernameField.setBorder(new EmptyBorder(0, 19, 0, 0));
        usernameField.setForeground(Color.gray);

        JPasswordField passwordField = new JPasswordField() {

            private final String placeholder = "Mot de passe";
            private final Color placeholdercolor = new Color(0xC2C2C2);

            @Override
            protected void paintComponent(final Graphics graphics) {
                super.paintComponent(graphics);

                if (!getText().isEmpty()) {

                    return;
                } else if (placeholder == null || placeholder.trim().isEmpty()) {

                    return;
                }

                final Graphics2D g = (Graphics2D) graphics;
                g.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(placeholdercolor == null ? getDisabledTextColor() : placeholdercolor);
                g.drawString(placeholder, getInsets().left, graphics.getFontMetrics()
                        .getMaxAscent() + 15);
            }

        };
        passwordField.setFont(new Font("monospace", Font.BOLD, 17));
        passwordField.setBounds(340, 270, 300, 50);
        passwordField.setBorder(new EmptyBorder(0, 19, 0, 0));
        passwordField.setForeground(Color.gray);

        ImageIcon userIcon = new ImageIcon("./src/IMJ/user.png");
        JButton create = new JButton(userIcon);
        create.setText(" Connexion   ");
        create.setVerticalTextPosition(AbstractButton.CENTER);
        create.setHorizontalTextPosition(AbstractButton.LEADING);
        create.setFont(new Font("monospace", Font.BOLD, 17));
        create.setBounds(390, 400, 200, 50);
        create.setBackground(Login.cabCol);
        create.setForeground(Color.white);
        create.setFocusable(false);
        create.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {

                    ResultSet rs = b.authentification(usernameField.getText(), passwordField.getText());

                    if ("Doctor".equals(rs.getString("Role"))) {
                        d = new Doctor(
                                rs.getInt("id"),
                                rs.getDate("date_recrut"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getInt("age"),
                                rs.getString("username"),
                                rs.getString("password"));

                        /*
                         * System.out.println(d.getName());
                         * System.out.println(d.getSurname());
                         * System.out.println(d.getAge());
                         * System.out.println(d.role);
                         */
                    } else if ("Nurse".equals(rs.getString("Role"))) {
                        /*
                         * n = new Nurse(
                         * rs.getInt("id"),
                         * rs.getDate("date_recrut"),
                         * rs.getString("name"),
                         * rs.getString("surname"),
                         * rs.getInt("age"),
                         * rs.getString("usernameField"),
                         * rs.getString("passwordField"));
                         * System.out.println(n.getName());
                         * System.out.println(n.getSurname());
                         * System.out.println(n.getAge());
                         * System.out.println(n.role);
                         */
                    }

                    switch (rs.getString("role")) {
                        case "Doctor":
                            JOptionPane.showMessageDialog(null, "Doctor", "state", JOptionPane.PLAIN_MESSAGE);
                            break;

                        case "Nurse":
                            id = rs.getString("id");
                            name = rs.getString("name");
                            surname = rs.getString("surname");
                            age = rs.getString("age");
                            username = rs.getString("username");
                            password = rs.getString("password");
                            role = rs.getString("role");
                            System.out.println(name + " " + surname + " " + age + " " + " " + username + " " + password + " " + role );
                            new DashboardNurse(id, name, surname, age, username, password,  role, 0);
                            dispose();
                            break;

                    }
                } catch (SQLException er) {
                    System.out.println(er.getMessage());
                } catch (NullPointerException er) {
                    JOptionPane.showMessageDialog(null, "Nom D'utilisateur ou mot de passe Incorrecte !", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        _minimise.setFont(new Font("monospace", Font.BOLD, 15));
        _minimise.setForeground(Color.black);
        _minimise.setBounds(920, 2, 10, 20);
        _minimise.addMouseListener(new MouseAdapter() {
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

        bgLab.setBounds(0, 0, 970, 546);

        JLabel XExit = new JLabel("X");
        JLabel reduire = new JLabel("_");

        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Objects.equals(usernameField.getText(), "Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                    usernameField.setBorder(new EmptyBorder(12, 20, 11, 0));
                    usernameField.getCaret().setVisible(true);
                }
            }

        });
        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Objects.equals(passwordField.getText(), "PasswordField")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setBorder(new EmptyBorder(12, 20, 11, 0));
                    passwordField.getCaret().setVisible(true);
                }
            }

        });

        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isAlphabetic(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                    showMessageDialog(null,
                            " Utiliser Uniquement les lettres !",
                            "forme non accepté",
                            ERROR_MESSAGE);
                }
            }

        });

        this.add(create);
        this.add(usernameField);
        this.add(passwordField);
        this.setUndecorated(true);
        //this.add(lgLab);
        this.add(_minimise);
        this.add(xExit);
        this.add(bgLab);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(970, 546);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) throws LineUnavailableException, IOException {
        new Login();
    }
}

class dragFrame extends MouseAdapter {

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
