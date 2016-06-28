package com.example.chenqiao.day_5_sqlite_demo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private MyDBOpenHelper dbOpenHelper;
    private EditText insert_id;
    private EditText insert_name;
    private EditText insert_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbOpenHelper = new MyDBOpenHelper(this,"mydb.db",null,1);

        insert_id = (EditText) findViewById(R.id.insert_editText1);
        insert_name = (EditText) findViewById(R.id.insert_editText2);
        insert_gender = (EditText) findViewById(R.id.insert_editText3);


    }

    public void insert(View v){
        SQLiteDatabase  db = dbOpenHelper.getWritableDatabase();
        String idSting = insert_id.getText().toString();
        String nameString = insert_name.getText().toString();
        int Id=Integer.parseInt(idSting);
        String genderString = insert_gender.getText().toString();

        String sql="insert into person (id,name,gender) values(?,?,?);";
        db.execSQL(sql,new Object[]{Id,nameString,genderString});
        db.close();
        Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_SHORT).show();
    }
    public void delete(View v){

    }
    public void update(){

    }
    public void query(){

    }


}
