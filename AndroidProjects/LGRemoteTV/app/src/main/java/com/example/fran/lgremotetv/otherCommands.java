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
 * Created by fran on 18/12/14.
 */
public class otherCommands extends Fragment {
    clientSocketMng clienMng = null;
    GridLayout gL;
    View.OnClickListener onClickListener = null;
    static final String guide = "21", home = "22", arrowUp = "26", arrowDown = "27", arrowRight = "28", arrowLeft = "29", Ok = "30",
            back = "31", info = "32", exit = "33", red = "34", green = "35", yellow = "36", blue = "37", text = "38", tOpot = "39",
            subtitle = "40", stopV = "41", play = "42", pause = "43", rew = "44", fwd = "45", ad = "46", menu = "47";
    utils utils=new utils();


    public GridLayout getgL() {
        return gL;
    }

    public void setgL(GridLayout gL) {
        this.gL = gL;
    }

    public clientSocketMng getClienMng() {
        return clienMng;
    }

    public void setClienMng(clientSocketMng clienMng) {
        this.clienMng = clienMng;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
      setgL((GridLayout) inflater.inflate(R.layout.fragment_remote_tv1, container, false));
        initonClickListener();
        utils.addListeners(getgL(),getOnClickListener());
        return getgL();
    }

    private void initonClickListener() {
        if (getOnClickListener() == null) {

            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();
                    String dataSended = "";
                    switch (id) {
                        case R.id.guideBttn:
                            dataSended = guide;
                            break;
                        case R.id.home:
                            dataSended = home;
                            break;
                        case R.id.menuBttn:
                            dataSended = menu;
                        case R.id.arrowUpBttn:
                            dataSended = arrowUp;
                            break;
                        case R.id.downArrowBttn:
                            dataSended = arrowDown;
                            break;
                        case R.id.rightArrowBttn:
                            dataSended = arrowRight;
                            break;
                        case R.id.leftArrowBttn:
                            dataSended = arrowLeft;
                            break;
                        case R.id.okBttn:
                            dataSended = Ok;
                            break;
                        case R.id.backBttn:
                            dataSended = back;
                            break;
                        case R.id.infoBttn:
                            dataSended = info;
                            break;
                        case R.id.exitBttn:
                            dataSended = exit;
                            break;
                        case R.id.redBttn:
                            dataSended = red;
                            break;
                        case R.id.greenBttn:
                            dataSended = green;
                            break;
                        case R.id.yellowBttn:
                            dataSended = yellow;
                            break;
                        case R.id.blueBttn:
                            dataSended = blue;
                            break;
                        case R.id.teletxtBttn:
                            dataSended = text;
                            break;
                        case R.id.tOptBttn:
                            dataSended = tOpot;
                            break;
                        case R.id.subtitleBttn:
                            dataSended = subtitle;
                            break;
                        case R.id.stopBttn:
                            dataSended = stopV;
                            break;
                        case R.id.playBttn:
                            dataSended = play;
                            break;
                        case R.id.pauseBttn:
                            dataSended = pause;
                            break;
                        case R.id.rewBttn:
                            dataSended = rew;
                            break;
                        case R.id.fwBttn:
                            dataSended = fwd;
                            break;
                        case R.id.adBttn:
                            dataSended = ad;
                            break;
                    }
                    getClienMng().sendData(dataSended);

                }
            };
        }
    }
}
