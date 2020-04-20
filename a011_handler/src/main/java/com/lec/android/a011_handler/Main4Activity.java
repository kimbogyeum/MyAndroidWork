package com.lec.android.a011_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;
//TODO
//Value1
//1~10 까지 1초단위로 증가시키기
//10초에 도달하면 멈추어서 Toast 띄우기
//message 사용

//Value2
//1~20까지 1초단위로 증가시키기
//10초에 도달하면 멈추어서 Toast 띄우기
//Handler 사용
public class Main4Activity extends AppCompatActivity {

    int value1= 0,value2=0,value3= 0,value4=0,value5=0 ;

    TextView tvResult1,tvResult2,tvResult3,tvResult4,tvResult5;
    Handler handler2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvResult1 = findViewById(R.id.tvResult1);
        tvResult2 = findViewById(R.id.tvResult2);
        tvResult3 = findViewById(R.id.tvResult3);
        tvResult4 = findViewById(R.id.tvResult4);
        tvResult5 = findViewById(R.id.tvResult5);

        //value1
        handler1.sendEmptyMessage(1);

        //value2
        handler2= new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                value2++;
                tvResult2.setText("value2: "+value2);
                if(value2<20){
                    handler2.postDelayed(this,1000);
                }else{
                    doUpload(2);
                }
            }
        },0);


    }//end onCreate()


    //value 1
    Handler handler1=new Handler() {

        @Override
        public void handleMessage(@NonNull Message msg) {
            value1++;
            tvResult1.setText("value1: " + value1);

            if(value1<10){
                handler1.sendEmptyMessageDelayed(1,1000);
            }else{
            doUpload(msg.what);
        }
        }

    };


    //Toast 만들어주기
    void doUpload(int n){
        Toast.makeText(getApplicationContext(),n+": 업로드 끝",Toast.LENGTH_LONG).show();
    }








}//end Activity
