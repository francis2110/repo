/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raspberrymanageleds;

import gnu.io.NoSuchPortException;

/**
 *
 * @author fran
 */
public class LedsMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       System.out.println("creating a socket server with one thread...");
//        try {
            serverSocketMonoThread sSocket = new serverSocketMonoThread();
            System.out.println("Instance of class serverSocketMonothread created...");
            sSocket.initSocket();
            sSocket.listentoConnection();
//        } catch (NoClassDefFoundError e) {
//            System.err.println("Failed creating new instance of socket.");
//            System.out.println(e.getMessage());
//            System.out.println(e.getCause());
//           
//        }

    }

}
