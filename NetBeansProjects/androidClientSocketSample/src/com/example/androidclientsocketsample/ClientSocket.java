package com.example.androidclientsocketsample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClientSocket implements Runnable {
	private Socket socket = null;
	private static final int serverPort = 1234;
	private static final String serverIP = "192.168.1.128";
	private BufferedReader in;
	private PrintWriter out;
	private Context activityContext;

	public ClientSocket(Context activityContext) {
		super();
		this.activityContext = activityContext;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public static int getServerport() {
		return serverPort;
	}

	public static String getServerip() {
		return serverIP;
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

	public void closeConnection() {

		try {
			//sendData("Stop");

//			getIn().close();
//			getOut().close();
			
			 getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// setSocket(null);
	}

	public boolean initClientSocket() {
		try {
			InetAddress inetAddress = InetAddress.getByName(getServerip());
			if (getSocket() == null) {
				setSocket(new Socket(inetAddress, getServerport()));
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void sendData(String text) {
		if (getOut() != null && !getOut().checkError()) {
			getOut().println(text);
			getOut().flush();
		}
	}

	public boolean sendDatainit() {
		try {
			if (getSocket() != null) {
				// System.out.println("Socket connected by wifi");
				setOut(new PrintWriter(getSocket().getOutputStream(), true));
				setIn(new BufferedReader(new InputStreamReader(getSocket()
						.getInputStream())));

				int i = 0;
				while (getIn().readLine() != null) {
					sendData("Envio datos al servidor" + i++);
					System.out.println("Recibo:" + getIn().readLine());
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		boolean ok = initClientSocket();
		if (ok) {
			ok = sendDatainit();
			if (!ok) {
				Toast.makeText(activityContext,
						"Unable send data to the server", Toast.LENGTH_LONG);
			} else {
				Toast.makeText(activityContext,
						"Unable to connect with server by wifi.",
						Toast.LENGTH_LONG);
			}
		}

	}

}
