/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocketsample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fran
 */
public class serverSocketMonoThread {

    ServerSocket server;
    Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private int serverPort = 1234;

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void initSocket() {
        try {
            server = new ServerSocket(getServerPort());
        } catch (IOException ex) {
            System.out.println("Can not listen to port 1234");
            closeserverSocketElements();
            System.exit(-1);
        }
    }

    public void listentoConnection() {
        try {
            client = server.accept();
            System.out.println("connection received");
        } catch (IOException ex) {
            System.out.println("Accept failed : 1234");
            closeserverSocketElements();
            System.exit(-1);
        }
        try {
            setIn(new BufferedReader(new InputStreamReader(client.getInputStream())));
            setOut(new PrintWriter(client.getOutputStream(), true));
        } catch (IOException ex) {
            System.out.println("Read failed");
            closeserverSocketElements();
            System.exit(-1);
        }
        getOut().println("Waiting for your requests.");
        getOut().flush();
        int i = 0;
        while (true) {
            try {
                String readLine = in.readLine();
                //send data client:
                //getOut().println("Datos recividos" + i++);
                if (readLine != null) {
                    getOut().flush();
                    System.out.println(readLine + " " + i);

                    if (readLine.equals("Stop")) {
                        closeserverSocketElements();
                        System.exit(-1);
                    }
                } else {
                    System.out.println("Client disconected");
                    break;
                }
            } catch (IOException ex) {
                System.out.println("Read failed");
                closeserverSocketElements();
                System.exit(-1);
            }
        }
    }

    private void closeserverSocketElements() {
        try {
//            getIn().close();
//            getOut().close();
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(serverSocketMonoThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
