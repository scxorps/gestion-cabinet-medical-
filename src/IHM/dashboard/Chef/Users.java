package IHM.dashboard.Chef;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import BDD.bdd;
import IHM.Login;
import IHM.dashboard.Chef.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class Users extends JPanel {
    private JLabel title;
    private JPanel tabPanel;

    public static JButton addBtn;
    public static JButton editBtn;
    public static JButton deleteBtn;
    public static JButton printBtn;
    public static JComboBox<String> doctor;

    private Object[][] data;

    public static JTable table;

    private String id = null;
    private String patient = null;
    private String nom = null;
    private String prenom = null;
    private String age = null;
    private String sexe = null;
    private String ntel = null;
    private String adresse = null;
    private String prix = null;

    private JTextField searchBar;
    private String search;
    public static JFrame F;

    public Users(JFrame frame) {
        bdd db = new bdd();

        ImageIcon bg = new ImageIcon("./img/bgP.jpg");
        JLabel lab = new JLabel(bg);
        lab.setBounds(0, 0, 1280, 800);

        this.F = frame;
        title = new JLabel("Utilisateur");
        title.setFont(new Font("monospace", Font.BOLD, 25));
        title.setBounds(450, 20, 200, 50);
        // title.setFont(new Font("ARIAL", Font.BOLD, 20));

        add(title);

        addBtn = new JButton("Ajouter");
        addBtn.setBounds(1000, 100, 150, 70);
        addBtn.setFont(new Font("Arial", Font.BOLD, 20));
        addBtn.setFocusable(false);
        addBtn.setBackground(new Color(0X011b45));
        addBtn.setForeground(Color.WHITE);

        deleteBtn = new JButton("Supprimer");
        deleteBtn.setBounds(1000, 200, 150, 70);
        deleteBtn.setFont(new Font("Arial", Font.BOLD, 20));
        deleteBtn.setBackground(new Color(0X011b45));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFocusable(false);
        deleteBtn.addActionListener(e -> {
            if (id != null) {
                int choice = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimé le utilisateur n=°" + id,
                        "Confirmer", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    if (db.deleteUsers(id)) {
                        JOptionPane.showMessageDialog(null, "Patient a été bien supprimé", "Success",
                                JOptionPane.PLAIN_MESSAGE, new ImageIcon("./img/logo.jpg"));
                        frame.dispose();
                        new DashboardChef(Login.name, Login.role,Login.surname,Login.id,Login.age,Login.username,Login.password, 0);
                    }

                    else
                        JOptionPane.showMessageDialog(null, "Erreur lors du suppression", "Erreur",
                                JOptionPane.ERROR_MESSAGE);

                }
            } else
                JOptionPane.showMessageDialog(null, "Veuillez Selectionner un patient", "Remarque",
                        JOptionPane.INFORMATION_MESSAGE);

        });

        printBtn = new JButton("Imprimer");
        printBtn.setBounds(1000, 300, 150, 70);
        printBtn.setFont(new Font("Arial", Font.BOLD, 20));
        printBtn.setBackground(Color.BLACK);
        printBtn.setForeground(Color.WHITE);
        printBtn.setFocusable(false);

        String[] doctorName = db.getDoctorName();
        doctor = new JComboBox<String>(doctorName);
        doctor.setRenderer(new CenteredRenderer());

        doctor.setBounds(1000, 400, 150, 70);
        doctor.setFont(new Font("Arial", Font.BOLD, 20));

        addBtn.addActionListener(e -> {

            new AddUsers(frame);
            addBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
            printBtn.setEnabled(false);

        });

        tabPanel = new JPanel();
        tabPanel.setLayout(null);
        tabPanel.setBounds(20, 70, 900, 640);

        String header[] = { "Id", "Nom", "Prénom", "Username", "Age", "Role", "Date de recrutement" };
        String defaultId = db.getDoctorId((String) doctor.getSelectedItem());
        data = db.getUsers();

        DefaultTableModel model = new DefaultTableModel(data, header);
        TableRowSorter sorter = new TableRowSorter<>(model);

        table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setRowSorter(sorter);

        doctor.addActionListener(e -> {
            id = null;
            String id = db.getDoctorId((String) doctor.getSelectedItem());
            System.out.println(id);
            Object[][] datatmp = db.getWaitingRoomById(id);
            data = datatmp;
            model.setRowCount(0);
            for (int i = 0; i < datatmp.length; i++) {
                model.addRow(datatmp[i]);
                System.out.println(Arrays.toString(datatmp[i]));
            }
            model.fireTableDataChanged();

        });

        table.setFocusable(false);
        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                // to detect doble click events
                JTable target = (JTable) me.getSource();
                // int row = target.getSelectedRow(); // select a row
                // int column = target.getSelectedColumn(); // select a column
                // JOptionPane.showMessageDialog(null, data[target.getSelectedRow()][4]); // get
                // the value of a row and column.
                id = data[target.getSelectedRow()][0].toString();
                
                System.out.println(id);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        TableColumnModel columnModel = table.getColumnModel();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        columnModel.getColumn(0).setPreferredWidth(128);
        columnModel.getColumn(0).setMaxWidth(128);
        columnModel.getColumn(1).setPreferredWidth(128);
        columnModel.getColumn(1).setMaxWidth(128);
        columnModel.getColumn(2).setPreferredWidth(128);
        columnModel.getColumn(2).setMaxWidth(128);
        columnModel.getColumn(3).setPreferredWidth(128);
        columnModel.getColumn(3).setMaxWidth(128);
        columnModel.getColumn(4).setPreferredWidth(128);
        columnModel.getColumn(4).setMaxWidth(128);
        columnModel.getColumn(5).setPreferredWidth(128);
        columnModel.getColumn(5).setMaxWidth(128);
        columnModel.getColumn(6).setPreferredWidth(128);
        columnModel.getColumn(6).setMaxWidth(128);
        

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        sp.setBounds(0, 0, 900, 640);

        tabPanel.add(sp);
        add(tabPanel);
        add(deleteBtn);
        add(addBtn);
        
        

        // printBtn.addActionListener(new ActionListener(){

        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // TODO Auto-generated method stub
        // MessageFormat header = new MessageFormat("Produit");
        // MessageFormat footer = new MessageFormat("Page{0,number,integer}");

        // try {
        // table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        // } catch (java.awt.print.PrinterAbortException err) {
        // } catch (PrinterException ex) {
        // System.out.println(ex.getMessage());
        // }
        // }
        // });
        searchBar = new JTextField() {

            private final String placeholder = "Rechercher Utilisateur";
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
                g.drawString(placeholder, 10, 20);
            }

        };
        ;
        searchBar.setBounds(20, 20, 250, 30);
        searchBar.setFont(new Font("Arial", Font.BOLD, 15));
        searchBar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        searchBar.setBackground(Color.WHITE);
        searchBar.setForeground(Color.BLACK);
        searchBar.setCaretColor(Color.BLACK);
        searchBar.setName(TOOL_TIP_TEXT_KEY);
        ;
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchBar.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchBar.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchBar.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }

        });

        add(searchBar);
        add(lab);
        setSize(1280, 800);

        setLayout(null);

    }

    public static void main(String[] args) {
        new Users(F);
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

class CenteredRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setHorizontalAlignment(CENTER);
        return c;
    }
}