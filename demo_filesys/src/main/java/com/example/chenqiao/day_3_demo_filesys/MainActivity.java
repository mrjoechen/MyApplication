package com.example.chenqiao.day_3_demo_filesys;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    EditText edit_username;
    EditText edit_password;

    CheckBox cb_remember;
    //    Button bt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);
        cb_remember = (CheckBox) findViewById(R.id.cb_remember);


        showback();



         /*File file = new File("/data/data/com.cskoayan.lan.myfilesysdemo/1.txt");

         if (file.exists()){

             Log.i("filedemo","file exist");

         }else {

             Log.i("filedemo","file not exist");

             try {
                 file.createNewFile();
             } catch (IOException e) {
                 e.printStackTrace();
             }

         }*/

    }

    public void showback(){
        File file = new File("/data/data/com.cskoayan.lan.myfilesysdemo/1.txt");

        if (file.exists()){

            Log.i("filedemo","file exist");

            FileReader fileReader=null;
            try {
                fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String readLine = bufferedReader.readLine();

                String[] split = readLine.split("##");
                String username = split[0];
                String password = split[1];

                edit_username.setText(username);
                edit_password.setText(password);
                cb_remember.setChecked(true);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fileReader!=null){
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }


        }else {

            Log.i("filedemo","file not exist");

            Toast.makeText(this, "没有记录用户名和密码", Toast.LENGTH_LONG).show();

        }

    }

    public void login(View v)   {

        //1.取出两个edittext控件的东西
        String username =  edit_username.getText().toString();
        String password = edit_password.getText().toString();

        boolean checked = cb_remember.isChecked();


        if (checked){

            //2.保存到文件中
            FileOutputStream fos=null;
            try {

                fos = new FileOutputStream(new File("/data/data/com.cskoayan.lan.myfilesysdemo/1.txt"));
                fos.write((username+ "##" + password).getBytes());

                /*Toast toast = Toast.makeText(this, username + ":" + password, Toast.LENGTH_SHORT);
                toast.show();*/

                Toast.makeText(this, username + ":" + password, Toast.LENGTH_LONG).show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {

                if (fos!=null)
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }


    }


}

