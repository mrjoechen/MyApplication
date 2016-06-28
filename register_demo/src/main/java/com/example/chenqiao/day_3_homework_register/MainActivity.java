package com.example.chenqiao.day_3_homework_register;


        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
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
    EditText edit_email;
    EditText edit_phonenumber;
    CheckBox cb_approve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_username = (EditText) findViewById(R.id.username);
        edit_password = (EditText) findViewById(R.id.password);
        edit_email = (EditText) findViewById(R.id.email);
        edit_phonenumber = (EditText) findViewById(R.id.phonenumber);
        cb_approve = (CheckBox) findViewById(R.id.cb_approve);

        showback();


    }


    public void showback() {
        File file = new File("/data/data/com.example.chenqiao.day_3_homework_register/info.txt");
        FileReader fileReader = null;
        if(file.exists()){


            try {
                fileReader = new FileReader(file);
                BufferedReader bufferreader = new BufferedReader(fileReader);
                String readLine = bufferreader.readLine();

                String[] split = readLine.split("##");
                edit_username.setText(split[0]);
                edit_password.setText(split[1]);
                edit_email.setText(split[2]);
                edit_phonenumber.setText(split[3]);

                cb_approve.setChecked(true);


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


        }

    }

    public void register(View v) {
        //取出空间内容
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();
        String email = edit_email.getText().toString();
        String phonenumber = edit_phonenumber.getText().toString();


        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File("/data/data/com.example.chenqiao.day_3_homework_register/info.txt"));
            fileOutputStream.write((username + "##" + password + "##" + email + "##" + phonenumber).getBytes());
            Toast.makeText(this, username + "注册成功：", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
