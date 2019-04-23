/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testLucene;

import model.Document;

/**
 *
 * @author admin
 */
public class TestDocumentWithStemmedContent {
    public static void main(String[] args) {
        Document doc = new Document(1,"He was a man with gun");
        System.out.println("Without Stemming");
        System.out.println(doc);
        System.out.println("With stemming");
        doc.stemming();
        System.out.println(doc);
    }
 
}
