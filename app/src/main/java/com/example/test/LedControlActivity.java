package com.example.test;

/*import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class LedControlActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnToggleOn, btnToggleOff;
    private Toast mToast;
    String topic = "house/build";
    String serverURI = "tcp://broker.hivemq.com:1883";
    String clientId = "MqttAndroid";
    MQTT mqtt = new MQTT();
    private MqttAndroidClient mqttAndroidClient;
    private static final String TAG = "LedControlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("LED Control");

        initView();
    }

    private void initView() {
        btnToggleOn = findViewById(R.id.btnToggleOn);
        btnToggleOff = findViewById(R.id.btnToggleOff);

        btnToggleOn.setOnClickListener(this);
        btnToggleOff.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.btnToggleOn) {
            mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, "1", topic);
            showToast("LED 1 turned on");
        } else if (viewId == R.id.btnToggleOff) {
            mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, "0", topic);
            showToast("LED turned off");
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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;
public class LedControlActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnTurnOffAll;
    private Button btnTurnOnLed1;
    private Button btnTurnOffLed1;
    private Button btnTurnOnLed2;
    private Button btnTurnOffLed2;
    private Button btnTurnOnBothLeds;

    private Toast mToast;
    String topic = "Test2";
    String serverURI = "tcp://test.mosquitto.org:1883";
    String clientId = "MqttAndroid";
    MQTT mqtt = new MQTT();
    private MqttAndroidClient mqttAndroidClient;
    private static final String TAG = "LedControlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
       // actionBar.setLogo(R.mipmap.ic_launcher);
        //actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("LED Control");

        initView();
    }

    private void initView() {
        btnTurnOffAll = findViewById(R.id.btnTurnOffAll);
        btnTurnOnLed1 = findViewById(R.id.btnTurnOnLed1);
        btnTurnOffLed1 = findViewById(R.id.btnTurnOffLed1);
        btnTurnOnLed2 = findViewById(R.id.btnTurnOnLed2);
        btnTurnOffLed2 = findViewById(R.id.btnTurnOffLed2);
        btnTurnOnBothLeds = findViewById(R.id.btnTurnOnBothLeds);

        btnTurnOffAll.setOnClickListener(this);
        btnTurnOnLed1.setOnClickListener(this);
        btnTurnOffLed1.setOnClickListener(this);
        btnTurnOnLed2.setOnClickListener(this);
        btnTurnOffLed2.setOnClickListener(this);
        btnTurnOnBothLeds.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String command = "";

        if (viewId == R.id.btnTurnOffAll) {
            command = "0"; // Tắt hết cả hai đèn
        } else if (viewId == R.id.btnTurnOnLed1) {
            command = "1"; // Mở LED 1
        } else if (viewId == R.id.btnTurnOffLed1) {
            command = "2"; // Tắt LED 1
        } else if (viewId == R.id.btnTurnOnLed2) {
            command = "3"; // Mở LED 2
        } else if (viewId == R.id.btnTurnOffLed2) {
            command = "4"; // Tắt LED 2
        } else if (viewId == R.id.btnTurnOnBothLeds) {
            command = "5"; // Mở cả hai đèn
        }

        // Gửi yêu cầu điều khiển đèn LED thông qua MQTT
        mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, command, topic);

        // Hiển thị thông báo
        showToast("LED toggled");
    }


    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}

