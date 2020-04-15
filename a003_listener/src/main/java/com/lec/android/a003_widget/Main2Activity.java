package com.lec.android.a003_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    EditText et;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvResult=findViewById(R.id.btneql);
        et=findViewById(R.id.et);

        Button btnC=findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myapp","지웁니다.");
                et.setText("0");
            }
        });
        Button btn0=findViewById(R.id.btn0);
        Button btn1=findViewById(R.id.btn1);
        Button btn2=findViewById(R.id.btn2);
        Button btn3=findViewById(R.id.btn3);
        Button btn4=findViewById(R.id.btn4);
        Button btn5=findViewById(R.id.btn5);
        Button btn6=findViewById(R.id.btn6);
        Button btn7=findViewById(R.id.btn7);
        Button btn8=findViewById(R.id.btn8);
        Button btn9=findViewById(R.id.btn9);


        class MyListener implements View.OnClickListener{

            String name;
            public MyListener(String name) {this.name=name;}

            @Override
            public void onClick(View v) {
                String tag= (String)v.getTag();
                String text=(String)((Button)v).getText();
                String msg=String.format("%s 버튼 %s 이 클릭[%s]",name,text,tag);
                Log.d("myapp",msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name));
            }
        }
        btn0.setOnClickListener(new MyListener("1"));



        Button btnSum=findViewById(R.id.btnSum);
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myapp","더합니다.");

            }
        });

    }//end onCreate


    public void onClick(View v){
        Log.d("myapp","지웁니다.");
        et.setText("");
    }
}//end AppCompatActivity
