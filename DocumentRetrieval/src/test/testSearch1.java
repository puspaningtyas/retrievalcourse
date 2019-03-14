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
public class testSearch1 {

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
        // bikin dictionary
        index.makeDictionaryWithTermNumber();
        
        // id dokumen
        int idDoc =1;
        System.out.println("Id Dokument = "+idDoc);
        ArrayList<Posting> tempDocWeight = index.makeTFIDF(idDoc);
        for (int i = 0; i < tempDocWeight.size(); i++) {
            Posting tempPost = tempDocWeight.get(i);
            System.out.println("term= "+tempPost.getTerm()+
                    ", tf = "+tempPost.getNumberOfTerm()+
                    ", weight= "+tempPost.getWeight());
        }
        
        System.out.println("Length of Dokumen = "+index.getLengthOfPosting(tempDocWeight));
        
        String query = "silver gold truck";
        System.out.println("query = "+query);
        ArrayList<Posting> queryPostingList = index.getQueryPosting(query);
        for (int i = 0; i < queryPostingList.size(); i++) {
            Posting tempPost = queryPostingList.get(i);
            System.out.println("term= "+tempPost.getTerm()+
                    ", tf = "+tempPost.getNumberOfTerm()+
                    ", weight= "+tempPost.getWeight());
        }
        
        // test cosine similarity
        System.out.println("Cosine similarity = "+index.getCosineSimilarity(queryPostingList, 
                tempDocWeight));
    }
}
