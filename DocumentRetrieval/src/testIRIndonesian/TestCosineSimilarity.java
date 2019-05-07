/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testIRIndonesian;

import java.util.ArrayList;
import model.Document;
import model.InvertedIndex;
import model.Posting;

/**
 *
 * @author admin
 */
public class TestCosineSimilarity {

    public static void main(String[] args) {
        // seting dokumen
        Document doc1 = new Document(1, "Fahri Hamzah Usul Ibu Kota Pindah ke Kepulauan Seribu");
        Document doc2 = new Document(2, "Gaya Nyentrik Menteri Susi Saat Pimpin Penenggelaman 13 Kapal Vietnam di Kalbar");
        Document doc3 = new Document(3, "TNI AL: KRI Tjiptadi-381 Diprovokasi Kapal Pengawas Ikan Vietnam ");

        // buat object invertedIndex
        InvertedIndex index = new InvertedIndex();
        // lakukan stemming untuk semua dokumen
        doc1.IndonesiaStemming();
        doc2.IndonesiaStemming();
        doc3.IndonesiaStemming();
        // tmbahkan document ke index
        index.addNewDocument(doc1);
        index.addNewDocument(doc2);
        index.addNewDocument(doc3);
        // bikin dictionary
        index.makeDictionaryWithTermNumber();
        
        int idDokumen=1;
        System.out.println("IdDokumen "+idDokumen);
        ArrayList<Posting> listDoc1 = index.makeTFIDF(idDokumen);
        for(Posting temp:listDoc1){
            System.out.println(temp);
        }
        
        idDokumen=2;
        System.out.println("IdDokumen "+idDokumen);
        ArrayList<Posting> listDoc2 = index.makeTFIDF(idDokumen);
        for(Posting temp:listDoc2){
            System.out.println(temp);
        }
    }
}
