/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sha256coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fran
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String password = "";
        System.out.println("If you want to exit the program you must write -1.");
        while (!"-1".equals(password)) {
            try {
                // TODO code application logic here
                MessageDigest digest = MessageDigest.getInstance("SHA-256");

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Write the text you want to code using SHA-256 ");
                password = br.readLine();
                System.out.println("User writes " + password);

                byte[] hash = digest.digest(password.getBytes("UTF-8"));
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < hash.length; i++) {
                    sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
                }
                System.out.println("Codified text: " + sb.toString());

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
