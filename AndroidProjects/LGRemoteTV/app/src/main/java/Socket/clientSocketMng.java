package Socket;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientSocketMng extends AsyncTask<String, Void, String> {
    final static String send = "send", init = "Start", stop = "Stop";
    private static final int serverPort = 1234;
    private static String serverIP = "";
    String result = "+";
    String readLine;
    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;
    private Context activityContext;

    public clientSocketMng() {

    }

    public static int getServerport() {
        return serverPort;
    }

    public static String getServerip() {
        return serverIP;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

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

    public Context getActivityContext() {
        return activityContext;
    }

    public void setActivityContext(Context activityContext) {
        this.activityContext = activityContext;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReadLine() {
        return readLine;
    }

    public void setReadLine(String readLine) {
        this.readLine = readLine;
    }

    public String closeConnection() {
        result = "+";
        try {
            if (getSocket() != null && getSocket().isConnected()) {
                sendData("Cerrando conexión con el servidor");
                getSocket().close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            result = e.getMessage();
        }
        return result;
    }

    public String initClientSocket(String ipService) {
        result = "+";
        String preparedIP = prepareIP(ipService);
        lookforlIP(preparedIP);


        return result;

    }

    public void tryingSocketConnection(final String preparedIP, final int i) {

      new Thread() {
            @Override
            public void run() {
                super.run();
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getByName(preparedIP + i);
                    if (getSocket() == null) {
                        setSocket(new Socket(inetAddress, getServerport()));
                        result = "";
                    }
                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result = "Unknown host. Try to find the host.";
                    Log.d("info", "trying to connect to: " + preparedIP + i);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Log.d("info", "trying to connect to: " + preparedIP + i);
                    result = e.getMessage();


                } catch (Exception e) {
                    e.printStackTrace();
                    result = e.getMessage();
                    Log.d("info", "trying to connect to: " + preparedIP + i);
                }
            }
        }.start();
    }


    public void lookforlIP(String preparedIP) {


        for (int i = 176; i < 177; i++) {
            if (getSocket() == null) {
                tryingSocketConnection(preparedIP, i);
              /*  try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            } else {
                break;
            }
        }
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (getSocket() == null) {
            setReadLine("Connection failed");
        } else if (getSocket().isConnected()) {
            setReadLine("Connection OK");
            result = "";
        }


    }

    public void sendData(String text) {
        result = "";
        if (getOut() != null && !getOut().checkError()) {
            getOut().println(text);
            getOut().flush();
        }
    }

    public String sendDatainit() {
        result = "";
        try {
            if (getSocket() != null) {
                setOut(new PrintWriter(getSocket().getOutputStream(), true));
                setIn(new BufferedReader(new InputStreamReader(getSocket()
                        .getInputStream())));

                sendData("Envio datos al servidor");
                //setReadLine(getIn().readLine());
                System.out.println("Recibo: " + getReadLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            result = e.getMessage();

        }
        return result;

    }


    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        // in first place we put the type of action we have to do.
        // in second place we put the text we have to send. If there are no text

        // we put " ".
        String toDo = params[0];
        String ip = params[1];
        if (toDo.equals(init)) {
            initClientSocket(ip);
            if (getSocket() !=null && getSocket().isConnected()) {
                sendDatainit();
            }
            try {
                while (getIn().readLine() != null) {
                    readLine = getIn().readLine();

                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public void SendDataToNetwork(String cmd) { // You run this from the main
        // thread.
        try {
            if (getSocket().isConnected()) {
                Log.i("AsyncTask",
                        "SendDataToNetwork: Writing received message to socket");
                getOut().println(cmd);
            } else {
                Log.i("AsyncTask",
                        "SendDataToNetwork: Cannot send message. Socket is closed");
            }
        } catch (Exception e) {
            Log.i("AsyncTask",
                    "SendDataToNetwork: Message send failed. Caught an exception");
        }
    }

    /**
     * return a number ip without the last number after the third point
     *
     * @param: autoip generated with method getIP
     */
    private String prepareIP(String autoIP) {
        int numberPoints = 0;
        String ip = "";
        for (int i = 0; i < autoIP.length(); i++) {
            char c = autoIP.charAt(i);
            ip = ip + c;
            if (c == '.')
                numberPoints++;
            if (numberPoints == 3) {
                i = autoIP.length();
            }
        }
        return ip;
    }

}
