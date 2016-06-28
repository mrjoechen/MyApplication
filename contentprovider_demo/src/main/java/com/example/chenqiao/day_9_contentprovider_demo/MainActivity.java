package com.example.chenqiao.day_9_contentprovider_demo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.security.PublicKey;
import java.util.ArrayList;

/*provider为day_6_manager_homework
 */
public class MainActivity extends AppCompatActivity {

    private ArrayList<Person> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        resolver.insert(uri,values);
//        resolver.delete(uri, null, null);
//        resolver.update(uri, values, null, null);


    }

    public void insert(View view){
        //获取解析器对象
        ContentResolver resolver = getContentResolver();

        //访问内容提供者
        ContentValues values = new ContentValues();
        Uri uri = Uri.parse("content://com.example.chenqiao.day_6_manage_homework");

    }

    public void delete(View view){
        //获取解析器对象
        ContentResolver resolver = getContentResolver();

        //访问内容提供者
        ContentValues values = new ContentValues();
        Uri uri = Uri.parse("content://com.example.chenqiao.day_6_manage_homework");

    }
    public void query(View view){
        ListView listview = (ListView) findViewById(R.id.listview);

        //获取解析器对象
        ContentResolver resolver = getContentResolver();
        //访问内容提供者
        ContentValues values = new ContentValues();
        Uri uri = Uri.parse("content://com.example.chenqiao.day_6_manage_homework");
        Cursor cursor =  resolver.query(uri, null, null, null, null);
        arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String gender = cursor.getString(3);
            String phone = cursor.getString(4);

            Person person = new Person(id, name, age,gender,phone);
            arrayList.add(person);

            Log.i("person info", id + "--" + name + "--" + age + "--" + gender + "--" + phone);

        }
        listview.setAdapter(new MyAdapter());

    }

    public void update(View view){

    }
    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("=====" + position);
            View view;
            if (convertView == null) {
                view = View.inflate(MainActivity.this, R.layout.list, null);
                Log.i("tag", "新建的view");
            } else {
                view = convertView;
                Log.i("tag", "这是convertview");
            }
            TextView id = (TextView) view.findViewById(R.id.id);                    // 获取这个新生成的View中的TextView
            TextView name = (TextView) view.findViewById(R.id.name);
            TextView age = (TextView) view.findViewById(R.id.age);
            TextView gender = (TextView) view.findViewById(R.id.gender);
            TextView phone = (TextView) view.findViewById(R.id.phone);
            Person person = (Person) getItem(position);                                    // 根据位置获取Person对象

            id.setText(person.getId() + "");                                                // 给TextView设置文本
            name.setText(person.getName());
            age.setText(person.getAge()+"");
            gender.setText(person.getGender());
            phone.setText(person.getPhone());

            return view;

        }
    }
}