package com.example.fran.lgremotetv;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import Socket.clientSocketMng;

/**
 * Created by fran on 24/01/15.
 */
public class mainCommands extends Fragment {
    private GridLayout gL; View.OnClickListener onClickListener = null;

    clientSocketMng clienMng = null;
    private static final String stop = "Stop", init = "Start";
    private static final String onOff = "10", energy = "11", avMode = "12", input = "13", tvRad = "14", one = "1", two = "2", three = "3", four = "4", five = "5", six = "6", seven = "7", eight = "8", nine = "9", zero = "0",
            list = "15", quickView = "16", volUP = "17", volDown = "18", progUp = "19", progDown = "20", fav = "23", ratio = "24", mute = "25";
    utils utils=new utils();
    public GridLayout getgL() {
        return gL;
    }

    public void setgL(GridLayout gL) {
        this.gL = gL;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public clientSocketMng getClienMng() {
        return clienMng;
    }

    public void setClienMng(clientSocketMng clienMng) {
        this.clienMng = clienMng;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setgL((GridLayout) inflater.inflate(R.layout.fragment_remote_tv, container, false));
        initOnclickListener();
        utils.addListeners(getgL(),getOnClickListener());
        return getgL();

    }

    private  void initOnclickListener() {
        if (onClickListener == null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    String dataSended = "";
                    switch (id) {
                        case R.id.powerBttn:
                            dataSended = onOff;
                            break;
                        case R.id.energySaveBttn:
                            dataSended = energy;
                            break;
                        case R.id.avModeBttn:
                            dataSended = avMode;
                            break;
                        case R.id.inputBttn:
                            dataSended = input;
                            break;
                        case R.id.tvRadBttn:
                            dataSended = tvRad;
                            break;
                        case R.id.Bttn1:
                            dataSended = one;
                            break;
                        case R.id.Bttn2:
                            dataSended = two;
                            break;
                        case R.id.Bttn3:
                            dataSended = three;
                            break;
                        case R.id.Bttn4:
                            dataSended = four;
                            break;
                        case R.id.Bttn5:
                            dataSended = five;
                            break;
                        case R.id.Bttn6:
                            dataSended = six;
                            break;
                        case R.id.Bttn7:
                            dataSended = seven;
                            break;
                        case R.id.Bttn8:
                            dataSended = eight;
                            break;
                        case R.id.Bttn9:
                            dataSended = nine;
                            break;
                        case R.id.Bttn0:
                            dataSended = zero;
                            break;
                        case R.id.listBttn: dataSended=list;
                            break;
                        case R.id.quickBttn:dataSended=quickView;
                            break;
                        case R.id.volUPBttn:dataSended=volUP;
                            break;
                        case R.id.VolDownBttn:dataSended=volDown;
                            break;
                        case R.id.progUpBttn:dataSended=progUp;
                            break;
                        case R.id.progDownBttn:dataSended=progDown;
                            break;
                        case R.id.favBttn:dataSended=fav;
                            break;
                        case R.id.ratioBttn:dataSended=ratio;
                            break;
                        case R.id.muteBttn: dataSended=mute;
                            break;

                    }
                    getClienMng().sendData(dataSended);

                }
            };
        }
    }


}
