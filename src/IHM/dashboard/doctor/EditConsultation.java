package IHM.dashboard.doctor;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import BDD.bdd;
import IHM.FQ;
import IHM.Login;
import IHM.dashboard.nurse.AddPatient;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class EditConsultation extends JFrame {
    


    private JButton savButton;
    private JButton cancelButton;
    private Object[][] data;
    private String anc_cons;
    private String nt_cons;
    private String ord;
    private int pr;
    private String med;
    private int num;
    private JLabel title;

    public EditConsultation(String id1, String name, String surname, String nom,String prenom,String age,String sexe,String ntel,String adresse) {
        super("Ajouter Patient");
        bdd db = new bdd();
        dragFrame move = new dragFrame(this);

        num = db.getPatientId(nom);
        anc_cons = db.getAnc(num);
        nt_cons = db.getntCons(num);
        ord = db.getOrd(num);
        pr = db.getPrix(num);

        System.out.println("\n"+ anc_cons +"\n" + "\n"+ nt_cons +"\n" + "\n"+ ord +"\n" + "\n"+ pr +"\n");
        

        // this.addMouseListener(move);
        // this.addMouseMotionListener(move);
        title = new JLabel("Modifier Consultation");
        title.setBounds(250, 30, 500, 50);
        title.setFont(new Font("monospace", Font.BOLD, 24));

        add(title);

        JLabel xExit = new JLabel("X");
        JLabel _minimise = new JLabel("_");
        ImageIcon backGround = new ImageIcon("./src/IMJ/form2.jpg");
        JLabel bgLab = new JLabel(backGround);
        bgLab.setBounds(0, 0, 800, 600);
        xExit.setFont(new Font("monospace", Font.BOLD, 15));
        xExit.setForeground(Color.black);
        xExit.setBounds(770, 7, 40, 20);
        xExit.addMouseListener(new MouseInputAdapter() {

            @Override
            public void mousePressed(MouseEvent ev) {
                dispose();
                Conspage.consBtn.setEnabled(true);
                Conspage.deleteBtn.setEnabled(true);
                Conspage.printBtn.setEnabled(true);
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
        _minimise.setBounds(740, 2, 10, 20);

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


        JLabel anclabel = new JLabel("Ancienne maladie");
        anclabel.setForeground(Color.BLACK);
        anclabel.setBounds(100, 130, 150, 30);
        JTextArea ancField = new JTextArea(anc_cons);
        ancField.setBorder(BorderFactory.createLineBorder(Color.black));          
        ancField.setBounds(250, 130, 500, 100);
        
        String nom_string="Patient: ";
        AffineTransform a=new AffineTransform();
        FontRenderContext frc =new FontRenderContext(a, true, true);
        Font font =new Font("Tahoma",Font.PLAIN,12);
        
        JLabel lab_name=new JLabel("Patient: "+nom);
        lab_name.setForeground(Color.BLACK);
        lab_name.setBounds(200, 70, (int)(font.getStringBounds(nom_string,frc).getWidth())+(int)(font.getStringBounds(nom,frc).getWidth())+14, 30);
        
        JLabel lab_famil=new JLabel(prenom);
        int x=(int)(font.getStringBounds(nom_string,frc).getWidth())+(int)(font.getStringBounds(nom,frc).getWidth())+210;
        lab_famil.setBounds(x, 70, (int)(font.getStringBounds(prenom,frc).getWidth())+14, 30);
        lab_famil.setForeground(Color.BLACK);
   
        JLabel age_=new JLabel(" - Age: "+age+" ans");
        age_.setForeground(Color.BLACK);
        String age_string="Age: ";
        String age_string1=" ans";
        int y=(int)(font.getStringBounds(prenom,frc).getWidth())+14;
        age_.setBounds(x+y, 70, (int)(font.getStringBounds(age_string,frc).getWidth())+(int)(font.getStringBounds(age,frc).getWidth())+(int)(font.getStringBounds(age_string1,frc).getWidth())+14, 30);
        
        JLabel sexe_=new JLabel(" - "+sexe);
        int z= (int)(font.getStringBounds(age_string,frc).getWidth())+(int)(font.getStringBounds(age,frc).getWidth())+(int)(font.getStringBounds(age_string1,frc).getWidth())+14+x+y;
        sexe_.setBounds(z, 70,(int)(font.getStringBounds(sexe,frc).getWidth())+14, 30);
        sexe_.setForeground(Color.BLACK);
        
        JLabel ntel_=new JLabel(" - "+ntel);
        ntel_.setForeground(Color.BLACK);
        int h=z+(int)(font.getStringBounds(sexe,frc).getWidth())+10;
        ntel_.setBounds(h, 70, (int)(font.getStringBounds(ntel,frc).getWidth())+14, 30);

        JLabel adresse_=new JLabel(" - "+adresse);
        adresse_.setForeground(Color.BLACK);
        int f=h+(int)(font.getStringBounds(ntel,frc).getWidth())+10;
        adresse_.setBounds(f, 70, (int)(font.getStringBounds(adresse,frc).getWidth())+14, 30);
        
       
        JLabel consLabel = new JLabel("Notes de consultation");
        consLabel.setForeground(Color.BLACK);
        consLabel.setBounds(100, 260, 150, 30);
        JTextArea consField= new JTextArea(nt_cons); 
        consField.setBorder(BorderFactory.createLineBorder(Color.black));  
        consField.setBounds(250, 260, 500, 100);

        JLabel ordLabel = new JLabel("Ordonnance");
        ordLabel.setForeground(Color.BLACK);
        ordLabel.setBounds(100, 390, 150, 30);

        JLabel ordField = new JLabel();   
        ordField.setBounds(250, 390, 500, 30);
        JTextField ordField1 = new JTextField(ord);
        ordField1.setBounds(250, 390, 400, 30);        
        ordField.setBorder(BorderFactory.createLineBorder(Color.black));
       
        
        JButton addMed = new JButton("Ajouter");
        addMed.setBounds(650, 390, 100, 30);
        addMed.setForeground(Color.BLACK);
        addMed.setBackground(Color.WHITE);

        addMed.addActionListener(e ->{
            
            // Créer une boîte de dialogue
            JDialog d = new JDialog(this, "Médicaments"); 
            
            // Créer une étiquette
            JLabel l = new JLabel("Ceci est une boite de dialogue."); 

            String header[] = {"N°", "Médicament"};
            data = db.getMédicament();
            System.out.println("SQL :\t"+ data);

            DefaultTableModel model = new DefaultTableModel(data, header);
           

            JTable tab  = new JTable(model) {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            
        };       
        JScrollPane sp = new JScrollPane(tab);  

        tab.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me){
                JTable target = (JTable) me.getSource();
                med = data[target.getSelectedRow()][1].toString();
             }

             @Override
             public void mousePressed(MouseEvent me){
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased(MouseEvent me){
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
        JPanel p = new JPanel();
        p.add(sp);
        
           
            // Ajouter l'étiquette à la boîte de dialogue 
            JButton ok = new JButton("OK");
            ok.setBounds(150, 550, 50, 50);
            
            
            p.add(ok);
            d.add(p);
            ok.addActionListener(g -> {
                if(!ordField1.getText().isEmpty()){
                ordField1.setText(ordField1.getText() + " - " + med);                
                ordField1.setForeground(Color.BLACK);
                d.dispose();}
                else{
                ordField1.setText(med);                
                ordField1.setForeground(Color.BLACK);
                d.dispose();}
            }
           
            );
            // Définir la taille de la boîte de dialogue 
            d.setBounds(400, 150, 600, 500);
            // Définir la visibilité de la boîte de dialogue
            d.setVisible(true); 
        } );

        JLabel pricelabel = new JLabel("Prix Consultation");
        pricelabel.setForeground(Color.BLACK);
        pricelabel.setBounds(280, 440, 150, 30);
        JTextField priceField = new JTextField(""+pr+"");   
        priceField.setForeground(Color.BLACK);        
        priceField.setBounds(380, 440, 150, 30);

        JButton print = new JButton("imprimer");
        print.setBounds(530, 440, 100, 30);
        print.setForeground(Color.BLACK);
        print.setBackground(Color.WHITE);
        print.addActionListener(e ->{
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(new function.InvoicePrinter(name, surname, nom, prenom, age, sexe, ntel, adresse, consField.getText(), ordField1.getText(), priceField.getText()));
            
            // Uncomment the following line to display a print dialog
        // if (job.printDialog()) {
        try {
            job.print();
        } catch (PrinterException err) {
            err.printStackTrace();
        }
        });
       
        savButton = new JButton("Sauvegarder");
        savButton.setBounds(250, 520, 150, 30);
        savButton.setForeground(Color.WHITE);
        savButton.setBackground(Color.GREEN);
        cancelButton = new JButton("Annuler");
        cancelButton.setBounds(450, 520, 150, 30);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.RED);

        cancelButton.addActionListener(e -> {
            dispose();
            Conspage.consBtn.setEnabled(true);
            Conspage.deleteBtn.setEnabled(true);
            Conspage.printBtn.setEnabled(true);
        });

        savButton.addActionListener(e ->{
            System.out.print(nom);
            int idPatient = db.getPatientId(nom);
            boolean isAncEmpty = ancField.getText().isEmpty();
            boolean isConsEmpty = consField.getText().isEmpty();
            boolean isOrdEmpty = ordField1.getText().isEmpty();
            boolean isPriceEmpty = priceField.getText().isEmpty();

            if (isAncEmpty || isConsEmpty || isOrdEmpty || isPriceEmpty){
                JOptionPane.showMessageDialog(null, "Veuillez remplir toutes les cases", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            } else{
                String anc = ancField.getText();
                String cons = consField.getText();
                String ord = ordField1.getText();
                String price = priceField.getText();
                System.out.println("\n tableau Consultation : \n id ="+idPatient+"\n cons ="+cons+"\n cons ="+cons+"\n ord ="+ord+"\n price ="+price);
               if(db.UpdateCons(idPatient, anc, cons, ord, price)){
                JOptionPane.showMessageDialog(null, "Consultation modifié et enregistré", "Succes", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon("./img/logo.jpg"));
               } else {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
            }     

            }
        });
        
        bgLab.add(xExit);
        bgLab.add(_minimise);
        bgLab.add(cancelButton);
        bgLab.add(savButton);
        add(bgLab);
          
        bgLab.add(lab_name);
        bgLab.add(lab_famil);
          
        bgLab.add(anclabel);
        bgLab.add(ancField);
        bgLab.add(consLabel);
        bgLab.add(consField);
        bgLab.add(ordLabel);
        bgLab.add(ordField);
        
        bgLab.add(ordField1);
        bgLab.add(addMed);
        bgLab.add(pricelabel);
        bgLab.add(priceField);
        bgLab.add(print);

          
        bgLab.add(age_);
        bgLab.add(sexe_);
        bgLab.add(ntel_);
        bgLab.add(adresse_);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);

    }
}

class dragFrame1 extends MouseInputAdapter {

    private int posX, posY;
    private final JFrame frame;

    dragFrame1(JFrame frame) {
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

