package com.example.test;

/*import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private Button btnConnect;
    private Button btnChange;
    private MqttAndroidClient mqttAndroidClient;
    private EditText inputTopic;
    private EditText inputSURI;
    private long backPressTime;

    private Toast mToast;
    String topic;
    String serverURI;
    String clientId = "MqttAndroid";
    int STT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    public void onBackPressed() {
        if (backPressTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            showToast("Press back again to exit the application");
        }
        backPressTime = System.currentTimeMillis();
    }

    private void initView() {
        btnConnect = findViewById(R.id.btn_connect);
        btnChange = findViewById(R.id.btn_change);
        inputTopic = findViewById(R.id.topic);
        inputSURI = findViewById(R.id.SURI);

        btnConnect.setOnClickListener(this);
        btnChange.setOnClickListener(this);
    }

    public EditText getTopic() {
        return inputTopic;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_connect) {
            topic = inputTopic.getText().toString();
            serverURI = inputSURI.getText().toString();
            showToast("Connecting...");
            connect();
        } else if (viewId == R.id.btn_change) {
            if (STT == 0) {
                showToast("Vui lòng kết nối MQTT");
            } else {
                change();
            }
        }
    }

    private void change() {
        startActivities(new Intent[]{new Intent(MainActivity.this, MainActivity2.class)});
    }
    private void connect() {
        Log.d(TAG, "Connecting to serverURI: " + serverURI);
        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), serverURI, clientId);
        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Log.e(TAG, "connectionLost: "+cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.e(TAG, "messageArrived: " + topic + ":" + message.toString());
                showToast("time: " + System.currentTimeMillis() + "\r\n" + "topic: " + topic + "\r\n" + "message: " + message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Log.d(TAG, "deliveryComplete: ");
            }
        });

        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setAutomaticReconnect(true);

        try {
            mqttAndroidClient.connect(connectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "connect onSuccess: " + asyncActionToken.getClient().getClientId());
                    showToast("connect onSuccess");
                    STT = 1;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast("connect onFailure"+exception.getMessage());
                        }
                    });
                    Log.e(TAG, "connect onFailure: ");
                    STT = 0;
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.e(TAG, "MqttException: " + e.getMessage());
        }
    }

    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}*/


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    public static String getTopic;
    private Button btnConnect;
    private Button btnPublish;
    private MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions connectOptions;
    private Button btnSubscribe, btnChange;
    private TextView tvMsg;
    private TextView tvStatus;
    private EditText inputMsg,inputtopic,inputSURI;
    private long backPressTime;
    private Toast mToast;
    String topic ;
    String serverURI;

    int STT=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btn_connect);

        btnChange = findViewById(R.id.btn_change);

        btnConnect.setOnClickListener(this);


        btnChange.setOnClickListener(this);



        tvStatus = findViewById(R.id.text);

        inputtopic = findViewById(R.id.topic);
        inputSURI = findViewById(R.id.SURI);
    }

    public void onBackPressed(){
        if (backPressTime+2000>System.currentTimeMillis()){
            super.onBackPressed();
        } else {
            Toast.makeText(MainActivity .this,"Press back again to exit the application", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();

    }


    private void initView() {
    }

    public EditText getTopic()
    {
        return inputtopic;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btn_connect) {
            topic = inputtopic.getText().toString();
            serverURI = inputSURI.getText().toString();
            tvStatus.setText("connect...");
            connect();
        } else if (viewId == R.id.btn_change) {
            if (STT == 0) {
                Toast.makeText(MainActivity.this, "Please Connect MQTT", Toast.LENGTH_SHORT).show();
            } else {
                change();
            }
        }
    }
    private void change()
    {
        startActivities(new Intent[]{new  Intent(MainActivity .this, MainActivity2.class)});
    }


    private void connect() {
        String clientId = MqttClient.generateClientId();
        mqttAndroidClient = new MqttAndroidClient(this.getApplicationContext(), serverURI, clientId);

        connectOptions = new MqttConnectOptions();
        connectOptions.setAutomaticReconnect(true);

        try {
            IMqttToken token = mqttAndroidClient.connect(connectOptions, null);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.e(TAG, "connect onSuccess: " + asyncActionToken.getClient().getClientId());

                    Toast.makeText(MainActivity .this, "connect onSuccess", Toast.LENGTH_SHORT).show();
                    tvStatus.setText("connect onSuccess");
//                    publish("Success");
//                    change();
                    STT=1;
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    tvStatus.setText("connect onFailure");
                    Log.e(TAG, "connect onFailure: " + exception.getMessage(), exception);
                    STT = 0;
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }


}
