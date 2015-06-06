package com.example.fran.lgremotetv;

import android.view.View;
import android.widget.GridLayout;

/**
 * Created by fran on 21/12/14.
 */
public class utils {
    public void addListeners(GridLayout gL, View.OnClickListener oCListener) {
        for (int i = 0; i < gL.getChildCount(); i++) {
            View v = gL.getChildAt(i);
            v.setOnClickListener(oCListener);
        }
    }

}
