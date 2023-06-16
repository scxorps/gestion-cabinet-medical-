package function;


import java.awt.*;
import java.awt.print.*;

import javax.swing.ImageIcon;

public class InvoicePrinter implements Printable {
    private String nom;
    private String prenom;
    private String age;
    private String sexe;
    private String ntel;
    private String adresse;
    private String consFieldText;
    private String ordField1Text;
    private String priceFieldText;

    public InvoicePrinter(String nom, String prenom, String age, String sexe, String ntel, String adresse, String consFieldText, String ordField1Text, String priceFieldText) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.ntel = ntel;
        this.adresse = adresse;
        this.consFieldText = consFieldText;
        this.ordField1Text = ordField1Text;
        this.priceFieldText = priceFieldText;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Set the font and graphics context
        Font titleFont = new Font("Arial", Font.BOLD, 28);
        Font labelFont = new Font("Arial", Font.BOLD, 22);
        Font valueFont = new Font("Arial", Font.PLAIN, 22);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(valueFont);

        // Calculate the printable area
        int x = (int) pf.getImageableX();
        int y = (int) pf.getImageableY();
        int width = (int) pf.getImageableWidth();
        int height = (int) pf.getImageableHeight();

        ImageIcon logo = new ImageIcon("./src/IMJ/logo1.png");
        Image scaledLogo = logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        g2d.drawImage(scaledLogo, x, y, null);

        // Print the invoice content
        g2d.setFont(titleFont);
        g2d.drawString("Maladie", x + 200, y + 30);

        g2d.setFont(labelFont);
        g2d.drawString("Nom:", x, y + 120);
        g2d.drawString("Prenom:", x, y + 160);
        g2d.drawString("Age:", x, y + 200);
        g2d.drawString("Sexe:", x, y + 240);
        g2d.drawString("N° Tel:", x, y + 280);
        g2d.drawString("Adresse:", x, y + 320);
        g2d.drawString("Consultation:", x, y + 400);
        g2d.drawString("Ordennance:", x, y + 440);
        g2d.drawString("Price:", x, y + 480);

        g2d.setFont(valueFont);
        g2d.drawString(nom, x + 200, y + 120);
        g2d.drawString(prenom, x + 200, y + 160);
        g2d.drawString(age, x + 200, y + 200);
        g2d.drawString(sexe, x + 200, y + 240);
        g2d.drawString(ntel, x + 200, y + 280);
        g2d.drawString(adresse, x + 200, y + 320);
        g2d.drawString(consFieldText, x + 200, y + 400);
        g2d.drawString(ordField1Text, x + 200, y + 440);
        g2d.drawString(priceFieldText, x + 200, y + 480);

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        String nom = "John";
        String prenom = "Doe";
        String age = "30";
        String sexe = "Male";
        String ntel = "123456789";
        String adresse = "123 Main St";
        String consFieldText = "Consultation";
        String ordField1Text = "Order 1";
        String priceFieldText = "$100.00";

        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new InvoicePrinter(nom, prenom, age, sexe, ntel, adresse, consFieldText, ordField1Text, priceFieldText));

        // Uncomment the following line to display a print dialog
        // if (job.printDialog()) {
        try {
            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
        // }
    }
}
