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
        IndonesianStemmer analyzer = new IndonesianStemmer();
        String term = "mendukung";
        char stemTerm[] = term.toCharArray();
        int hasil = analyzer.stem(stemTerm, term.length(), true);
        System.out.println(hasil);
        System.out.println(stemTerm);
        char stemResult[] = new char[hasil];
        for (int i = 0; i < hasil; i++) {
            stemResult[i] = stemTerm[i];
        }
        System.out.println(stemResult);
    }
}
