package com.example.test;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;

public class FingerCounter extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnTurnOn;
    private ImageButton btnTurnOff;
    private Toast mToast;
    String topic = "Test";
    String serverURI = "tcp://test.mosquitto.org:1883";
    String clientId = "MqttAndroid";
    MQTT mqtt = new MQTT();
    private MqttAndroidClient mqttAndroidClient;
    private static final String TAG = "LedControlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_counter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
       // actionBar.setLogo(R.mipmap.ic_launcher);
       // actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Finger Counter");

        initView();
    }

    private void initView() {
        btnTurnOn = findViewById(R.id.on_hand);
        btnTurnOff = findViewById(R.id.off_hand);

        btnTurnOn.setOnClickListener(this);
        btnTurnOff.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String command = "";

        if (viewId == R.id.on_hand) {
            command = "ON"; // Gửi chuỗi "on" để bật đèn
        } else if (viewId == R.id.off_hand) {
            command = "OFF"; // Gửi chuỗi "off" để tắt đèn
        }

        // Gửi yêu cầu điều khiển đèn LED thông qua MQTT
        mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, command, topic);

        // Hiển thị thông báo
        showToast("LED " + (command.equals("on") ? "turned on" : "turned off"));
    }

    private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }
}
