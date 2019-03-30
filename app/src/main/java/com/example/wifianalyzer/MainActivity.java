package com.example.wifianalyzer;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    TextView text;
    String str;
    String str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView)findViewById(R.id.text1);
        text.setMovementMethod(new ScrollingMovementMethod());
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WifiManager manager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
                WifiInfo info = manager.getConnectionInfo();
                int ip = info.getIpAddress();
                str =   "\nSSID = "+info.getSSID()+"\nRSSI = +"+info.getRssi()+"\nMacAddress = "+info.getMacAddress()+"\nBSSID = "+info.getBSSID()+"\nIpAddress = "+info.getIpAddress()+"\nNetworkID = "+info.getNetworkId()+"\n";
                str2 = str + str2;

                if(ip == 0){
                    String str1 = "Please switch on the Wifi!";
                    text.setText(str1);
                }
                else{
                    text.setText(str);
                }

                File file = new File(getApplicationContext().getFilesDir(),"WiFi");
                if(!file.exists())
                {
                    file.mkdir();
                }

                try
                {
                    File f = new File(file, "WifiData.txt");
                    FileWriter writer = new FileWriter(f);
                    writer.append(str2);
                    writer.flush();
                    writer.close();
                }
                catch (Exception e)
                {

                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    File file = new File(getApplicationContext().getFilesDir(), "WiFi");
                    File f = new File(file, "WifiData.txt");

                    FileInputStream input = new FileInputStream(f);
                    DataInputStream data = new DataInputStream(input);
                    BufferedReader br = new BufferedReader(new InputStreamReader(data));
                    String line;
                    while((line = br.readLine())!=null)
                    {
                        str2 = str2 + line +"\n";
                    }
                    text.setText(str2);

                }
                catch (Exception e)
                {

                }
            }
        });
    }
}
