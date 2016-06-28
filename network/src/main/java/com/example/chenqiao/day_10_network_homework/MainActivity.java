package com.example.chenqiao.day_10_network_homework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_jpg;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Bitmap bitmap = (Bitmap) msg.obj;
                iv_jpg.setImageBitmap(bitmap);
            }
            else  if(msg.what==2){
                Bitmap bitmap = (Bitmap) msg.obj;
                iv_jpg.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this, "来自内存~~·", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_jpg = (ImageView) findViewById(R.id.iv_jpg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getJpg();
            }
        }).start();
    }

    private void getJpg() {
        File file = new File(getCacheDir(), "iu.jpg");
        System.out.println("=========" + file.getAbsolutePath());
        if(file.exists())
        {
            System.out.println("=========" + file);
            Bitmap bitmap = BitmapFactory.decodeFile(getCacheDir() + "/iu.jpg");
            Message msg = new Message();
            msg.what=2;
            msg.obj=bitmap;
            handler.sendMessage(msg);

        }else {
            System.out.println("=========");
            try {
                URL url = new URL("http://192.168.3.36:8080/Test/servlet/Test/iu.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                connection.setRequestMethod("GET");
                int code = connection.getResponseCode();
                if(code==200){
                    InputStream inputStream = connection.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(getCacheDir()+"/iu.jpg");
                    byte[] bytes = new byte[1024];
                    int len=0;
                    while ((len=inputStream.read(bytes))!=-1){
                        fileOutputStream.write(bytes,0,len);
                    }
                    fileOutputStream.close();
                    Bitmap bitmap = BitmapFactory.decodeFile(getCacheDir() + "/iu.jpg");
                    Message msg = new Message();
                    msg.what=1;
                    msg.obj=bitmap;
                    handler.sendMessage(msg);
                }

            } catch (Exception e) {

            }
        }
    }

}
