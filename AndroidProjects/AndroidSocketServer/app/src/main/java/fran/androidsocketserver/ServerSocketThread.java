package fran.androidsocketserver;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by fran on 28/01/15.
 */
public class ServerSocketThread extends Thread {
    private static final int port=1234;
    private ServerSocket server;
    private BufferedReader input;
    private Socket client;
    private PrintWriter out;
    private Handler handler;



    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public static int getPort() {
        return port;
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

       public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    @Override
    public void run() {
        super.run();
        try {
            setServer(new ServerSocket(getPort()));
            setClient(getServer().accept());
            setOut(new PrintWriter(getClient().getOutputStream(),true));
            getOut().write("connection received");
            setInput(new BufferedReader(new InputStreamReader(getClient().getInputStream())));

            while(true){
                String readLine=getInput().readLine();
                if(readLine!=null){
                    //update info in TextView
                    Message message = getHandler().obtainMessage();
                    message.what=1;
                    message.obj=readLine;
                    getHandler().sendMessage(message);
                                       if (readLine.equals("Stop")) {
                        closeSocketServer();
                        System.exit(-1);
                    }
                }else{
                    System.out.println("Client disconected");
                    System.exit(-1);
                    break;
                }
            System.out.println("readline: "+readLine);
            }
        } catch (IOException e) {
            System.out.println("Accept failed : 1234");
        }



    }
    public void closeSocketServer() {
        try {
            getInput().close();
            getOut().close();

            if (server != null) {
                server.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerSocketThread.class.getName()).log(Level.SEVERE, null, ex);
        }

}

}
