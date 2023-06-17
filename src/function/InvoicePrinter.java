package function;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

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

    public InvoicePrinter(String nom, String prenom, String age, String sexe, String ntel, String adresse,
            String consFieldText, String ordField1Text, String priceFieldText) {
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
        Font titleFont = new Font("Arial", Font.BOLD, 18);
        Font labelFont = new Font("Arial", Font.BOLD, 12);
        Font valueFont = new Font("Arial", Font.PLAIN, 12);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(valueFont);

        // Calculate the printable area
        int x = (int) pf.getImageableX();
        int y = (int) pf.getImageableY();
        int width = (int) pf.getImageableWidth();
        int height = (int) pf.getImageableHeight();

        // Print the invoice content
        g2d.setFont(titleFont);
        g2d.drawString("Consultation", x + 200, y + 30);

        g2d.setFont(labelFont);
        g2d.drawString("Nom:", x, y + 60);
        g2d.drawString("Prenom:", x, y + 80);
        g2d.drawString("Age:", x, y + 100);
        g2d.drawString("Sexe:", x, y + 120);
        g2d.drawString("N° Tel:", x, y + 140);
        g2d.drawString("Adresse:", x, y + 160);
        g2d.drawString("Consultaion :", x, y + 200);

        g2d.setFont(valueFont);
        g2d.drawString(nom, x + 100, y + 60);
        g2d.drawString(prenom, x + 100, y + 80);
        g2d.drawString(age, x + 100, y + 100);
        g2d.drawString(sexe, x + 100, y + 120);
        g2d.drawString(ntel, x + 100, y + 140);
        g2d.drawString(adresse, x + 100, y + 160);

        // Split and print the multiline consField
        String[] consLines = consFieldText.split("\\n");
        int consLineY = y + 200;
        for (String line : consLines) {
            g2d.drawString(line, x + 100, consLineY);
            consLineY += 20; // Adjust the line spacing (20 pixels here, but you can modify it as needed)
        }

        g2d.drawString("Ordannace:", x, y + consLineY + 20);
        g2d.drawString("Prix:", x, y + consLineY + 40);
        g2d.drawString(ordField1Text, x + 100, y + consLineY + 20);
        g2d.drawString(priceFieldText, x + 100, y + consLineY + 40);

        return PAGE_EXISTS;
    }
}