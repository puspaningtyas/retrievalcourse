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
public class Cluster {
    private ArrayList<Document> member = new ArrayList<Document>();
    private Document center = new Document();

    public Cluster() {
    }

    /**
     * @return the member
     */
    public ArrayList<Document> getMember() {
        return member;
    }

    /**
     * @param member the member to set
     */
    public void setMember(ArrayList<Document> member) {
        this.member = member;
    }

    /**
     * @return the center
     */
    public Document getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(Document center) {
        this.center = center;
    }
    
}
