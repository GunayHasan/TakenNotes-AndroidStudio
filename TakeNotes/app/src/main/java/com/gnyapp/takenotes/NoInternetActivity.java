package com.gnyapp.takenotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.widget.Switch;

public class NoInternetActivity extends AppCompatActivity {

    private WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        wifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        AppCompatButton mobil_data = findViewById(R.id.mobil_data);

        mobil_data.setOnClickListener(v -> {
            Vibrator v3 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            assert v3 != null;
            v3.vibrate(30);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Intent i = new Intent(Settings.ACTION_DATA_USAGE_SETTINGS);
                startActivity(i);
            }else{
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                startActivity(intent);
            }
        });

        Switch btnSwitch = findViewById(R.id.switcher);
        btnSwitch.setChecked(wifiManager.isWifiEnabled());
        btnSwitch.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                Intent panelIntent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                startActivityForResult(panelIntent, 545);
            }else {
                wifiManager.setWifiEnabled(isChecked);

                int vibrate_like_actual_switch = 100;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(30);
                    }
                }, vibrate_like_actual_switch);
                Vibrator v2 = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v2.vibrate(25);
            }
        }));

    }
}