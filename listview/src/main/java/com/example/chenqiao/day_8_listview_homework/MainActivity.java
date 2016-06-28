package com.example.chenqiao.day_8_listview_homework;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MyDBHelper myDBHelper;
    private SQLiteDatabase database;
    private ArrayList<Star> arrayList;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到listview控件
        ListView listview = (ListView) findViewById(R.id.listview);

        myDBHelper = new MyDBHelper(this, "stars.db", null, 1);

        database = myDBHelper.getReadableDatabase();
        count = 0;

        Cursor cursor = database.rawQuery("select * from star", null);
        arrayList = new ArrayList();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String nationality = cursor.getString(2);

            Star star = new Star(id, name, nationality);
            arrayList.add(star);
            count++;
            Log.i("star info", id + "--" + name + "--" + nationality);
            System.out.println("=====" + count);
        }

        System.out.println("==" + arrayList.size());
        listview.setAdapter(new MyAdapter());
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
            TextView nationality = (TextView) view.findViewById(R.id.nationality);
            Star star = (Star) getItem(position);                                    // 根据位置获取Person对象
            id.setText(star.getId() + "");                                                // 给TextView设置文本
            name.setText(star.getName());
            nationality.setText(star.getNationality());

            return view;

        }
    }
}
