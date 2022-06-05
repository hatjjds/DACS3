package com.example.dacs3.AppUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.dacs3.MainActivity;
import com.example.dacs3.PlayerActiviti;
import com.example.dacs3.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();

        LoadData();
    }

    private void LoadData() {
        if(CheckNetwork.CheckNetworking(this)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                }
            },2500);
        }
        else{
            Toast.makeText(this, "Không có kết nối internet", Toast.LENGTH_LONG).show();
        }
    }
}