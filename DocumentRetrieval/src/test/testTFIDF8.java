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
public class testTFIDF8 {

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
        index.makeDictionaryWithTermNumber();
        ArrayList<Term> result = index.getDictionary();
        // tampilkan isi document dan id-nya
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println("Term = "+result.get(i).getTerm()+
//                    ",numberOfDocument = "+result.get(i).getNumberOfDocument());
//            ArrayList<Posting> tempPosting = result.get(i).getPostingList();
//            for (int j = 0; j < tempPosting.size(); j++) {
//                System.out.println("id_doc = "+tempPosting.get(j).getDocument().getId()
//                +", numberofTerm = "+tempPosting.get(j).getNumberOfTerm());
//            }
//        }
        
        // number of document
        String tempString = "silver";
        int result2 = index.getDocumentFrequency(tempString);
        System.out.println("Number of Doc with "+tempString+" is "+result2);
        
        // idf
        String tempString1 = "silver";
        double result3 = index.getInverseDocumentFrequency(tempString1);
        System.out.println("IDF of "+tempString1+" is "+result3);
        
        // tf
        // idf
        String tempString2 = "truck";
        int idDoc=2;
        int result4 = index.getTermFrequency(tempString2, idDoc);
        System.out.println("TF of "+tempString2+" in idDoc = "+idDoc+ " is "+result4);
        
        // make arraylist of TFIDF
        idDoc=1;
        System.out.println("Id Dokument = "+idDoc);
        ArrayList<Posting> tempDocWeight = index.makeTFIDF(idDoc);
        for (int i = 0; i < tempDocWeight.size(); i++) {
            Posting tempPost = tempDocWeight.get(i);
            System.out.println("term= "+tempPost.getTerm()+
                    ", tf = "+tempPost.getNumberOfTerm()+
                    ", weight= "+tempPost.getWeight());
        }
        
        idDoc=2;
        System.out.println("Id Dokument = "+idDoc);
        ArrayList<Posting> temp1DocWeight = index.makeTFIDF(idDoc);
        for (int i = 0; i < temp1DocWeight.size(); i++) {
            Posting tempPost = temp1DocWeight.get(i);
            System.out.println("term= " + tempPost.getTerm()
                    + ", tf = " + tempPost.getNumberOfTerm()
                    + ", weight= " + tempPost.getWeight());
        }
        
        // panggil fungsi inner product
//        double resultProduct = index.getInnerProduct(temp1DocWeight, tempDocWeight);
//        System.out.println("Hasil inner product doc1 dan doc2 = "+resultProduct);
        
        //panggil fungsi buat postinglist dar sebuah query
        String query = "silver gold truck";
        System.out.println("query = "+query);
        ArrayList<Posting> queryPostingList = index.getQueryPosting(query);
        for (int i = 0; i < queryPostingList.size(); i++) {
            Posting tempPost = queryPostingList.get(i);
            System.out.println("term= "+tempPost.getTerm()+
                    ", tf = "+tempPost.getNumberOfTerm()+
                    ", weight= "+tempPost.getWeight());
        }
        // panggil fungsi inner product
        double result1Product = index.getInnerProduct(queryPostingList, tempDocWeight);
        System.out.println("Hasil inner product query dan doc1= "+result1Product);
//        
    }
}
