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
public class testDocument6 {

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
        // panggil fungsi make dictionary
        index.makeDictionary();
        // panggil term yang ada dan jumlah posting
        for (int i = 0; i < index.getDictionary().size(); i++) {
            Term tempTerm = index.getDictionary().get(i);
            System.out.println(tempTerm.getTerm()+","
                    +tempTerm.getNumberOfDocument());
            for (int j = 0; j < tempTerm.getNumberOfDocument(); j++) {
                Posting tempPosting = tempTerm.getPostingList().get(j);
                Document tempDoc = tempPosting.getDocument();
                System.out.println("idDoc = "+tempDoc.getId());
            }
        }
        
    }
}
