package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
//안드로이드의 모든 리소스(Resoure)로 사용하는 파일들은
/*<파일명 규칙>
- 대문자 불가!
- 숫자 시작 불가!
- 공백, 특수문자(특수문자는 _만 가능)불가
*/


public class MainActivity extends AppCompatActivity {

    int [] imageId={R.drawable.a1, R.drawable.a2,R.drawable.a3,R.drawable.a5,R.drawable.a6};
    ImageView iv;


    //layer에는 사진 아무거나 default로 해놓고 onCreate에서 바꿔주면 댐.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv=findViewById(R.id.iv1);

        //res/drawable 폴더에 있는 이미지로 세팅하기
        iv.setImageResource(R.drawable.a1);

        iv.setOnClickListener(new MyListener());





    }//end onCreate()

    class MyListener implements View.OnClickListener{

        int i=0;
        TextView tvResult=findViewById(R.id.tvResult);

        @Override
        public void onClick(View v) {
            i++;
            if(i==imageId.length) i=0;
            iv.setImageResource(imageId[i]);
            tvResult.setText("이미지 뷰: "+i);

        }
    }

}//end Activity
