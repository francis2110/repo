/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.remoteserialarduino;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
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
    private static final String blueOFF = "0", blueON = "1", redOFF = "2", redON = "3";
    private SerialCom serialCom;
    private boolean initOK;

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
            serialCom = new SerialCom();
            serialCom.initialize();
            serialCom.setPortId(CommPortIdentifier.getPortIdentifier("/dev/ttyACM0"));
            initOK = serialCom.initPortCommunication();
            if (initOK) {
                System.out.println("Connection with arduino OK.");
            } else if (!initOK) {
                System.out.println("Problems connection with arduino.");
            }
        } catch (IOException ex) {
            System.out.println("Can not listen to port 1234");
            closeserverSocketElements();
            System.exit(-1);
        } catch (NoSuchPortException ex) {
            Logger.getLogger(serverSocketMonoThread.class.getName()).log(Level.SEVERE, null, ex);
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
        getOut().println("Connection OK");
        getOut().flush();
        int i = 0;
        while (true) {
            try {
                String readLine = in.readLine();
                //send data client:
                //getOut().println("Datos recividos" + i++);
                if (readLine != null) {
                    try {
                        serialCom.getOutput().write(Integer.parseInt(readLine));
                        getOut().flush();
                    } catch (NumberFormatException e) {
                        System.out.println("Texto enviado");
                        if (readLine.equals("Stop")) {
                            closeserverSocketElements();
                            System.exit(-1);
                        }
                    }

                    System.out.println(readLine + " ");

                } else {
                    serialCom.close();
                                            System.out.println("Client disconected");
                    System.exit(-1);
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
            getIn().close();
            getOut().close();
            if (server != null) {
                server.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(serverSocketMonoThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
