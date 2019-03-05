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
public class Posting implements Comparable<Posting> {

    private String term;
    private Document document;
    private int numberOfTerm = 1;
    private double weight=0.0; // nilai TF-IDF

    public Posting() {
    }

    
    public Posting(Document document) {
        this.document = document;
    }

    public Posting(String term, Document document) {
        this.term = term;
        this.document = document;
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

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    @Override
    public int compareTo(Posting posting) {
        int result = 0;
        result = term.compareToIgnoreCase(posting.getTerm());
        if (result == 0) {
            if(posting.getDocument()!=null){
                result = getDocument().getId() - 
                        posting.getDocument().getId();
                return result;
            } else{
                return result;
            }
        } else {
            return result;
        }
    }

    /**
     * @return the numberOfTerm
     */
    public int getNumberOfTerm() {
        return numberOfTerm;
    }

    /**
     * @param numberOfTerm the numberOfTerm to set
     */
    public void setNumberOfTerm(int numberOfTerm) {
        this.numberOfTerm = numberOfTerm;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
