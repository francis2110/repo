/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbmanageleds;

/**
 *
 * @author fran
 */
public class RBManageLeds {

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
    }
    
}
