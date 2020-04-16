package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main4Activity extends AppCompatActivity {
//checkbox와 다르게 group에  listener가 달린다.

    RadioGroup rg;
    Button btnResult;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //체크박스와는 달리 Radio Button은 각각 선언하는것이 아니라 RadioGroup으로 선언하여 사용한다.
        rg=findViewById(R.id.rg);
        btnResult=findViewById(R.id.btnResult);
        tvResult=findViewById(R.id.tvResult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택된 라디오버튼의 id값 가져오기
                int id=rg.getCheckedRadioButtonId();
                //위 id 값을 통해 radioButton 객체 가져오기
                RadioButton rb=findViewById(id);
                tvResult.setText("결과: "+rb.getText());
            }
        });
        //라디오버튼을 선택했을때도 위와같은 동작하게 하기
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //checkedId: 선택된 라디오버튼의 id
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=findViewById(checkedId);
                tvResult.setText("결과"+rb.getText());
            }
        });
    }//end onCreate
}//end AppCompatActivity
