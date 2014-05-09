package com.example.scrollabletabs1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RedFragment extends Fragment {
static final String sampleText="Sample text for red background";

	public RedFragment() {
	super();
	// TODO Auto-generated constructor stub
}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View rootView=inflater.inflate(R.layout.red_screen, container, false);
		TextView text=(TextView) rootView.findViewById(R.id.redTextExample);
		text.setText(sampleText);
		return rootView;
		
	}

}
