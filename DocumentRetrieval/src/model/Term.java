/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Term implements Comparable<Term>{
    private String term;
    private ArrayList<Posting> postingList = new ArrayList<Posting>();

    public Term(String term) {
        this.term = term;
    }

    public int getNumberOfDocument(){
        return postingList.size();
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

    /**
     * @return the postingList
     */
    public ArrayList<Posting> getPostingList() {
        return postingList;
    }

    /**
     * @param postingList the postingList to set
     */
    public void setPostingList(ArrayList<Posting> postingList) {
        this.postingList = postingList;
    }

    @Override
    public int compareTo(Term o) {
        return term.compareToIgnoreCase(o.getTerm());
    }

    @Override
    public String toString() {
        return "Term{" + "term=" + term + ", postingList=" + postingList + '}';
    }

    
}
