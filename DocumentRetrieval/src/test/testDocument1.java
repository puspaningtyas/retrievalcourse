/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Document;

/**
 *
 * @author admin
 */
public class testDocument1 {
    public static void main(String[] args) {
        Document doc1 = new Document(1, "computer information retrieval.");
        Document doc2 = new Document(2, "computer organization and architecture");
        String result[] = doc1.getListofTerm();
        for (int i = 0; i < result.length; i++) {
            System.out.println("term "+i+" = "+result[i]);
        }
        String result1[] = doc2.getListofTerm();
        for (int i = 0; i < result1.length; i++) {
            System.out.println("term "+i+" = "+result1[i]);
        }
        
    }
}
