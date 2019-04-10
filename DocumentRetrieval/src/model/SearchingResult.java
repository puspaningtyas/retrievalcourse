/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class SearchingResult implements Comparable<SearchingResult>{
    public static final int FAKTOR=1000; // faktor pengali similarity
    private double similarity;
    private Document document;

    public SearchingResult(double similarity, Document document) {
        this.similarity = similarity;
        this.document = document;
    }

    /**
     * @return the similarity
     */
    public double getSimilarity() {
        return similarity;
    }

    /**
     * @param similarity the similarity to set
     */
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    @Override
    public int compareTo(SearchingResult result) {
        return Double.compare(result.getSimilarity(),similarity); 
    }
    
}
