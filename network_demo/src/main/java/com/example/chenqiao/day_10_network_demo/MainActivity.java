package com.example.chenqiao.day_10_network_demo;
//网页源码显示
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private EditText edit_path;
    private TextView result;

    //主线程创建handler
    private Handler handler = new Handler() {
        @Override
        //接受子线程消息
        public void handleMessage(Message msg) {

            String content = (String) msg.obj;

                result.setText(content);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到控件
        edit_path = (EditText) findViewById(R.id.edit_path);
        result = (TextView) findViewById(R.id.result);

    }


    //点击按钮产生的操作
    public void click(View view) {


        //创建子线程
        new Thread(){
            @Override
            public void run() {
                //获取路径
                String path = "http://"+edit_path.getText().toString();
                try {
                    //创建url对象
                    URL url = new URL(path);

                    //获取httpurlconnection对象
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置请求方式，注意请求方式为大写
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(3000);
                    //获取响应状态码
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        //获取返回的数据
                        InputStream inputStream = connection.getInputStream();
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        int length = -1;
                        byte[] buffer = new byte[1024];
                        while ((length = inputStream.read(buffer)) != -1) {
                            byteArrayOutputStream.write(buffer, 0, length);
                        }
                        inputStream.close();

                        String content = new String(byteArrayOutputStream.toByteArray());
                        //发消息给主线程
                        Message msg = new Message();
                        //消息携带数据
                        msg.obj = content;
                        handler.sendMessage(msg);//执行主线程handlemessage方法

                    }
                }catch (Exception e){

                    Toast.makeText(MainActivity.this,"异常",Toast.LENGTH_LONG).show();
                }
            }
        }.start();

    }


}
