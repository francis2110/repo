package com.example.androidclientsocketsample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class clientSocketMng extends AsyncTask<String, Void, String> {
	private Socket socket = null;
	private static final int serverPort = 1234;
	private static final String serverIP = "192.168.1.128";
	private BufferedReader in;
	private PrintWriter out;
	private Context activityContext;
	final static String send = "send", start = "Start", stop = "Stop";
	String result = "";

	public clientSocketMng() {
			
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

	public static int getServerport() {
		return serverPort;
	}

	public static String getServerip() {
		return serverIP;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String closeConnection() {
		result = "";
		try {
			if (getSocket()!=null && getSocket().isConnected()) {
				sendData("Cerrando conexión con el servidor");
				/*getIn().close();
				getOut().close();*/
				getSocket().close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = e.getMessage();
		}
		return result;
	}

	public String initClientSocket() {
		result = "";
		try {
			InetAddress inetAddress = InetAddress.getByName(getServerip());
			if (getSocket() == null) {
				setSocket(new Socket(inetAddress, getServerport()));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "Unknown host. Try to find the host.";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();

		}
		return result;

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
				// System.out.println("Socket connected by wifi");
				setOut(new PrintWriter(getSocket().getOutputStream(), true));
				setIn(new BufferedReader(new InputStreamReader(getSocket()
						.getInputStream())));

				/*int i = 0;
				while (getIn().readLine() != null) {*/
					sendData("Envio datos al servidor");
					System.out.println("Recibo:" + getIn().readLine());
					/*break;*/
				/*}*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			result = e.getMessage();

		}
		return result;

	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		// in first place we put the type of action we have to do.
		// in second place we put the text we hace to send. If there are no text
		// we put " ".
		String toDo = params[0];
		String message = params[1];
		if (toDo.equals(start)) {
			initClientSocket();
			if (getResult().equals("")) {
				sendDatainit();
			}
			try {String readLine;
				while(getIn().readLine()!=null){
					readLine=getIn().readLine();
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}/*finally{
				try {
					getIn().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				getOut().close();
				try {
					getSocket().close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		} /*
		 * else if (toDo.equals(stop)) { closeConnection();
		 * 
		 * }
		 *//*else if (toDo.equals(send)) {
			initClientSocket();
			sendDatainit();
			sendData(message);
			closeConnection();
		}*/

		return null;
	}
	/*
	 * @Override protected void onPostExecute(String result) { // TODO
	 * Auto-generated method stub super.onPostExecute(result); if
	 * (getSocket().isConnected()) { try { getSocket().close(); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } } }
	 */
	public void SendDataToNetwork(String cmd) { //You run this from the main thread.
        try {
            if (getSocket().isConnected()) {
                Log.i("AsyncTask", "SendDataToNetwork: Writing received message to socket");
               getOut().println(cmd);
            } else {
                Log.i("AsyncTask", "SendDataToNetwork: Cannot send message. Socket is closed");
            }
        } catch (Exception e) {
            Log.i("AsyncTask", "SendDataToNetwork: Message send failed. Caught an exception");
        }
    }
	
}
