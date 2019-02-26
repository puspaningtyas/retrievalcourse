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
public class testTFIDF1 {

    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "Shipment of gold damaged in a fire");
        Document doc2 = new Document(2, "delivery of silver arrived in a silver truck");
        Document doc3 = new Document(3, "shipment of gold arrived in a truck");
        
        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        
        // panggil fungsi search
        index.makeDictionary();
        ArrayList<Posting> result = index.search("machine learning inteligence");
        // tampilkan isi document dan id-nya
        for (int i = 0; i < result.size(); i++) {
            System.out.println("id_doc = " +result.get(i).getDocument().getId());
            System.out.println(result.get(i).getDocument().getContent());
        }
        
    }
}
