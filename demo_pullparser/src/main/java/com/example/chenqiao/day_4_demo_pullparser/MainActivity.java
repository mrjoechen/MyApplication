package com.example.chenqiao.day_4_demo_pullparser;


        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.util.Xml;

        import org.xmlpull.v1.XmlPullParser;
        import org.xmlpull.v1.XmlPullParserException;
        import org.xmlpull.v1.XmlSerializer;

        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;

public class MainActivity extends ActionBarActivity {


    Student[] students  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        students = new Student[4];

        for (int i=0;i<4;i++){
            students[i]=new Student();
        }

        parse();

        for (int i=0;i<4;i++){

            Log.v("student",students[i].toString());
        }

        saveToAnotherXmlFile();



    }

    public void saveToAnotherXmlFile(){

        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            FileOutputStream fos = openFileOutput("students.xml", MODE_PRIVATE);

            xmlSerializer.setOutput(fos,"utf-8");

            //写头结点
            xmlSerializer.startDocument("utf-8",true);


            //写根节点
            xmlSerializer.startTag(null,"students");


            //写子节点
            for (int i=0;i<4;i++) {

                xmlSerializer.startTag(null,"student");

                xmlSerializer.startTag(null,"name");
                xmlSerializer.text(students[i].getName());
                xmlSerializer.endTag(null, "name");

                xmlSerializer.startTag(null,"gender");
                xmlSerializer.text(students[i].getGender());
                xmlSerializer.endTag(null, "gender");

                xmlSerializer.startTag(null,"id");
                xmlSerializer.text(students[i].getId());
                xmlSerializer.endTag(null, "id");

                xmlSerializer.startTag(null,"class");
                xmlSerializer.text(students[i].getClasses());
                xmlSerializer.endTag(null, "class");

                xmlSerializer.endTag(null,"student");

            }

            //根节点闭合
            xmlSerializer.endTag(null,"students");

            xmlSerializer.endDocument();

            fos.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void parse(){

        XmlPullParser parser = Xml.newPullParser();

        try {
            FileInputStream fileInputStream = openFileInput("books.xml");
            parser.setInput(fileInputStream, "utf-8");



            int next = parser.next();

            Student stu =null;
            int i=0;

            while(next != parser.END_DOCUMENT){

                switch (next){

                    case XmlPullParser.START_DOCUMENT:

                        Log.i("parse", "START_DOCUMENT");
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        Log.i("parse", "END_DOCUMENT");

                        break;

                    case XmlPullParser.START_TAG:
                        String name = parser.getName();
                        Log.i("parse", "START_TAG:"+name);

                        if (name.equals("student")){
                            stu = students[i];
                        }
                        else if (name.equals("name")){
                            String nextText = parser.nextText();
                            stu.setName(nextText);
                        }else if(name.equals("gender")){
                            String nextText = parser.nextText();
                            stu.setGender(nextText);
                        }else if(name.equals("id")){
                            String nextText = parser.nextText();
                            stu.setId(nextText);
                        }else if(name.equals("class")){
                            String nextText = parser.nextText();
                            stu.setClasses(nextText);
                        }



                        break;

                    case XmlPullParser.END_TAG:

                        String edname = parser.getName();

                        if (edname.equals("student")){
                            i++;
                        }

                        Log.i("parse", "END_TAG:"+edname);

                        break;

                    case XmlPullParser.TEXT:
                        String text = parser.getText();
                        Log.i("parse", "TEXT:"+text);
                        break;

                }



                next = parser.next();
                Log.i("parse", next+" ");

            }

            /*Log.i("parse", next+" ");

            if (next==parser.START_DOCUMENT){

                Log.i("parse","START_DOCUMENT");

            }
            if (next==parser.START_TAG){


            }*/


            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
