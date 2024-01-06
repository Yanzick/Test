package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;


public class MainActivity2 extends AppCompatActivity {

    private MqttAndroidClient mqttAndroidClient;
    private static final String TAG = "MainActivity4";
    String topic = "house/build";
    String serverURI = "tcp://broker.hivemq.com:1883";
    String clientId = "MqttAndroid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Activity");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sub_activity1) {
            startActivities(new Intent[]{new Intent(MainActivity2.this, FingerCounter.class)});
            return true;
        } else if (id == R.id.sub_activity2) {
            startActivities(new Intent[]{new Intent(MainActivity2.this, LedControlActivity.class)});
            return true;
        } else if (id == R.id.Profile) {
            startActivities(new Intent[]{new Intent(MainActivity2.this, Profile.class)});
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
