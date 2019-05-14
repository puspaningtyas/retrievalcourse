/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.id.IndonesianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author puspaningtyas
 */
public class Document implements Comparable<Document> {

    private int id;
    private String content; // atribut content yang dianalisis
    private String realContent; // atribut content asli
    // list posting untuk keperluan clustering document
    private ArrayList<Posting> listOfClusteringPosting = new ArrayList<Posting>();

    public Document() {
    }

    public Document(int id) {
        this.id = id;
    }

    public Document(String content) {
        this.content = content;
        this.realContent = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
        this.realContent = content;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public String[] getListofTerm() {
        String value = this.getContent();
        value = value.replaceAll("[.,?!]", "");
        return value.split(" ");
    }

    public ArrayList<Posting> getListofPosting() {
        // panggil fungsi getListOfTerm
        String tempString[] = getListofTerm();
        // buat objek ArrayList<Posting> result untuk menampung hasil
        ArrayList<Posting> result = new ArrayList<Posting>();
        // buat looping sebanyak listOfTerm
        for (int i = 0; i < tempString.length; i++) {
            // di dalam looping
            // jika term pertama maka
            if (i == 0) {
                // buat object tempPosting
                Posting temPosting = new Posting(tempString[0], this);
                // set atribut document, gunakan this
                // tambahkan ke ArrayList result
                result.add(temPosting);
            } else {
                // lainnya
                // sorting ArayList result
                Collections.sort(result);
                // cek apakah term sudah ada
                // gunakan fungsi search dengan luaran indeks obyek yang memenuhi
                // buat object tempPosting           
                Posting temPosting = new Posting(tempString[i], this);
                int indexCari = Collections.binarySearch(result, temPosting);
                // jika hasil cari kurang dari 0  (obyek tidak ada)
                if (indexCari < 0) {
                    // set atribut document, gunakan this
                    // tambahkan ke ArrayList result
                    result.add(temPosting);
                } else {
                    // lainnya   (obyek ada)
                    // ambil postingnya, 
                    // tambahkan atribut numberOfTerm dengan 1
                    // dgn fungsi get
                    // int tempNumber = get(indekshasilCari).getNumberOfTerm()+1;
                    int tempNumber = result.get(indexCari).getNumberOfTerm() + 1;
                    // atau get(indekshasilcari.setNumberOfTerm(tempNumber)
                    result.get(indexCari).setNumberOfTerm(tempNumber);
                }
            }
        }
        return result;
    }

    @Override
    public int compareTo(Document doc) {
        return id - doc.getId();
    }

    /**
     * Fungsi untuk membaca sebuah file *.txt dan hasil baca dimasukkan ke
     * atribut content
     */
    public void readFile(int idDoc, File file) {
        // simpan idDoc
        this.id = idDoc;
        // baca file
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", content=" + content + ", realContent=" + realContent + '}';
    }

    /**
     * Fungsi untuk menghilangkan kata stop word
     */
    public void removeStopWords() {
        // asumsi content sudah ada
        String text = content;
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(matchVersion);
        // ambil stopwords
        CharArraySet stopWords = EnglishAnalyzer.getDefaultStopSet();
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
        content = sb.toString();
    }

    /**
     * Fungsi untuk menghilangkan stop word dan stemming
     */
    public void stemming() {
        String text = content;
//        System.out.println("Text = "+text);
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(matchVersion);
        // buat token
        TokenStream tokenStream = analyzer.tokenStream(
                "myField",
                new StringReader(text.trim()));
        // stemming
        tokenStream = new PorterStemFilter(tokenStream);
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
        content = sb.toString();
    }

    /**
     * @return the realContent
     */
    public String getRealContent() {
        return realContent;
    }

    /**
     * @param realContent the realContent to set
     */
    public void setRealContent(String realContent) {
        this.realContent = realContent;
    }

    /**
     * Fungsi untuk mensteming content dalam bahasa indonesia
     */
    public void IndonesiaStemming() {
        Version matchVersion = Version.LUCENE_7_7_0; // Substitute desired Lucene version for XY
        Analyzer analyzer = new IndonesianAnalyzer();
        analyzer.setVersion(matchVersion);
        // ambil stopwords
        CharArraySet stopWords = IndonesianAnalyzer.getDefaultStopSet();
        // buat token
        TokenStream tokenStream = analyzer.tokenStream(
                "myField",
                new StringReader(realContent.trim()));
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
        content = sb.toString();
    }

    /**
     * Fungsi baca file pdf yang dibentu dari pengolah kata. File pdf tidak bisa
     * dibaca jika bentuknya gambar/citra
     *
     * @param pdfFile
     */
    public void readPDFFile(File pdfFile) {
        try {
            //Loading an existing document
            PDDocument document = null;
            document = PDDocument.load(pdfFile);
            
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();
            
            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
//            System.out.println(text);
            realContent = text;
            //Closing the document
            document.close();
        } catch (IOException ex) {
            Logger.getLogger(Document.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the listOfPosting
     */
    public ArrayList<Posting> getListOfPosting() {
        return listOfClusteringPosting;
    }

    /**
     * @param listOfPosting the listOfPosting to set
     */
    public void setListOfPosting(ArrayList<Posting> listOfPosting) {
        this.listOfClusteringPosting = listOfPosting;
    }

}
