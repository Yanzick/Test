package com.example.test;

import androidx.appcompat.app.ActionBar;
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
            showToast("LED turned on");
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
}
