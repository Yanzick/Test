package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;

public class FingerCounter extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnonHand, btnoffHand;
    private TextView Status;

    private static final String TAG = "MainActivity5";
    private MqttAndroidClient mqttAndroidClient;
    private Toast mToast;
    String topic = "house/build";
    String serverURI = "tcp://broker.hivemq.com:1883";
    String clientId = "MqttAndroid";
    MQTT mqtt = new MQTT();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_counter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Finger Counter");

        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        btnonHand = findViewById(R.id.on_hand);

        btnoffHand = findViewById(R.id.off_hand);

        btnonHand.setOnClickListener(this);

        btnoffHand.setOnClickListener(this);

        Status = findViewById(R.id.Text);


    }


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

        if (viewId == R.id.on_hand) {
            mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, "on", topic);
            Status.setText("Enable Finger Counter");
        } else if (viewId == R.id.off_hand) {
            mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, "off", topic);
            Status.setText("Disable Finger Counter");
        }
    }
}
