package com.example.ledsonoffwifi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button blueLedBttn, redLedBttn, startStopBttn;
	private clientSocketMng clientMng = null;
	// 0 blue off, 1 blue on, 2 red off, 3 red on
	private static final String blueOFF = "0", blueON = "1", redOFF = "2",
			redON = "3";
	private static final String stop = "Stop", init = "Start";
	private static final String blueLedOff = "Blue Led OFF",
			blueLedON = "Blue Led ON", redLedOff = "Red Led OFF",
			redLedON = "Red Led ON";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startStopBttn = (Button) findViewById(R.id.initStop);
		blueLedBttn = (Button) findViewById(R.id.blueLedBttn);
		setRedLedBttn((Button) findViewById(R.id.redLedBttn));
		addListeners();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		resizeBttn(blueLedBttn, false);
		resizeBttn(getRedLedBttn(), false);

	}

	public Button getBttn() {
		return blueLedBttn;
	}

	public void setBttn(Button bttn) {
		this.blueLedBttn = bttn;
	}

	public Button getStartStopBttn() {
		return startStopBttn;
	}

	public void setStartStopBttn(Button startStopBttn) {
		this.startStopBttn = startStopBttn;
	}

	public Button getRedLedBttn() {
		return redLedBttn;
	}

	public void setRedLedBttn(Button redLedBttn) {
		this.redLedBttn = redLedBttn;
	}

	private void resizeBttn(Button bttn, boolean on) {
		Drawable dr;
		if (on) {
			dr = getResources().getDrawable(R.drawable.ledon);
		} else {
			dr = getResources().getDrawable(R.drawable.ledoff);
		}
		Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
		int weight = bttn.getWidth() / 3;
		int height = (int) (bttn.getHeight() * .9);
		Bitmap resized = Bitmap
				.createScaledBitmap(bitmap, weight, height, true);

		try {
			Drawable d = new BitmapDrawable(getResources(), resized);

			bttn.setCompoundDrawablesWithIntrinsicBounds(d, null, null, null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void addListeners() {
		getStartStopBttn().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getStartStopBttn().getText().equals(init)) {
					if (clientMng == null) {
						clientMng = new clientSocketMng();
						String[] p = { init, getIP() };
						clientMng.execute(p);
						boolean trying=true;
						while (trying) {
							if (clientMng.getReadLine()!=null &&clientMng.getReadLine().equals("Connection OK")) {
								getStartStopBttn().setText(stop);
								Toast toast = Toast.makeText(
										getApplicationContext(),
										"Connection OK", Toast.LENGTH_LONG);
								toast.show();
								trying=false;
							} else if (clientMng.getReadLine()!=null &&clientMng.getReadLine().equals(
									"Connection failed")) {
								Toast toast = Toast
										.makeText(getApplicationContext(),
												"Connection failed",
												Toast.LENGTH_SHORT);
								toast.show();
								trying=false;
							}
						}
						/*
						 * if (clientMng.getResult().equals("")) {
						 * getStartStopBttn().setText(stop); }
						 */
					}
				} else if (getStartStopBttn().getText().equals(stop)) {
					// String[] p = { stop, "" };
					clientMng.closeConnection();
					clientMng = null;
					getStartStopBttn().setText(init);
				}

			}
		});
		getBttn().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getBttn().getText().equals(blueLedOff)) {
					clientMng.SendDataToNetwork(blueON);
					getBttn().setText(blueLedON);
					resizeBttn(getBttn(), true);
				} else if (getBttn().getText().equals(blueLedON)) {
					clientMng.SendDataToNetwork(blueOFF);
					getBttn().setText(blueLedOff);
					resizeBttn(getBttn(), false);
				}
			}
		});
		getRedLedBttn().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (getRedLedBttn().getText().equals(redLedOff)) {
					clientMng.SendDataToNetwork(redON);
					getRedLedBttn().setText(redLedON);
					resizeBttn(getRedLedBttn(), true);
				} else if (getRedLedBttn().getText().equals(redLedON)) {
					clientMng.SendDataToNetwork(redOFF);
					getRedLedBttn().setText(redLedOff);
					resizeBttn(getRedLedBttn(), false);
				}
			}
		});

	}

	/**
	 * Get the IP of current Wi-Fi connection
	 * 
	 * @return IP as string
	 */
	private String getIP() {
		try {
			WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();

			return String.format("%d.%d.%d.%d", (ipAddress & 0xff),
					(ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
					(ipAddress >> 24 & 0x00));
		} catch (Exception ex) {

			Log.e("clientSocketMng", ex.getMessage());
			return null;
		}
	}
}
