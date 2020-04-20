package com.lec.android.a011_handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
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

    int mainValue = 0;
    int backValue1 = 0;
    int backValue2 = 0;
    TextView tvMainValue;
    TextView tvBackValue1, tvBackValue2, tvBackValue3, tvBackValue4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tvMainValue = findViewById(R.id.tvMainValue);
        tvBackValue1 = findViewById(R.id.tvBackValue1);
        tvBackValue2 = findViewById(R.id.tvBackValue2);
        tvBackValue3 = findViewById(R.id.tvBackValue3);
        tvBackValue4 = findViewById(R.id.tvBackValue4);

        Main4Activity.BackThread1 thread1=new Main4Activity.BackThread1();
        thread1.setDaemon(true);
        thread1.start();


    }//end onCreate()
    class BackThread1 extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){


                //방법 1: 메인에서 생성된 Handler 객체의 sentEmptyMessage 를 통해 message 전달
                backValue1++;
                handler1.sendEmptyMessage(1);// 아래 핸들러에 1번 message를 전송하면 핸들러가 main 스레드를 건드릴 수 있는거지.

                //방법 2: 메인에서 생성된 Handler 객체의 post어쩌구저쩌구()를 통해 Runnable 객체 전달.post로 시작하면 Runnable
                backValue2+=2;
                handler2.post(new Runnable() {
                    @Override
                    public void run() {//Runnable의 run()에서 메인 UI 접근
                        tvBackValue2.setText("BackValue2: "+ backValue2);
                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }//end while
        }//end run
    }//end BackThread1
}//end Activity
