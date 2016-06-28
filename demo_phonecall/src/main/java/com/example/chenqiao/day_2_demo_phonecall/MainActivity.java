package com.example.chenqiao.day_2_demo_phonecall;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button_call;


    EditText editText2;
    EditText editText3;
    Button sendmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载一个布局
        setContentView(R.layout.activity_main);

        //第一步，找到控件button，editText强转
        button_call = (Button) findViewById(R.id.bt_dialer);
        editText = (EditText) findViewById(R.id.editText);

        //第二步，给button控件设置回调函数（一个点击事件）。
        button_call.setOnClickListener(new MyCallOnClickListener());


//        sendmsg = (Button) findViewById(R.id.sendmsg);
//        editText2 = (EditText) findViewById(R.id.editText2);
//        editText3 = (EditText) findViewById(R.id.editText3);
//        button_call.setOnClickListener(new MysendmsgCOnClickListener());

    }


    class MyCallOnClickListener implements View.OnClickListener {

        public void onClick(View v) {

            //System.out.println("bt clicked");
            //点击button的时候
            //  1. 获取textview里的文字
            String number = editText.getText().toString();
            if ("".equals(number)) {
                Toast.makeText(MainActivity.this, "电话不能为空", 1).show();
                return;
            }
            //  2. 把电话号码给到真正要打电话的应用,进行拨打电话,创建一个意图对象
            //设置动作
//            intent.setAction(Intent.ACTION_CALL);
            //设置要拨打的数据
//            intent.setData(Uri.parse("tel:"+number));
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //开启意图

            startActivity(intent);

        }

    }

    class MysendmsgCOnClickListener implements View.OnClickListener {
        public void onClick(View v) {

            String msg = editText2.getText().toString();
            String smsto = editText3.getText().toString();
            if ("".equals(msg)) {
                Toast.makeText(MainActivity.this, "内容不能为空", 1).show();
                return;
            }
            if ("".equals(smsto)) {
                Toast.makeText(MainActivity.this, "号码不能为空", 1).show();
                return;
            }


            Uri smsToUri = Uri.parse("smsto:");
            Intent sendIntent = new Intent(Intent.ACTION_VIEW, smsToUri);
            sendIntent.putExtra("address", smsto);   //电话号码，这行去掉的话，默认就没有电话
            sendIntent.putExtra("sms_body", msg);
            sendIntent.setType("vnd.android-dir/mms-sms");

            startActivity(sendIntent);

        }
    }


}
