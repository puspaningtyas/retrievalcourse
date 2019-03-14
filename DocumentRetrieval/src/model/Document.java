/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 *
 * @author puspaningtyas
 */
public class Document implements Comparable<Document>{

    private int id;
    private String content;

    public Document() {
    }

    public Document(int id) {
        this.id = id;
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
                Posting temPosting = new Posting(tempString[0],this);
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
                Posting temPosting = new Posting(tempString[i],this);
                int indexCari = Collections.binarySearch(result,temPosting);
                // jika hasil cari kurang dari 0  (obyek tidak ada)
                if (indexCari <0){
                    // set atribut document, gunakan this
                    // tambahkan ke ArrayList result
                    result.add(temPosting);
                } else{
                // lainnya   (obyek ada)
                    // ambil postingnya, 
                    // tambahkan atribut numberOfTerm dengan 1
                    // dgn fungsi get
                    // int tempNumber = get(indekshasilCari).getNumberOfTerm()+1;
                    int tempNumber = result.get(indexCari).getNumberOfTerm()+1;
                    // atau get(indekshasilcari.setNumberOfTerm(tempNumber)
                    result.get(indexCari).setNumberOfTerm(tempNumber);
                }
            }
        }
        return result;
    }

    @Override
    public int compareTo(Document doc) {
        return id-doc.getId();
    }

}
