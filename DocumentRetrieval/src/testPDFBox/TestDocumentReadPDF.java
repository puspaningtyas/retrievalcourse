/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testPDFBox;

import java.io.File;
import model.Document;

/**
 *
 * @author admin
 */
public class TestDocumentReadPDF {

    public static void main(String[] args) {
        //Loading an existing document
        File file = new File("pdf_reader.pdf");
        Document doc = new Document();
        doc.setId(1);
        doc.readPDFFile(file);
        System.out.println(doc);
    }
}
