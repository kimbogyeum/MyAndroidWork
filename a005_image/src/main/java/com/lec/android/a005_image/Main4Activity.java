package com.lec.android.a005_image;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
/**
 *  인터넷 상의 이미지 보여주기 과정
 *      1. 권한을 획득한다 (인터넷에 접근할수 있는 권한을 획득한다)  - 메니페스트 파일
 *      2. Thread 에서 웹의 이미지를 받아온다 - honeycomb(3.0) 버젼 부터 바뀜
 *      3. 외부쓰레드에서 메인 UI에 접근하려면 Handler 를 사용해야 한다.
 *
 *
 *
 */
public class Main4Activity extends AppCompatActivity {

    //이미지 URL,반드시 http://이어야 한다.
    String imgUrl = "https://developer.android.com/studio/images/studio-icon-stable.png";

    ImageView iv1;
    TextView tvUrl;


    //Handler 객체 만들어주기
    Handler handler=new Handler();//외부 쓰레드에서 main UI화면에 그릴때 사용


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        iv1=findViewById(R.id.iv1);
        tvUrl=findViewById(R.id.tvUrl);

        //Thread t=new Thread(Runnable);
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                //Bitmap <- InputStream <- URL <-"url"
                try {
                    //Thread 없이 수행하면
                    // android.os.NetworkOnMainThreadException 발생
                    URL url=new URL(imgUrl);
                    InputStream in=url.openStream();
                    final Bitmap bm= BitmapFactory.decodeStream(in);//같은 로컬변수끼리 못써주니까 final로 해줘야함.
                    //iv1.setImageBitmap(bm);
                    //CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views
                    //main thread에서 따로 외부 thread를 뽑아서 네트워크랑 상호작용 후 다시 main에 반영해주려고하는건 위의 에러가 나면서 안됨.
                    // -> Handler이라는걸 중간에 매개로 껴줘야함.


                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //외부스레드에서 메인 UI 접근할떄는 반드시 Handler 객체 사용용
                           iv1.setImageBitmap(bm);
                            tvUrl.setText(imgUrl);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();


    }//end onCreate()
}//end Activity
