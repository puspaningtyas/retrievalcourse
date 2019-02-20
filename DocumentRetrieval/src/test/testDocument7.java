/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.List;
import model.Document;
import model.InvertedIndex;
import model.Posting;
import model.Term;

/**
 *
 * @author admin
 */
public class testDocument7 {

    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "computer information retrieval.");
        Document doc2 = new Document(2, "computer organization and architecture");
        Document doc3 = new Document(3, "machine learning architecture");
        
        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // panggil fungsi search
        ArrayList<Posting> result = index.search("machine");
        // tampilkan isi document dan id-nya
        for (int i = 0; i < result.size(); i++) {
            System.out.println("id_doc = " +result.get(i).getDocument().getId());
            System.out.println(result.get(i).getDocument().getContent());
        }
        
        // panggil fungsi search
        ArrayList<Posting> result1 = index.search("machine learning");
        // tampilkan isi document dan id-nya
        for (int i = 0; i < result1.size(); i++) {
            System.out.println("id_doc = " +result1.get(i).getDocument().getId());
            System.out.println(result1.get(i).getDocument().getContent());
        }
        
    }
}
