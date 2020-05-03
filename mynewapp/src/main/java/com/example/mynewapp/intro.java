package com.example.mynewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler mHandler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class); //3초뒤에 Main으로 넘어가겠어!
                startActivity(intent);
                finish(); //적층형으로 되어있으니까 꼭 다음화면으로 넘어가면 intro 화면은 종료해줘야해.
            }


        };
        mHandler.sendEmptyMessageDelayed(1,3000); //3초뒤에 넘어가겠어.
    }
}
