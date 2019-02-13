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
public class testDocument {
    public static void main(String[] args) {
        Document doc1= new Document(1, "computer information retrieval.");
        // kelurannya kata computer information retrieval 
        // dipotong-potong menjadi 3 string
        // luarnnya:
        // computer
        // information
        // retrieval
        String result[] = doc1.getListofTerm();
        for (int i = 0; i < result.length; i++) {
            System.out.println("term "+i+" = "+result[i]);
        }
    }
}
