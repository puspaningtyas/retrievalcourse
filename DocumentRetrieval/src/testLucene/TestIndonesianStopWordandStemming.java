/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testLucene;

import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/**
 *
 * @author admin
 */
public class TestIndonesianStopWordandStemming {
    public static void main(String[] args) {
        String text = "Dia sedang pergi berbelanja di pusat perbelanjaan. "
                + "Namun, ibunya melarangnya pergi kesana karena tempat itu sangat berbahaya.";
        System.out.println("Text = "+text);
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new IndonesianAnalyzer();
        analyzer.setVersion(matchVersion);
        // ambil stopwords
        CharArraySet stopWords = IndonesianAnalyzer.getDefaultStopSet();
        // buat token
        TokenStream tokenStream = analyzer.tokenStream(
                "myField",
                new StringReader(text.trim()));
        // buang stop word
        tokenStream = new StopFilter(tokenStream, stopWords);
        // buat string baru tanpa stopword
        StringBuilder sb = new StringBuilder();
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String term = charTermAttribute.toString();
                sb.append(term + " ");
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        String newText = sb.toString();
        System.out.println("New Text = "+newText);
    
    }
}
