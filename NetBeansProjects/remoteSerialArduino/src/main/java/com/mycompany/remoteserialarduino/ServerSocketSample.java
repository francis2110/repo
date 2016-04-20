/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remoteserialarduino;

/**
 *
 * @author fran
 */
public class ServerSocketSample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        serverSocketMonoThread sSocket = new serverSocketMonoThread();
        sSocket.initSocket();
        sSocket.listentoConnection();
    }
}
