package com.lec.android.a003_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    Button btn0,btn1,btn3,btn2,btn4,btn5,btn6,btn7,btn8,btn9;
    Button btnE,btnPlus,btnMinus,btnMul,btnDiv,btnC;
    EditText et;
    String cal1="";
    String cal2="";
    String symbol="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et=findViewById(R.id.et);
        btnE=findViewById(R.id.btnE);
        btnC=findViewById(R.id.btnC);
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnMinus);
        btnMul=findViewById(R.id.btnMul);
        btnDiv=findViewById(R.id.btnDiv);
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);


        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal2=et.getText().toString();
                if(symbol.equals("")||cal1.equals("")||cal2.equals("")) return;
                double result=0;

                try{
                    if (symbol.equals("+")){
                        result=Double.parseDouble(cal1)+Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));

                    }else if(symbol.equals("-")){
                        result=Double.parseDouble(cal1)-Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));

                    }else if (symbol.equals("x")){
                        result=Double.parseDouble(cal1)*Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));

                    }else {
                        result=Double.parseDouble(cal1)/Double.parseDouble(cal2);
                        et.setText(String.valueOf(result));
                    }
                }catch (Exception e){
                    et.setText("");
                }finally {
                    cal1="";
                    cal2="";
                    symbol="";
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.setText("0");
            }
        });

        class numberListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                String text=(String)((Button)v).getText();
                et.setText(et.getText().append(text));
            }
        }

        class calListener implements View.OnClickListener{
            @Override
            public void onClick(View v) {
                cal1=et.getText().toString();
                symbol=(String)((Button)v).getText();
                et.setText("");
                Log.d("listener",cal1);
            }
        }



        btn0.setOnClickListener(new numberListener());
        btn1.setOnClickListener(new numberListener());
        btn2.setOnClickListener(new numberListener());
        btn3.setOnClickListener(new numberListener());
        btn4.setOnClickListener(new numberListener());
        btn5.setOnClickListener(new numberListener());
        btn6.setOnClickListener(new numberListener());
        btn7.setOnClickListener(new numberListener());
        btn8.setOnClickListener(new numberListener());
        btn9.setOnClickListener(new numberListener());
        btnPlus.setOnClickListener(new calListener());
        btnMinus.setOnClickListener(new calListener());
        btnMul.setOnClickListener(new calListener());
        btnDiv.setOnClickListener(new calListener());

    }//end onCreate()
}//end AppCompatActivity
