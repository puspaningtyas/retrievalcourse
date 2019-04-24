/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testLucene;

import org.apache.lucene.analysis.id.IndonesianStemmer;

/**
 *
 * @author admin
 */
public class TestIndonesiaStemmer {
    public static void main(String[] args) {
        IndonesianStemmer stem = new IndonesianStemmer();
        String term = "membunuh";
        char stemTerm[] = term.toCharArray();
        int hasil = stem.stem(stemTerm, term.length(), true);
        System.out.println(hasil);
        System.out.println(stemTerm);
    }
}
