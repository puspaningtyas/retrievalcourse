/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author user only
 */
public class testRegex {
    public static void main(String[] args) {
        String test = "ini sebuah, kalimat..?";
        test = test.replaceAll("[,?.]", "");
        System.out.println(test);
    }
}
