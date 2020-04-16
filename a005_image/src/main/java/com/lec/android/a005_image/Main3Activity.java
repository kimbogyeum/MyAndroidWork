package com.lec.android.a005_image;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class Main3Activity extends AppCompatActivity {

    ImageView ivPhoto;
    TextView tvPath;
    Button btnAction;

    /**
     * 폰의 저장장치에 있는 사진을 ImageView 에 띄우기
     *    1. 권한 획득
     *        - 메니페스트
     *        - '위험권한'요청  (Android 6.0 / API23 이상)
     *         https://developer.android.com/guide/topics/security/permissions?hl=ko  (위험권한및 위험권한 목록들)
     *            https://developer.android.com/training/permissions/requesting?hl=ko#java
     *
     *            설치 앱별 '권한' 확인 가능하다 :
     *                픽셀2 폰의 경우 '앱' 아이콘 롱클릭후 App Info 확인
     *
     *    2. 이미지 경로
     *    3. Bitmap 사용하여 ImageView 에 띄우기
     */




    //이미지 경로 알아내기
    //픽셀폰: 이미지 보기 -> 하단의 i 버튼
    //삼성폰: 갤러리 이미지 롱클릭 후 상세정보

    private final int PERMISSION_REQUEST_CODE=101; // 숫자는 아무거나, 권한요청코드값(int)
    private final String[] PERMISSIONS= {//요청할 권한들을 String[]로 준비
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    private String imgPath="/sdcard/DCIM/Camera/france.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ivPhoto=findViewById(R.id.ivPhoto);
        btnAction=findViewById(R.id.btnAction);
        tvPath=findViewById(R.id.tvPath);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawPhoto();
                //E/BitmapFactory: Unable to decode stream: java.io.FileNotFoundException:
                // /storage/emulated/0/DCIM/Camera/france.png: open failed: EACCES (Permission denied)
            }
        });

        //위험권한 획득
        //바뀐 정책에 의해 API23(마시멜로) 이상에서만 권한 요청되어야한다.
        //권한을 획득하지 않은 상태에서만 요청
        //Build.VERSION_CODES.M 이라고 해도되고 23이라고 해도되고
        //Read 가능한지 권한 확인 -> 승인받지 않았을때 -> 권한 요청
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
                //Read 가능한지 권한 확인 -> 승인받지 않았을때 -> 권한 요청
                //권한요청: 비동기로 진행됨.
            ActivityCompat.requestPermissions(this,
                    PERMISSIONS,//요청할 위험권한들
                    PERMISSION_REQUEST_CODE);//권한 요청코드


            }else{
            //권한을 이미 획득한 상태
            drawPhoto();
        }




    }//end onCreate()
    public void drawPhoto(){
        //경로명에서 Bitmap 객체 뽑아내서 ImageView에 셋팅
        Bitmap bm =BitmapFactory.decodeFile(imgPath);
        ivPhoto.setImageBitmap(bm);
        tvPath.setText(imgPath);

    }//end drawPhoto()

    //권한 획득 결과 처리 콜백
    //requestCode: 권한 요청코드값
    //permission: 권한 요청


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
        case PERMISSION_REQUEST_CODE:
            if(grantResults.length>0 && grantResults[0]==getPackageManager().PERMISSION_GRANTED){
                //사용자가 권한 승인
//            Log.d("myapp","권한획득굘과\n\t"Arrays.toString(permissions)+" : "+Arrays.toString(grantResults));
        }else{
                //사용자가 권한 승인 안함.

        }
            break;
    }
    }
}//end Activity
