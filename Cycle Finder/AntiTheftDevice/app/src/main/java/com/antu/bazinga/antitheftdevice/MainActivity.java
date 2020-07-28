package com.antu.bazinga.antitheftdevice;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
//import android.databinding.DataBindingUtil;
//import com.antu.bazinga.antitheftdevice.databinding.ActivityMainBinding;

//POP UP SCREEN E LIST THAKBE

public class MainActivity extends AppCompatActivity  {
    private final static String TAG = "MAINACTIVITY_TAG";
    private static final String SOURCE = "source";
    private static final String BLUETOOTH_CONTROLLER = "bt_control";
    private static final String START_MONITOR = "start_monitor";
    ConnectedThread mConnectedThread = null;
    private TextView mBluetoothStatus;
    private BluetoothAdapter mBTAdapter;
    private BluetoothSocket mBTSocket = null; // bi-directional client-to-client data path
    private Handler mHandler; // Our main handler that will receive callback notifications
    private String mBluetoothAddress, mBTName;
    private BluetoothDevice bluetoothDevice= null;

    private final String BLUETOOTH_ADDRESS = "bt_address";
    private final String BLUETOOTH_NAME = "bt_name";
    private static final String BLUETOOTH_DEVICE = "bt_device";
    private static final UUID BTMODULEUUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); // "random" unique identifier
//    private static final UUID BTMODULEUUID = UUID.fromString("1553273b-afcc-44ed-b2e8-5fbea7571a74");
    // #defines for identifying shared types between calling functions
    private final static int REQUEST_ENABLE_BT = 1; // used to identify adding bluetooth names
    private final static int MESSAGE_READ = 2; // used in bluetooth handler to identify message update
    private final static int CONNECTING_STATUS = 3; // used in bluetooth handler to identify message status

    private static boolean isMonitoringStarted = false;
    private static boolean isIsMonitoringStopped = true;

