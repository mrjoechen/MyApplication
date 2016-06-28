package com.example.chenqiao.day_4_demo_sharedpref;

        import android.content.SharedPreferences;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private EditText edit_username;
    private EditText edit_password;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edit_username = (EditText) findViewById(R.id.edit_username);
        edit_password = (EditText) findViewById(R.id.edit_password);

        sp= getSharedPreferences("config", MODE_PRIVATE);


        showBack();

    }


    public void showBack(){

        String username = sp.getString("username", "");
        int password = sp.getInt("password", 0);

        edit_username.setText(username);
        edit_password.setText(password+"");

    }

    public void login(View v){

        String password = edit_password.getText().toString();
        String username = edit_username.getText().toString();




        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username",username);
        edit.putInt("password", Integer.parseInt(password));

        edit.commit();

        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();

    }

}

