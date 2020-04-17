package com.lec.android.a007_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

//화면이 없는 Activity 생성 가능 setContentView가 없음.
public class CalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //받고
        Intent intent=getIntent();
        int num1=intent.getIntExtra("num1",0);
        int num2=intent.getIntExtra("num2",0);

        //돌려주기
        intent.putExtra("plus",num1+num2);
        intent.putExtra("minus",num1-num2);

        //호출한 화면에 값 되돌려주기
        setResult(RESULT_OK,intent);

        finish(); //onDestroy()와 동일
    }// end onCreate()



}//end Activity
