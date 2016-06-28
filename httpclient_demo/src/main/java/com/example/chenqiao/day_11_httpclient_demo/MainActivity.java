package com.example.chenqiao.day_11_httpclient_demo;

import android.app.ActionBar;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

public class MainActivity extends ActionBarActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

    }
    Handler MyHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){

                case 1:

                    String text = (String) msg.obj;
                    Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
                    break;

                case -1:
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };



    //get方式提交数据
    public void login1(View view){

        new Thread(){public void run(){
            try {
             //获取用户名和密码
            String u = username.getText().toString();
            String p = password.getText().toString();


            String path = "http://192.168.3.36:8080/servlet/LoginServlet?username="+u+"&password="+p;
            //获取httpclient实例
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(path);
            //执行请求,获取状态码
                HttpResponse response = client.execute(get);
                int code = response.getStatusLine().getStatusCode();
                if (code == 200){
                    Log.i("httpclient","get server response!");
                    InputStream inputStream = response.getEntity().getContent();
                    String textFromStream = HttpUtils.getTextFromStream(inputStream);
                    Message msg = MyHandler.obtainMessage();
                    msg.what=1;
                    msg.obj = textFromStream;
                    MyHandler.sendMessage(msg);
                } else
            {
                Message msg = MyHandler.obtainMessage();
                msg.what=-1;
                MyHandler.sendMessage(msg);

            }
            } catch (IOException e) {
                e.printStackTrace();
            }


        };}.start();



    }
    //post方式提交数据
    public void login2(){

    }
}
