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
public class testDocument2 {
    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "computer information retrieval.");
        Document doc2 = new Document(2, "computer organization and architecture");
        // tokenisasi dokumen
        String tokenDoc1[] = doc1.getListofTerm();
        String tokenDoc2[] = doc2.getListofTerm();
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk doc1
        for (int i = 0; i < tokenDoc1.length; i++) {
            // buat temp Posting
            Posting tempPosting = new Posting(tokenDoc1[i],doc1);
            // masukkan ke list
            list.add(tempPosting);
        }
        // buat node Posting utk doc2
        for (int i = 0; i < tokenDoc2.length; i++) {
            // buat temp Posting
            Posting tempPosting = new Posting(tokenDoc2[i],doc2);
            // masukkan ke list
            list.add(tempPosting);
        }
        
        // panggil list posting
        System.out.println("Ukuran list = "+list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTerm()+","+list.get(i).getDocument().getId());
        }
    }
}
