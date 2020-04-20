package com.lec.android.a014_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //대화상자 객체 2개
    Dialog dlg1;
    ImageView ivDlgBanner;
    Button btnDlgEvent;


    Dialog dlg2;
    TextView tvResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult=findViewById(R.id.tvResult);


        //Dialog 클래스로 생성
        dlg1=new Dialog(this); //다이얼로그 객체 생성
        dlg1.setContentView(R.layout.dialog_layout11); //다이얼로그 화면 등록

        // Dialog 안의 View 객체들 얻어오기
        ivDlgBanner=dlg1.findViewById(R.id.ivDlgBanner); //dialog 안에!
        btnDlgEvent=dlg1.findViewById(R.id.btnDlgEvent);

        btnDlgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivDlgBanner.setImageResource(R.drawable.face01);
                Toast.makeText(getApplicationContext(),"다이얼로그버튼을 눌렀어요",Toast.LENGTH_SHORT).show();
            }
        });

        //Activity에 Dialog 등록하기
        dlg1.setOwnerActivity(MainActivity.this);//누구의 dialog냐! Main꺼지!
        dlg1.setCanceledOnTouchOutside(true); //boolean타입으로 받는다. 다이얼로그 바깥 여백 클릭할시(혹은 back 버튼 클릭시)hide()상태가 된다.
                    //종료할것인지 여부, true: 종료됨, false: 종료안됨.
                    //false넣으면 다이얼로그 내부에 나가는 버튼안만들어줘서 아래에 back버튼 눌러줘야한다.

        //#2
        dlg2=new Dialog(this);
        dlg2.setContentView(R.layout.dialog_layout12);
        dlg2.setOwnerActivity(MainActivity.this);
        dlg2.setCanceledOnTouchOutside(false);

        final EditText etName=dlg2.findViewById(R.id.etName);
        Button btnOk=dlg2.findViewById(R.id.btnOk);
        Button btnCancel=dlg2.findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=etName.getText().toString();
                tvResult.setText(str);
                dlg2.dismiss(); //다이얼로그 객체 제거
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg2.dismiss();
            }
        });

        //다이얼로그가 등장할때 호출되는 메소드==> 다이얼로그 다시 누르면 입력했던게 초기화된상태로!
        dlg2.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                etName.setText("");

            }
        });

    }//end onCreate()

    public void showDialog1(View v){
        dlg1.show(); //다이얼로그 띄우기
    }

    public void showDialog2(View v){
        dlg2.show();
    }
}//end Activity
