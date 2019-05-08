/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPDFBox;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


/**
 *
 * @author admin
 */
public class TestPDFBox {
    public static void main(String[] args) {
        try {
            //Loading an existing document
            File file = new File("pdf_reader.pdf");
            PDDocument document = null;
            document = PDDocument.load(file);
            
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();
            
            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
            System.out.println(text);
            
            //Closing the document
            document.close();
        } catch (Exception ex) {
            Logger.getLogger(TestPDFBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
