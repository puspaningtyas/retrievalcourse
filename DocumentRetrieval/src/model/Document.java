/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author puspaningtyas
 */
public class Document {
    private int id;
    private String content;

    public Document() {
    }

    public Document(String content) {
        this.content = content;
    }

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
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
    
    public String[] getListofTerm(){
        String value = this.getContent();
        value = value.replaceAll("[.,?!]", "");
        return value.split(" ");
    }
    
    public ArrayList<Posting> getListofPosting(){
        // panggil fungsi getListOfTerm
        // buat objek ArrayList<Posting> result untuk menampung hasil
        // buat looping sebanyak listOfTerm
            // di dalam looping
            // jika term pertama maka
                 // buat object tempPosting
                 // set atribut document, gunakan this
                 // tambahkan ke ArrayList result
            // lainnya
                 // sorting ArayList result
                 // cek apakah term sudah ada
                 // gunakan fungsi search dengan luaran indeks obyek yang memenuhi
                 // jika hasil cari kurang dari 0  (obyek tidak ada)
                     // buat object tempPosting
                     // set atribut document, gunakan this
                     // tambahkan ke ArrayList result
                 // lainnya   (obyek ada)
                     // ambil postingnya, 
                     // tambahkan atribut numberOfTerm dengan 1
                     // dgn fungsi get
                     // int tempNumber = get(indekshasilCari).getNumberOfTerm()+1;
                     // atau get(indekshasilcari.setNumberOfTerm(tempNumber)
        return null;
    }
    
}