//    ActivityMainBinding mMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBluetoothStatus = (TextView)findViewById(R.id.bluetoothStatus);
        mBTAdapter = BluetoothAdapter.getDefaultAdapter(); // get a handle on the bluetooth radio

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
//        mConnectedThread = null;
        createNewHandler();
        if(bundle != null){
//            Toast.makeText(this,"Bundle null na",Toast.LENGTH_SHORT).show();
//            mConnectedThread = (ConnectedThread) bundle.get("CONNECTION_THREAD");
           /* mBluetoothAddress = (String) bundle.get(BLUETOOTH_ADDRESS);
            mBluetoothName = (String) bundle.get(BLUETOOTH_NAME);
            Toast.makeText(getBaseContext(), mBluetoothAddress +" ~ "+ mBluetoothName, Toast.LENGTH_SHORT).show();
            System.out.println("***************8 "+mBluetoothAddress + mBluetoothName);*/
            mBTName = (String) bundle.get(BLUETOOTH_NAME);
            bluetoothDevice = (BluetoothDevice) bundle.get(BLUETOOTH_DEVICE);
            if(mBTName != null && bluetoothDevice != null) {
                Log.d(TAG, "Dhuksi onNewIntent");
                establishConnection();
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            String source = (String) bundle.get(SOURCE);
            if(source!= null && source.equals(BLUETOOTH_CONTROLLER) && mConnectedThread != null){
                Toast.makeText(this, "Already Connected", Toast.LENGTH_SHORT).show();
                return;
            }
//            Toast.makeText(this," Abar Bundle null na",Toast.LENGTH_SHORT).show();
            mBTName = (String) bundle.get(BLUETOOTH_NAME);
            bluetoothDevice = (BluetoothDevice) bundle.get(BLUETOOTH_DEVICE);
            if(mBTName != null && bluetoothDevice != null) {
                Log.d(TAG, mBTName);
                establishConnection();
            }
        }
    }

    private void createNewHandler() {
        mHandler = new Handler(){
            public void handleMessage(android.os.Message msg){
                if(msg.what == MESSAGE_READ){
                    String readMessage = null;
                    try {
                        readMessage = new String((byte[]) msg.obj, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    mBluetoothStatus.setText(readMessage);
                }

                if(msg.what == CONNECTING_STATUS){
                    if(msg.arg1 == 1)
                        mBluetoothStatus.setText("Connected to Device: " + (String)(msg.obj));
                    else if(msg.arg1 == 2){
                        mBluetoothStatus.setText("createBluetoothSocket");
                    }
                    else {
                        mBluetoothStatus.setText("Please try again");
//                        Toast.makeText(this,"Connection Failed",Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getBaseContext(), "connection Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

    private void establishConnection() {

        final BluetoothDevice device = bluetoothDevice;
        final String mBluetoothName = mBTName;
        String checkString = mBTName.replaceAll("\\P{Print}","");
        if(!checkString.equals("HC-05")){
            Toast.makeText(this, "Incorrect device. Please Choose HC-05", Toast.LENGTH_SHORT).show();
            return;
        }
        mBluetoothStatus.setText("Connecting...");
//        Toast.makeText(getBaseContext(), "establishConnection", Toast.LENGTH_SHORT).show();
        new Thread()
        {
            public void run() {
                boolean fail = false;

//                BluetoothDevice device = mBTAdapter.getRemoteDevice(mBluetoothAddress);

                try {
                    mBTSocket = createBluetoothSocket(device);
                } catch (IOException e) {
                    fail = true;
                    mHandler.obtainMessage(CONNECTING_STATUS, 2, -1)
                            .sendToTarget();
//                    Toast.makeText(getBaseContext(), "arg 2", Toast.LENGTH_SHORT).show();
                }
                // Establish the Bluetooth socket connection.
                try {
//                    Toast.makeText(getBaseContext(), " before mBTSocket.connect();", Toast.LENGTH_SHORT).show();
                    mBTSocket.connect();
//                    mBluetoothStatus.setText("mBTSocket.connect();");
//                    Toast.makeText(getBaseContext(), "mBTSocket.connect();", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    try {
                        fail = true;
                        mBTSocket.close();
//                        mBluetoothStatus.setText("mBTSocket.close();");
                        e.printStackTrace();
                        mHandler.obtainMessage(CONNECTING_STATUS, -1, -1)
                                .sendToTarget();

                    } catch (IOException e2) {
                        //insert code to deal with this
                        e2.printStackTrace();
//                        Toast.makeText(getBaseContext(), "e2 Socket creation failed", Toast.LENGTH_SHORT).show();
                    }
                }
                if(!fail) {
                    mConnectedThread = new ConnectedThread(mBTSocket,mHandler);
//                    mConnectedThread.start();  // No need as we don not need to read anything from device

//                    mBluetoothStatus.setText(" mConnectedThread.start();");
//                    Toast.makeText(getBaseContext(), "Should work fine now", Toast.LENGTH_SHORT).show();
                    mHandler.obtainMessage(CONNECTING_STATUS, 1, -1, mBluetoothName)
                            .sendToTarget();
                }
            }
        }.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        if(!mBTAdapter.isEnabled()){
            mBluetoothStatus.setText(getResources().getString(R.string.connect_your_device));
            mConnectedThread = null;
        }
    }

    public void startMonitoring(View v)
    {
        if(mConnectedThread != null){
            /*if(isMonitoringStarted){
                Toast.makeText(this,"Device is already being monitored",Toast.LENGTH_SHORT).show();
                return;
            }
            isMonitoringStarted = true;
            isIsMonitoringStopped = false;*/
            mConnectedThread.write("1"); // START_MONITORING
            Intent intent = new Intent(this,startMonitor.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Device not Connected!",Toast.LENGTH_SHORT).show();
        }

    }
    public void stopMonitoring(View v)
    {
        if(mConnectedThread != null){
            /*if(isIsMonitoringStopped){
                Toast.makeText(this,"Device is not being monitored!",Toast.LENGTH_SHORT).show();
                return;
            }
            isMonitoringStarted = false;
            isIsMonitoringStopped = true;*/
            mConnectedThread.write("2"); // STOP_MONITORING
            Toast.makeText(this,"Monitoring Stopped!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Device not Connected!",Toast.LENGTH_SHORT).show();
        }

    }
    public void trackMyRide(View v)
    {
//        Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,MapsActivity.class));
    }

    public void connectDevice(View v)
    {
//        Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,BluetoothController.class));
    }
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        return  device.createRfcommSocketToServiceRecord(BTMODULEUUID);
        //creates secure outgoing connection with BT device using UUID
    }

    public void goToServer(View v)
    {
        Intent intent = new Intent(this,streamVideo.class);
        startActivity(intent);
    }

}
