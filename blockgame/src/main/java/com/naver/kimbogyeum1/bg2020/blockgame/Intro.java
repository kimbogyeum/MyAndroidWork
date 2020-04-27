package com.naver.kimbogyeum1.bg2020.blockgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

// 현재 화면이 가로/세로 변경되지 않도록 지정하기
// AndroidManifest.xml 에 screenOrientation="portrait" 지정

// 액션바 없애기 -> styles.xml 에서 NoActionBar 지정

public class Intro extends AppCompatActivity {
    //초기화면
    //3초동안 보이고 다음화면(Main)으로 넘어가기..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        Handler mHandler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Intent intent=new Intent(getApplicationContext(),Main.class); //3초뒤에 Main으로 넘어가겠어!
                startActivity(intent);
                finish(); //적층형으로 되어있으니까 꼭 다음화면으로 넘어가면 intro 화면은 종료해줘야해.
            }


        };
        mHandler.sendEmptyMessageDelayed(1,3000); //3초뒤에 넘어가겠어.

    }//end onCreate()
}//end Activity

