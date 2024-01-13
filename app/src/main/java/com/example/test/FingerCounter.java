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

    private ImageButton btnTurnOffAll;
    private ImageButton btnTurnOnLed1;


    private Toast mToast;
    String topic = "mqtt/handlethings";
    String serverURI = "tcp://broker.hivemq.com:1883";
    String clientId = "MqttAndroid";
    MQTT mqtt = new MQTT();
    private MqttAndroidClient mqttAndroidClient;
    private static final String TAG = "Activiti5";

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

    private void initView() {
        btnTurnOffAll = findViewById(R.id.on_hand);
        btnTurnOnLed1 = findViewById(R.id.off_hand);

        btnTurnOffAll.setOnClickListener(this);
        btnTurnOnLed1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        String command = "";

        if (viewId == R.id.btnTurnOffAll) {
            command = "ON"; // Tắt hết cả hai đèn
        } else if (viewId == R.id.btnTurnOnLed1) {
            command = "OFF"; // Mở LED 1
        }
        // Gửi yêu cầu điều khiển đèn LED thông qua MQTT
        mqtt.connect(mqttAndroidClient, getApplicationContext(), serverURI, clientId, TAG, 1, command, topic);

        // Hiển thị thông báo
        showToast("Camera open");
    }private void showToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

}