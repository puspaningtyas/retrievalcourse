/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import model.Document;
import model.Posting;

/**
 *
 * @author admin
 */
public class testDocument3 {

    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "computer information retrieval.");
        Document doc2 = new Document(2, "computer organization and architecture");
        ArrayList<Document> listOfDocument = new ArrayList<Document>();
        listOfDocument.add(doc1);
        listOfDocument.add(doc2);
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk listofdocument
        for (int i = 0; i < listOfDocument.size(); i++) {
            // buat listOfTerm dari document ke -i
            String[] termResult = listOfDocument.get(i).getListofTerm();
            // loop sebanyak term dari document ke i
            for (int j = 0; j < termResult.length; j++) {
                // buat object tempPosting
                Posting tempPosting = new Posting(termResult[j],
                        listOfDocument.get(i));
                list.add(tempPosting);
            }
        }

        // panggil list posting
        System.out.println("Ukuran list = " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTerm() + "," + list.get(i).getDocument().getId());
        }
    }
}
