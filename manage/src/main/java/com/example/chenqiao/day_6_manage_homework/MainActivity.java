package com.example.chenqiao.day_6_manage_homework;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase database;
    private MyDBHelper dbOpenHelper;
    private EditText id;
    private EditText name;
    private EditText age;
    private EditText gender;
    private EditText phone;

    private LinearLayout linerLayout_content;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbOpenHelper = new MyDBHelper(this, "mydb.db", null, 1);
        database = dbOpenHelper.getWritableDatabase();


        name = (EditText) findViewById(R.id.insert_editText1);
        age = (EditText) findViewById(R.id.insert_editText2);
        gender = (EditText) findViewById(R.id.insert_editText3);
        phone = (EditText) findViewById(R.id.insert_editText4);


        linerLayout_content = (LinearLayout) findViewById(R.id.linerLayout_content);
    }


    public void insert(View view) {


        String insert_name = name.getText().toString();
        int insert_age = Integer.parseInt(age.getText().toString());
        String insert_gender = gender.getText().toString();
        String insert_phone = phone.getText().toString();

        Object[] argments = {insert_name, insert_age, insert_gender, insert_phone};

        String insert = "insert into person(name,age,gender,phone) values(?,?,?,?);";
        database.execSQL(insert, argments);

        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }


    public void delete(View view) {
        String delete_name = name.getText().toString();
        if (delete_name.equals("")) {
            Toast.makeText(this, "请输入要查询的name", Toast.LENGTH_LONG).show();
            return;
        } else {
            Object[] argemnts = {delete_name};

            String deleteString = "delete from person where name=?;";
            database.execSQL(deleteString, argemnts);
//            Toast.makeText(this, "."+delete_name+".", Toast.LENGTH_LONG).show();

            Toast.makeText(this, "删除成功", Toast.LENGTH_LONG).show();
        }
    }

    public void update(View view) {

    }


    public void query(View view) {


//        person = new Person(name,age, gender);
//        TextView tv = new TextView(this);
//        tv.setText(person.toString());
//
//        linerLayout_content.addView(tv);
        linerLayout_content.removeAllViews();
        String queryname = name.getText().toString();

//        String[] queryArgs = {queryname};
        //得到结果集游标
        if (queryname.equals("")) {//如果name项为空，则输出所有person信息
            String querystring = "select * from person;";
            Cursor cursor = database.rawQuery(querystring, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String gender = cursor.getString(3);
                String phone = cursor.getString(4);

                //封装数据调用tostring方法输出
                Person person = new Person(id, name, age, gender, phone);

                TextView tv = new TextView(this);
                tv.setText(person.toString());
                linerLayout_content.addView(tv);
            }
        } else {
//            若name查询项不为空，查询对应信息
            String querystring = "select * from person where name=?;";
            String[] queryArgs = {queryname};
            Cursor cursor = database.rawQuery(querystring, queryArgs);
            if(!cursor.moveToNext()){
                Toast.makeText(this,"您查询的信息不存在",Toast.LENGTH_LONG).show();
            }else {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String gender = cursor.getString(3);
                String phone = cursor.getString(4);


                Person person = new Person(id, name, age, gender, phone);

                TextView tv = new TextView(this);
                tv.setText(person.toString());
                linerLayout_content.addView(tv);
            }
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                String gender = cursor.getString(3);
                String phone = cursor.getString(4);


                Person person = new Person(id, name, age, gender, phone);

                TextView tv = new TextView(this);
                tv.setText(person.toString());
                linerLayout_content.addView(tv);
            }

        }
    }
}
