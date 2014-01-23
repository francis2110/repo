package com.example.androidclientsocketsample;

import java.lang.Thread.UncaughtExceptionHandler;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.CursorJoiner.Result;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class MainActivity extends Activity {

	private Button sendBtn, stopInitBtn;
	private ClientSocket client;
	private Context activityContext;
	private static final String init = "Start";
	private static final String stop = "Stop", send = "send";
	private Socket socket = null;
	private clientSocketMng clientMng=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		activityContext = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sendBtn = (Button) findViewById(R.id.sendBtn);
		final EditText messageTxt = (EditText) findViewById(R.id.messageTxt);
		/* client = new ClientSocket(this); */
		// clientMng = new clientSocketMng(socket);
		stopInitBtn = (Button) findViewById(R.id.initEndBtn);
		// new Thread(client).start();
		//Toast.makeText(this, "Connected to server by wifi", Toast.LENGTH_LONG).show();

		sendBtn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// client.sendData(messageTxt.getText().toString());
				//String[] p = { send, messageTxt.getText().toString() };
				//si el socket==null hay que mostrar información que primero hemos 
				//de dar al boton start.
				/*clientMng = new clientSocketMng();
				clientMng.execute(p);
				if (clientMng.getResult().equals("")) {
					messageTxt.setText("");
				}*/
				if(clientMng!=null){
					clientMng.SendDataToNetwork(messageTxt.getText().toString());
					messageTxt.setText("");
				}
				
			}
		});
		stopInitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (stopInitBtn.getText().toString().equals(stop)) {
					// client.closeConnection();
					String[] p = { stop, "" };
					/*clientMng.execute(p);
					if (clientMng.getResult().equals("")) {
						stopInitBtn.setText(init);
											}*/
					clientMng.closeConnection();
					clientMng=null;
					stopInitBtn.setText(init);
				} else if (stopInitBtn.getText().toString().equals(init)) {
					/*
					 * if(socketThread==null){ socketThread = new
					 * Thread(client); socketThread.start();}else{
					 * socketThread.run(); }
					 */
					String[] p = { init, "" };
					if(clientMng==null){
					clientMng = new clientSocketMng();}
					clientMng.execute(p);
					//socket=clientMng.getSocket();
					if (clientMng.getResult().equals("")) {
						stopInitBtn.setText(stop);
					}
					//socket=clientMng.getSocket();
					/*
					 * boolean ok = client.initClientSocket(); if (ok) { ok =
					 * client.sendDatainit(); if (!ok) {
					 * Toast.makeText(activityContext,
					 * "Unable send data to the server", Toast.LENGTH_LONG); } }
					 * else { Toast.makeText(activityContext,
					 * "Unable to connect with server by wifi.",
					 * Toast.LENGTH_LONG); }
					 */
				}
		
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
