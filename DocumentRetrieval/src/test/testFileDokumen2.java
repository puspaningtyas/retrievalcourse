/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import model.Document;
import model.InvertedIndex;

/**
 *
 * @author admin
 */
public class testFileDokumen2 {
    public static void main(String[] args) {
        InvertedIndex index = new InvertedIndex();
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File dir = fc.getSelectedFile();
            index.readDirectory(dir); 
        }
        
        ArrayList<Document> listDoc = index.getListOfDocument();
        for (int i = 0; i < args.length; i++) {
            Document doc = listDoc.get(i);
            System.out.println("Content : "+doc.getId());
            System.out.println(doc.getContent());
        }
    }
}
