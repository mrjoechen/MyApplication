package com.example.chenqiao.day_4_homework_findfile;
/*
判断sdcard下是否有以后缀名为.jpg结尾的文件，如果有就输出此文件的文件名
 */

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void find(View view){
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String externalStorageState = Environment.getExternalStorageState();
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            File[] files = externalStorageDirectory.listFiles();
            for (File file :files){
                String filename = file.getName();
                if(filename!=null&&filename.endsWith(".jpg")){
                    Log.i("jpg",filename);
                }
            }
            Log.i("jpg","查找完成");
            Toast.makeText(this, "over",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "SD卡未挂载",Toast.LENGTH_LONG).show();
        }
    }

}
