package com.example.scrollabletabs1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlueFragment extends Fragment {
	final static String exampleText = "Sample text for blue screen.";

	public BlueFragment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater
				.inflate(R.layout.blue_screen, container, false);
		TextView blueTxt = (TextView) rootView.findViewById(R.id.blueTxt);
		blueTxt.setTextColor(Color.WHITE);
		blueTxt.setText(exampleText);
		return rootView;
	}

}
