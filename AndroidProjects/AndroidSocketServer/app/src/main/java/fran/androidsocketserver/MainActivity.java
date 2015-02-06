package fran.androidsocketserver;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    ServerSocketThread serverSocket;
    private String receivedInfoTxt;
    private Handler handler;
    private final static int receive=1;
    private TextView serverTagTxt;
    private final static String DATA_RECEIVED_INTENT = "primavera.arduino.intent.action.DATA_RECEIVED";
    private final static String SEND_DATA_INTENT = "primavera.arduino.intent.action.SEND_DATA";
    private final static String DATA_EXTRA = "primavera.arduino.intent.extra.DATA";
    private final static String FirstMssg="Envio datos al servidor";

    public String getReceivedInfoTxt() {
        return receivedInfoTxt;
    }

    public void setReceivedInfoTxt(String receivedInfoTxt) {
        this.receivedInfoTxt = receivedInfoTxt;
    }

    public ServerSocketThread getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocketThread serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public TextView getServerTagTxt() {
        return serverTagTxt;
    }

    public void setServerTagTxt(TextView serverTagTxt) {
        this.serverTagTxt = serverTagTxt;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setServerSocket(new ServerSocketThread());
       setServerTagTxt((TextView) findViewById(R.id.receivedTxt));
        setHandler(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                getServerTagTxt().append((String)msg.obj+"\n");
                if(msg.what==receive && !FirstMssg.equals((String)msg.obj)) {

                    //we use "-" sign like an end of file for arduino
                    sendArduinoData((String)msg.obj+"-");
                }
                super.handleMessage(msg);
            }
        });
        getServerSocket().setHandler(getHandler());
        getServerSocket().start();



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getServerSocket().closeSocketServer();
        System.exit(-1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void sendArduinoData(String data) {

        Intent intent = new Intent(SEND_DATA_INTENT);

        intent.putExtra(DATA_EXTRA, data.getBytes());
        sendBroadcast(intent);
    }
}
