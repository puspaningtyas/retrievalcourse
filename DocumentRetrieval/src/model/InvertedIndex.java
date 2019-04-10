/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 *
 * @author admin
 */
public class InvertedIndex {

    private ArrayList<Document> listOfDocument = new ArrayList<Document>();
    private ArrayList<Term> dictionary = new ArrayList<Term>();

    public InvertedIndex() {
    }

    public void addNewDocument(Document document) {
        getListOfDocument().add(document);
    }

    public ArrayList<Posting> getUnsortedPostingList() {
        // cek untuk term yang muncul lebih dari 1 kali
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk listofdocument
        for (int i = 0; i < getListOfDocument().size(); i++) {
            // buat listOfTerm dari document ke -i
            String[] termResult = getListOfDocument().get(i).getListofTerm();
            // loop sebanyak term dari document ke i
            for (int j = 0; j < termResult.length; j++) {
                // buat object tempPosting
                Posting tempPosting = new Posting(termResult[j],
                        getListOfDocument().get(i));
                // cek kemunculan term
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getUnsortedPostingListWithTermNumber() {
        // cek untuk term yang muncul lebih dari 1 kali
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // buat node Posting utk listofdocument
        for (int i = 0; i < getListOfDocument().size(); i++) {
            // buat listOfTerm dari document ke -i
            //String[] termResult = getListOfDocument().get(i).getListofTerm();
            ArrayList<Posting> postingDocument = getListOfDocument().get(i).getListofPosting();
            // loop sebanyak term dari document ke i
            for (int j = 0; j < postingDocument.size(); j++) {
                // ambil objek posting
                Posting tempPosting = postingDocument.get(j);
                // cek kemunculan term
                list.add(tempPosting);
            }
        }
        return list;
    }

    public ArrayList<Posting> getSortedPostingList() {
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // panggil list yang belum terurut
        list = this.getUnsortedPostingList();
        // urutkan
        Collections.sort(list);
        return list;
    }

    public ArrayList<Posting> getSortedPostingListWithTermNumber() {
        // siapkan posting List
        ArrayList<Posting> list = new ArrayList<Posting>();
        // panggil list yang belum terurut
        list = this.getUnsortedPostingListWithTermNumber();
        // urutkan
        Collections.sort(list);
        return list;
    }

    /**
     * Fungsi cari dokumen
     *
     * @param query
     * @return
     */
    public ArrayList<Posting> search(String query) {
        // buat index/dictionary
//        makeDictionary();
        String tempQuery[] = query.split(" ");
        ArrayList<Posting> result = new ArrayList<Posting>();
        for (int i = 0; i < tempQuery.length; i++) {
            String string = tempQuery[i];
            if (i == 0) {
                result = searchOneWord(string);
            } else {
                ArrayList<Posting> result1 = searchOneWord(string);
                result = intersection(result, result1);
            }
        }
        return result;
    }

    /**
     * Fungsi untuk menggabungkan 2 buah posting Made by Johan
     *
     * @param p1
     * @param p2
     * @return
     */
    public ArrayList<Posting> intersection(ArrayList<Posting> p1,
            ArrayList<Posting> p2) {
        if (p1 == null || p2 == null) {
            return new ArrayList<>();
        }

        ArrayList<Posting> postings = new ArrayList<>();
        int p1Index = 0;
        int p2Index = 0;

        Posting post1 = p1.get(p1Index);
        Posting post2 = p2.get(p2Index);

        while (true) {
            if (post1.getDocument().getId() == post2.getDocument().getId()) {
                try {
                    postings.add(post1);
                    p1Index++;
                    p2Index++;
                    post1 = p1.get(p1Index);
                    post2 = p2.get(p2Index);
                } catch (Exception e) {
                    break;
                }

            } else if (post1.getDocument().getId() < post2.getDocument().getId()) {
                try {
                    p1Index++;
                    post1 = p1.get(p1Index);
                } catch (Exception e) {
                    break;
                }

            } else {
                try {
                    p2Index++;
                    post2 = p2.get(p2Index);
                } catch (Exception e) {
                    break;
                }
            }
        }
        return postings;
    }

    public ArrayList<Posting> searchOneWord(String word) {
        Term tempTerm = new Term(word);
        if (getDictionary().isEmpty()) {
            // dictionary kosong
            return null;
        } else {
            int positionTerm = Collections.binarySearch(dictionary, tempTerm);
            if (positionTerm < 0) {
                // tidak ditemukan
                return null;
            } else {
                return dictionary.get(positionTerm).getPostingList();
            }
        }
    }

    public void makeDictionary() {
        // cek deteksi ada term yang frekuensinya lebih dari 
        // 1 pada sebuah dokumen
        // buat posting list term terurut
        ArrayList<Posting> list = getSortedPostingList();
        // looping buat list of term (dictionary)
        for (int i = 0; i < list.size(); i++) {
            // cek dictionary kosong?
            if (getDictionary().isEmpty()) {
                // buat term
                Term term = new Term(list.get(i).getTerm());
                // tambah posting ke posting list utk term ini
                term.getPostingList().add(list.get(i));
                // tambah ke dictionary
                getDictionary().add(term);
            } else {
                // dictionary sudah ada isinya
                Term tempTerm = new Term(list.get(i).getTerm());
                // pembandingan apakah term sudah ada atau belum
                // luaran dari binarysearch adalah posisi
                int position = Collections.binarySearch(getDictionary(), tempTerm);
                if (position < 0) {
                    // term baru
                    // tambah postinglist ke term
                    tempTerm.getPostingList().add(list.get(i));
                    // tambahkan term ke dictionary
                    getDictionary().add(tempTerm);
                } else {
                    // term ada
                    // tambahkan postinglist saja dari existing term
                    getDictionary().get(position).
                            getPostingList().add(list.get(i));
                    // urutkan posting list
                    Collections.sort(getDictionary().get(position)
                            .getPostingList());
                }
                // urutkan term dictionary
                Collections.sort(getDictionary());
            }

        }

    }

    public void makeDictionaryWithTermNumber() {
        // cek deteksi ada term yang frekuensinya lebih dari 
        // 1 pada sebuah dokumen
        // buat posting list term terurut
        ArrayList<Posting> list = getSortedPostingListWithTermNumber();
        // looping buat list of term (dictionary)
        for (int i = 0; i < list.size(); i++) {
            // cek dictionary kosong?
            if (getDictionary().isEmpty()) {
                // buat term
                Term term = new Term(list.get(i).getTerm());
                // tambah posting ke posting list utk term ini
                term.getPostingList().add(list.get(i));
                // tambah ke dictionary
                getDictionary().add(term);
            } else {
                // dictionary sudah ada isinya
                Term tempTerm = new Term(list.get(i).getTerm());
                // pembandingan apakah term sudah ada atau belum
                // luaran dari binarysearch adalah posisi
                int position = Collections.binarySearch(getDictionary(), tempTerm);
                if (position < 0) {
                    // term baru
                    // tambah postinglist ke term
                    tempTerm.getPostingList().add(list.get(i));
                    // tambahkan term ke dictionary
                    getDictionary().add(tempTerm);
                } else {
                    // term ada
                    // tambahkan postinglist saja dari existing term
                    getDictionary().get(position).
                            getPostingList().add(list.get(i));
                    // urutkan posting list
                    Collections.sort(getDictionary().get(position)
                            .getPostingList());
                }
                // urutkan term dictionary
                Collections.sort(getDictionary());
            }
        }

    }

    /**
     * @return the listOfDocument
     */
    public ArrayList<Document> getListOfDocument() {
        return listOfDocument;
    }

    /**
     * @param listOfDocument the listOfDocument to set
     */
    public void setListOfDocument(ArrayList<Document> listOfDocument) {
        this.listOfDocument = listOfDocument;
    }

    /**
     * @return the dictionary
     */
    public ArrayList<Term> getDictionary() {
        return dictionary;
    }

    /**
     * @param dictionary the dictionary to set
     */
    public void setDictionary(ArrayList<Term> dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * Fungsi mencari frequensi sebuah term dalam sebuah index
     *
     * @param term
     * @return
     */
    public int getDocumentFrequency(String term) {
        Term tempTerm = new Term(term);
        // cek apakah term ada di dictionary
        int index = Collections.binarySearch(dictionary, tempTerm);
        if (index > 0) {
            // term ada
            // ambil ArrayList<Posting> dari object term
            ArrayList<Posting> tempPosting = dictionary.get(index)
                    .getPostingList();
            // return ukuran posting list
            return tempPosting.size();
        } else {
            // term tidak ada
            return -1;
        }
    }

    /**
     * Fungsi untuk mencari inverse term dari sebuah index
     *
     * @param term
     * @return
     */
    public double getInverseDocumentFrequency(String term) {
        Term tempTerm = new Term(term);
        // cek apakah term ada di dictionary
        int index = Collections.binarySearch(dictionary, tempTerm);
        if (index > 0) {
            // term ada
            // jumlah total dokumen
            int N = listOfDocument.size();
            // jumlah dokumen dengan term i
            int ni = getDocumentFrequency(term);
            // idf = log10(N/ni)
            double Nni = (double) N / ni;
            return Math.log10(Nni);
        } else {
            // term tidak ada
            // nilai idf = 0
            return 0.0;
        }
    }

    /**
     * Fungsi untuk mencari term frequency
     *
     * @param term
     * @param idDocument
     * @return
     */
    public int getTermFrequency(String term, int idDocument) {
        Document document = new Document();
        document.setId(idDocument);
        int pos = Collections.binarySearch(listOfDocument, document);
        if (pos >= 0) {
            ArrayList<Posting> tempPosting = listOfDocument.get(pos).getListofPosting();
            Posting posting = new Posting();
            posting.setTerm(term);
            int postingIndex = Collections.binarySearch(tempPosting, posting);
            if (postingIndex >= 0) {
                return tempPosting.get(postingIndex).getNumberOfTerm();
            }
            return 0;
        }

        return 0;
    }

    /**
     * Fungsi untuk menghitung TF-IDF dari sebuah dokumen
     *
     * @param idDocument
     */
    public ArrayList<Posting> makeTFIDF(int idDocument) {
        // buat posting list hasil
        ArrayList<Posting> result = new ArrayList<Posting>();
        // buat document temporary, sesuai passing parameter
        Document temp = new Document(idDocument);
        // cek document temp, ada di dalam list document?
        int cari = Collections.binarySearch(listOfDocument, temp);
        // jika ada, variable cari akan berisi indeks. nilai lebih dari 0
        if (cari >= 0) {
            // dokumen ada
            // baca dokumen sesuai indek di list dokumen
            temp = listOfDocument.get(cari);
            // buat posting list dengan bobot masih 0
            result = temp.getListofPosting();
            // isi bobot dari posting list
            for (int i = 0; i < result.size(); i++) {
                // ambil term
                String tempTerm = result.get(i).getTerm();
                // cari idf
                double idf = getInverseDocumentFrequency(tempTerm);
                // cari tf
                int tf = result.get(i).getNumberOfTerm();
                // hitung bobot
                double bobot = tf * idf;
                // set bobot pada posting
                result.get(i).setWeight(bobot);
            }
            Collections.sort(result);
        } else {
            // dokumen tidak ada
        }
        return result;
    }

    /**
     * Fungsi perkalian inner product dari PostingList Atribut yang dikalikan
     * adalah atribut weight TFIDF dari posting
     * Ini dikenal dengan istilah penghitungan jarak Euclidean
     * @param p1
     * @param p2
     * @return
     */
    public double getInnerProduct(ArrayList<Posting> p1,
            ArrayList<Posting> p2) {
        // urutkan posting list
        Collections.sort(p2);
        Collections.sort(p1);
        // buat temp hasil
        double result = 0.0;
        // looping dari posting list p1
        for (int i = 0; i < p1.size(); i++) {
            // ambil temp
            Posting temp = p1.get(i);
            // cari posting di p2
            boolean found = false;
            for (int j = 0; j < p2.size() && found == false; j++) {
                Posting temp1 = p2.get(j);
                if (temp1.getTerm().equalsIgnoreCase(temp.getTerm())) {
                    // term sama
                    found = true;
                    // kalikan bobot untuk term yang sama
                    result = result + temp1.getWeight() * temp.getWeight();
                }
            }
        }
        return result;
    }

    /**
     * Fungsi untuk membentuk posting list dari sebuah query
     *
     * @param query
     * @return
     */
    public ArrayList<Posting> getQueryPosting(String query) {
        // buat dokumen
        Document temp = new Document(-1, query);
        // buat posting list
        ArrayList<Posting> result = temp.getListofPosting();
        // hitung bobot
        // isi bobot dari posting list
        for (int i = 0; i < result.size(); i++) {
            // ambil term
            String tempTerm = result.get(i).getTerm();
            // cari idf
            double idf = getInverseDocumentFrequency(tempTerm);
            // cari tf
            int tf = result.get(i).getNumberOfTerm();
            // hitung bobot
            double bobot = tf * idf;
            // set bobot pada posting
            result.get(i).setWeight(bobot);
        }
        Collections.sort(result);
        return result;
    }
    
    /**
     * Fungsi untuk menghitung panjang dari sebuah posting
     * Asumsi posting memiliki komponen bobot/weight
     * @param posting
     * @return 
     */
    public double getLengthOfPosting(ArrayList<Posting> posting){
        double result=0.0;
        for (int i = 0; i < posting.size(); i++) {
            // ambil obyek posting
            Posting post = posting.get(i);
            // ambil bobot/weight
            double weight = post.getWeight();
            // kuadrat bobot
            weight = weight*weight;
            // jumlahkan ke result
            result = result + weight;
        }
        // keluarkan akar kuadrat
        return Math.sqrt(result);
    }
    
    /**
     * Fungsi untuk menghitung cosine similarity
     * @param posting
     * @param posting1
     * @return 
     */
    public double getCosineSimilarity(ArrayList<Posting> posting,
            ArrayList<Posting> posting1){
        // cari jarak antara posting dan posting 1
        double hasilDotProduct = getInnerProduct(posting, posting1);
        // cari panjang posting
        double panjang_posting = getLengthOfPosting(posting);
        // cari panjang posting1
        double panjang_posting1 = getLengthOfPosting(posting1);
        // hitung cosine similarity
        double result = 
                hasilDotProduct / Math.sqrt(panjang_posting*panjang_posting1);
        return result;
    }
    
    /**
     * Fungsi untuk mencari berdasar nilai TFIDF
     * @param query
     * @return 
     */
    public ArrayList<SearchingResult> searchTFIDF(String query){
        // buat list search document
        ArrayList<SearchingResult> result = new ArrayList<SearchingResult>(); 
        // ubah query menjadi array list posting
        ArrayList<Posting> queryPostingList = getQueryPosting(query);
        // buat posting list untuk seluruh dokumen
        for (int i = 0; i < listOfDocument.size(); i++) {
            // ambil obyek dokumen
            Document doc = listOfDocument.get(i);
            int idDoc = doc.getId();
            // buat posting list untuk dokumen
            ArrayList<Posting> tempDocWeight = makeTFIDF(idDoc);
            // hitung jarak antar posting list dokumen dengan posting list query
            double hasilDotProduct = getInnerProduct(tempDocWeight,queryPostingList);
            // isi result list
            if (hasilDotProduct > 0){
                // buat obyek document hasil cari
                SearchingResult resultDoc = new SearchingResult(hasilDotProduct, doc);
                // tambahkan ke list hasil cari
                result.add(resultDoc);
            }
        }
        // urutkan hasil cari
        Collections.sort(result);
        return result;
    }
    
    /**
     * Fungsi untuk mencari dokumen berdasarkan cosine similarity
     * @param query
     * @return 
     */
    public ArrayList<SearchingResult> searchCosineSimilarity(String query){
        // buat list search document
        ArrayList<SearchingResult> result = new ArrayList<SearchingResult>(); 
        // ubah query menjadi array list posting
        ArrayList<Posting> queryPostingList = getQueryPosting(query);
        // buat posting list untuk seluruh dokumen
        for (int i = 0; i < listOfDocument.size(); i++) {
            // ambil obyek dokumen
            Document doc = listOfDocument.get(i);
            int idDoc = doc.getId();
            // buat posting list untuk dokumen
            ArrayList<Posting> tempDocWeight = makeTFIDF(idDoc);
            // hitung cosin similarity antar posting list dokumen dengan posting list query
            double cosineSimilarity = getCosineSimilarity(tempDocWeight,queryPostingList);
            // isi result list
            if (cosineSimilarity > 0){
                // buat obyek document hasil cari
                SearchingResult resultDoc = new SearchingResult(cosineSimilarity, doc);
                // tambahkan ke list hasil cari
                result.add(resultDoc);
            }
        }
        // urutkan hasil cari
        Collections.sort(result);
        return result;
    }
    
    /**
     * Fungsi untuk membuat list dokumen dari sebuah directory
     * asumsikan isi file cukup disimpan dalam sebuah obyek String
     * @param directory 
     */
    public void readDirectory(File directory){
        
    }
    
}
